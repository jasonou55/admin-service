package com.jarvis.adminservice.util;

import com.jarvis.adminservice.enums.ErrorCode;
import com.jarvis.adminservice.exception.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class EncryptUtils {

    private static String SECRET_KEY = "@an#encrypt$key%";

    public static String aesDecrypt(String encryptStr) {
        try {
            if (StringUtils.isEmpty(encryptStr) || StringUtils.isEmpty(SECRET_KEY)) {
                return null;
            }

            byte[] encryptByte = Base64.getDecoder().decode(encryptStr);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(SECRET_KEY.getBytes(), "AES"));
            byte[] decryptBytes = cipher.doFinal(encryptByte);
            return new String(decryptBytes);
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.ENCRYPT_ERROR, "AES decrypt failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static String aesEncrypt(String content) {
        try {
            if (StringUtils.isEmpty(content) || StringUtils.isEmpty(SECRET_KEY)) {
                return null;
            }

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(SECRET_KEY.getBytes(), "AES"));
            byte[] encryptStr = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptStr);
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.ENCRYPT_ERROR, "AES encrypt failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static String md5Encrypt(String s) {
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
            throw new ServiceException(ErrorCode.ENCRYPT_ERROR, "Md5 encrypt failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
