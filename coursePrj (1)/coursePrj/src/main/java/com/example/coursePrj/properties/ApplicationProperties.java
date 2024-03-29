package com.example.coursePrj.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public class ApplicationProperties {
   private static final String file_name = "/application.properties";
    private  static ApplicationProperties instance;
    private final Properties properties =  new Properties();

    private void init() {
        try (InputStream inputStream = getClass().getResourceAsStream(file_name)) {
            if(Objects.nonNull(inputStream))
                properties.load(inputStream);
        }

        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Properties getProperties() {
        return properties;
    }

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();

        for (final String name: properties.stringPropertyNames())
            map.put(name, properties.getProperty(name));

        return map;
    }

    public static ApplicationProperties getInstance() {
        if (Objects.isNull(instance)) {
            instance = new ApplicationProperties();
            instance.init();
        }

        return instance;
    }
}
