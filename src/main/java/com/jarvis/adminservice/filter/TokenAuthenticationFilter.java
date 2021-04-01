package com.jarvis.adminservice.filter;

import com.jarvis.adminservice.constant.SecurityConstants;
import com.jarvis.adminservice.service.UserService;
import com.jarvis.adminservice.util.JwtTokenUtils;
import com.jarvis.adminservice.util.RedisUtils;
import io.jsonwebtoken.MalformedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class TokenAuthenticationFilter extends BasicAuthenticationFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenAuthenticationFilter.class.getName());
    private final UserService userService;
    private final RedisUtils redisUtils;

    public TokenAuthenticationFilter(
            AuthenticationManager authenticationManager,
            UserService userService,
            RedisUtils redisUtils
    ) {
        super(authenticationManager);
        this.userService = userService;
        this.redisUtils = redisUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        String token = request.getHeader(SecurityConstants.TOKEN_HEADER);
        if (token == null
                || !token.startsWith(SecurityConstants.TOKEN_PREFIX)
                || !redisUtils.hasKey(token.replace(SecurityConstants.TOKEN_PREFIX, ""))) {
            SecurityContextHolder.clearContext();
        } else {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }

    /**
     * 获取用户认证信息 Authentication
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String authorization) {
        LOGGER.info("Get authentication");
        String token = authorization.replace(SecurityConstants.TOKEN_PREFIX, "");
        try {
            String username = JwtTokenUtils.getUsernameByToken(token);
            if (!StringUtils.isEmpty(username)) {
                // 这里又从数据库拿了一遍,避免用户的角色信息有变
                UserDetails userDetails = userService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());
                return userDetails.isEnabled() ? usernamePasswordAuthenticationToken : null;
            }
        } catch (MalformedJwtException | IllegalArgumentException exception) {
            LOGGER.warn("Request to parse JWT with invalid signature. {}", exception.getMessage());
        }
        return null;
    }
}
