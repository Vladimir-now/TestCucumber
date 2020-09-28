package ru.appline.framework.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class PropertiesManager {


    private final Properties properties = new Properties();

    private static PropertiesManager INSTANCE = null;

    private PropertiesManager() {
        try {
            properties.load(new FileInputStream(
                    new File("src/main/resources/" +
                            System.getProperty("env", "application") + ".properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static PropertiesManager getTestPropManager() {
        if (INSTANCE == null) {
            INSTANCE = new PropertiesManager();
        }
        return INSTANCE;
    }


    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

}
