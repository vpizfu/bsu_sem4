package web.sem2.lab1.controllers;

import java.util.ResourceBundle;

public class DbConfigurationManager {
    private static final String DEFAULT_FILE = "web.sem2.lab1.database";
    private static final String URL_KEY = "url";
    private static final String DRIVER_KEY = "driver";
    private static final String USER_KEY = "user";
    private static final String PASS_KEY = "password";

    private ResourceBundle dbProps;

    public DbConfigurationManager() {
        this(DEFAULT_FILE);
    }
    public DbConfigurationManager(String bundleName) {
        dbProps = ResourceBundle.getBundle(bundleName);
    }

    private String getValue(String key) {
        return dbProps.getString(key);
    }

    public String getUrl() {
        return getValue(URL_KEY);
    }
    public String getDriver() {
        return getValue(DRIVER_KEY);
    }
    public String getUser() {
        return getValue(USER_KEY);
    }
    public String getPassword() {
        return getValue(PASS_KEY);
    }
}
