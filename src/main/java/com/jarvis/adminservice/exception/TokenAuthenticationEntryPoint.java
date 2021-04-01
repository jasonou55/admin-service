package com.jarvis.adminservice.exception;

import com.jarvis.adminservice.enums.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class TokenAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenAuthenticationEntryPoint.class);

    /**
     * 当用户尝试访问需要权限才能的REST资源而不提供Token或者Token错误或者过期时，
     * 将调用此方法发送401响应以及错误信息
     */
    @Override
    public void commence(HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException {
        LOGGER.warn(ErrorCode.UNAUTHORIZED.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ErrorCode.UNAUTHORIZED.getMessage() + authException.getMessage());
    }
}
