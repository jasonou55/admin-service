package com.jarvis.adminservice.util;

import java.util.Base64;

public class DesUtil {

    private static String KEY_STR = "!this@is#private$key%";

    private static String CHARSETNAME = "UTF-8";

    public static String getEncryptString(String str) {

        try {
            str = str + KEY_STR;
            return Base64.getEncoder().encodeToString(str.getBytes(CHARSETNAME));
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static String getDecryptString(String str) {

        try {
            String decodeStr = new String(Base64.getDecoder().decode(str), CHARSETNAME);

            return decodeStr.replace(KEY_STR, "");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
