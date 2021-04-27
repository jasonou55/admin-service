package com.jarvis.adminservice.constant;

import org.springframework.stereotype.Component;

@Component
public class SettingConstants {

    public static final String ALLOWED_CHARACTERS = "0123456789ABCDEFGHJKLMNPQRSTUVWXYZ";
    public static final long USER_JWT_EXPIRE_TIME = 86400;
    public static final String ROLE_PREFIX = "ROLE_";
}
