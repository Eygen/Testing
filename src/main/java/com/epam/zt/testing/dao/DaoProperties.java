package com.epam.zt.testing.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DaoProperties {
    private static final String PROPERTIES_FILE = "dao.properties";
    private static final Properties PROPERTIES = new Properties();

    public DaoProperties() {
        init();
    }

    private void init() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propertiesFile = classLoader.getResourceAsStream(PROPERTIES_FILE);
        if (propertiesFile == null) {
            throw new DaoException("Properties file " + PROPERTIES_FILE + " is missing.");
        }
        try {
            PROPERTIES.load(propertiesFile);
        } catch (IOException e) {
            throw new DaoException("Can't load properties file " + PROPERTIES_FILE + e);
        }
    }

    public String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }
}
