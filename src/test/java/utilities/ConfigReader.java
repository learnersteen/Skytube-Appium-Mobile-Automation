package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static {
        try {
            properties = new Properties();
            FileInputStream fis = new FileInputStream("src/test/resources/config/config.properties");
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties file", e);
        }
    }

    public static String getProperty(String key) {

        // 1️⃣ First check environment variable
        String envValue = System.getenv(key);

        if (envValue != null && !envValue.isEmpty()) {
            return envValue;
        }

        // 2️⃣ Then check properties file
        return properties.getProperty(key);
    }
}



//package utilities;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.Properties;
//
//public class ConfigReader {
//
//    private static Properties properties;
//
//    static {
//        try {
//            properties = new Properties();
//            FileInputStream fis = new FileInputStream("src/test/resources/config/config.properties");
//            properties.load(fis);
//        } catch (IOException e) {
//            throw new RuntimeException("Failed to load config.properties file", e);
//        }
//    }
//
//    public static String getProperty(String key) {
//        return properties.getProperty(key);
//    }
//}

//import java.io.FileInputStream;
//import java.util.Properties;
//
//public class ConfigReader {
//
//    private static Properties prop;
//
//    static {
//        try {
//            FileInputStream fis = new FileInputStream("src/test/resources/config/config.properties");
//            prop = new Properties();
//            prop.load(fis);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static String get(String key) {
//        return prop.getProperty(key);
//    }
//}
