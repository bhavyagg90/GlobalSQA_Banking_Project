package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
 private Properties properties;

 public ConfigReader() {
     properties = new Properties();
     try (FileInputStream fis = new FileInputStream("src/test/resources/config/config.properties")) {
         properties.load(fis);
     } catch (IOException e) {
         e.printStackTrace();
     }
 }

 public String getUrl() {
     return properties.getProperty("url");
 }
}


