package com.jarvis.adminservice.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class IdentifierGenerator {

    private static final Logger logger = LoggerFactory.getLogger(IdentifierGenerator.class);

    private IdentifierGenerator() {
    }

    public static Optional<String> getIdentifierFromId(long id, long timestamp) {
        StringBuilder identifierBuilder = new StringBuilder();
        String idStr = String.valueOf(id);
        String timestampStr = String.valueOf(timestamp);
        int idLength = idStr.length();
        identifierBuilder.append(idStr);
        if (timestampStr.length() - (13 - idLength) > 0) {
            identifierBuilder.append(timestampStr.substring(timestampStr.length() - (13 - idLength)));
        } else {
            identifierBuilder.append(timestampStr);
        }

        try {
            identifierBuilder.append(NumberUtils.toBase((long)idLength, 10, 2));
            return Optional.of(NumberUtils.toBase(Long.parseLong(identifierBuilder.toString()), 34, 10));
        } catch (IllegalArgumentException var9) {
            logger.error("Unable to get identifier from given ID and Timestamp", var9);
            return Optional.empty();
        }
    }

    public static Optional<Long> getIdFromIdentifier(String identifier) {
        if (!isValidIdentifierFormat(identifier)) {
            logger.debug("Invalid identifier format [{}]", identifier);
            return Optional.empty();
        } else {
            try {
                String inBase10 = String.valueOf(NumberUtils.toBase10(identifier, 34));
                int idLength = Integer.parseInt(inBase10.substring(inBase10.length() - 2));
                return Optional.of(Long.valueOf(inBase10.substring(0, idLength)));
            } catch (Exception var3) {
                return Optional.empty();
            }
        }
    }

    public static boolean isValidIdentifierFormat(String identifier) {
        if (identifier != null && identifier.length() == 10) {
            try {
                NumberUtils.toBase10(identifier, 34);
                return true;
            } catch (Exception var2) {
                logger.debug("Unable to parse identifier {} as base {}", identifier, 34);
                return false;
            }
        } else {
            return false;
        }
    }
}
