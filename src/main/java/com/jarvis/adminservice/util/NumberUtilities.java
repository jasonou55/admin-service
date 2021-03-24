package com.jarvis.adminservice.util;

import com.jarvis.adminservice.constant.SettingConstants;

public class NumberUtilities {

    private NumberUtilities() {
    }

    public static String toBase(long original, int base) {
        if (base <= SettingConstants.ALLOWED_CHARACTERS.length() && base > 1) {
            if (original < 0L) {
                throw new IllegalArgumentException("Negative values cannot be converted to a different base.");
            } else {
                StringBuilder result = new StringBuilder();
                long temp = original;

                do {
                    int index = (int)(temp % (long)base);
                    temp /= (long)base;
                    result.append(SettingConstants.ALLOWED_CHARACTERS.charAt(index));
                } while(temp > 0L);

                return result.reverse().toString();
            }
        } else {
            throw new IllegalArgumentException("Only conversions between bases 2 and " + SettingConstants.ALLOWED_CHARACTERS.length() + " are supported.");
        }
    }

    public static String toBase(long original, int base, int numChars) {
        String converted = toBase(original, base);
        if (numChars < converted.length()) {
            throw new NumberFormatException("Unable to represent " + original + " in Base " + base + " within " + numChars + " characters.");
        } else {
            StringBuilder prefix = new StringBuilder();

            while(prefix.length() < numChars - converted.length()) {
                prefix.append("0");
            }

            return prefix.append(converted).toString();
        }
    }

    public static long toBase10(String original, int fromBase) {
        if (original != null && !original.isEmpty()) {
            if (fromBase >= 2 && fromBase <= SettingConstants.ALLOWED_CHARACTERS.length()) {
                return original.chars().map(SettingConstants.ALLOWED_CHARACTERS::indexOf).mapToLong((v) -> {
                    return (long)v;
                }).reduce(0L, (running, current) -> {
                    if (current < 0L) {
                        throw new IllegalArgumentException("Unknown character found in " + original);
                    } else {
                        return running * (long)fromBase + current;
                    }
                });
            } else {
                throw new IllegalArgumentException("Can only convert from a Base between 2 and 34 inclusive.");
            }
        } else {
            throw new IllegalArgumentException("Must be something to convert.");
        }
    }
}
