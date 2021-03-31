package com.jarvis.adminservice.util;

import com.jarvis.adminservice.enums.ErrorCode;
import com.jarvis.adminservice.exception.ServiceException;
import org.springframework.http.HttpStatus;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Md5Utils {
    public static String encrypt(String s) {
        try {
            StringBuilder hexString = new StringBuilder();

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(s.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest();

            for (byte aByte : bytes) {
                if ((0xff & aByte) < 0x10) {
                    hexString.append("0").append(String.format( "%02X", 0xFF & aByte));
                } else {
                    hexString.append(String.format( "%02X", 0xFF & aByte));
                }
            }
            return hexString.toString().toLowerCase();
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.ENCRYPT_PASSWORD_ERROR, "Encrypt failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
