package com.jarvis.adminservice.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jarvis.adminservice.constant.SecurityConstants;
import com.jarvis.adminservice.util.JwtTokenUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import java.util.Arrays;

@Aspect
@Component
public class AuditAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuditAspect.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Pointcut("execution(public * com.jarvis.adminservice.controller.*.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String requestUri = request.getRequestURI();
        String requestMethod = request.getMethod();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        String requestArgs = Arrays.asList(joinPoint.getArgs()).toString();
        String token = request.getHeader(SecurityConstants.TOKEN_HEADER);
        String username = "";
        if (token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            token = token.replace(SecurityConstants.TOKEN_PREFIX, "");
            username = JwtTokenUtils.getUsernameByToken(token);
        }
        LOGGER.info("User {} make a {} request to {} with args {} through {}.", username, requestMethod, requestUri, requestArgs, classMethod);
    }

    @AfterReturning(pointcut = "log()", returning = "response")
    public void doAfterReturning(JoinPoint joinPoint, Object response) {
        try {
            LOGGER.info("The response is {}", OBJECT_MAPPER.writeValueAsString(response));
        } catch (Exception e) {
            LOGGER.info("The response is {}", response);
        }
    }

    @AfterThrowing(pointcut = "log()", throwing = "exception")
    public void doAfterThrowing(JoinPoint joinPoint, Exception exception) {
        LOGGER.error("An error occurred: {} ", exception.getMessage());
    }
}
