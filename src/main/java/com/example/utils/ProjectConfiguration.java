package com.example.utils;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class ProjectConfiguration {

    public static Properties properties;

    static {
        properties = new Properties();
        URL config = ClassLoader.getSystemResource("project.cfg");
        try {
            properties.load(config.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
