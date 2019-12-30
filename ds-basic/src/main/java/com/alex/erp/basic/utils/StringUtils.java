package com.alex.erp.basic.utils;

import java.util.Formatter;
import java.util.UUID;

/**
 * @version 0.0.1
 * @Description
 * @Author Alex ZY Liang
 * @Date 2019-10-28 6:30 PM
 */
public class StringUtils {

    public static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    public static String createNonceString() {
        return UUID.randomUUID().toString();
    }

    public static String createTimestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}
