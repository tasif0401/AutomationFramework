
package com.example.base;

import java.io.FileInputStream;
import java.util.Properties;

public class DriverFactory {
    public static Properties loadConfig(String name) throws Exception {
        Properties prop = new Properties();
        String path = "src/test/resources/config/" + name;
        try(FileInputStream fis = new FileInputStream(path)){
            prop.load(fis);
		} catch (Exception e) {
			throw new Exception("Failed to load configuration file: " + path, e);
		}
        return prop;
    }
}
