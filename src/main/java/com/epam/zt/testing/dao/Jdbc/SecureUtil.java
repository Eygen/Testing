package com.epam.zt.testing.dao.Jdbc;

import org.apache.commons.codec.digest.DigestUtils;

public final class SecureUtil {

    public static String passwordToMd5(String password) {
        return DigestUtils.md5Hex(password);
    }

    public static boolean comparePassword(String password, String secure) {
        return secure.equals(passwordToMd5(password));
    }
}
