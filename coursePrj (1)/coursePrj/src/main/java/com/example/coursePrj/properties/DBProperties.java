package com.example.coursePrj.properties;

import java.util.Objects;
import java.util.Properties;

public class DBProperties {
    private static final String URL = "jdbc:postgresql://localhost:5432/bank";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "Duckling_3000";

    private static DBProperties instance;

    private String url;
    private String username;
    private String password;

    private DBProperties() {}

    private void init(Properties properties) {
        url = URL;
        username = USERNAME;
        password = PASSWORD;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static DBProperties getProperties() {
        if (Objects.isNull(instance)) {
            instance = new DBProperties();
            instance.init(ApplicationProperties.getInstance().getProperties());
        }

        return instance;
    }
}
