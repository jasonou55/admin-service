package com.jarvis.adminservice.constant;

import org.springframework.stereotype.Component;

@Component
public class SecurityConstants {

    public static final String ROLE_CLAIMS = "role";
    public static final String JWT_SECRET_KEY = "C*F-JaNdRgUkXn2r5u8x/A?D(G+KbPeShVmYq3s6v9y$B&E)H@McQfTjWnZr4u7w";

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";

    private SecurityConstants() {
    }
}