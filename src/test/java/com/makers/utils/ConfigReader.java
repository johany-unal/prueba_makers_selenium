package com.makers.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("No se encontró config.properties en el classpath");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar config.properties: " + e.getMessage());
        }
    }

    public static String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Propiedad no encontrada en config.properties: " + key);
        }
        return value.trim();
    }

    // Helpers semánticos
    public static String getBaseUrl()          { return get("base.url"); }
    public static String getBrowser()          { return get("browser"); }
    public static boolean isHeadless()         { return Boolean.parseBoolean(get("headless")); }
    public static int getImplicitWait()        { return Integer.parseInt(get("implicit.wait")); }
    public static int getExplicitWait()        { return Integer.parseInt(get("explicit.wait")); }
    public static String getPassword()         { return get("password"); }
    public static String getInvalidPassword()  { return get("password.invalid"); }
    public static String getInventoryUrl()     { return get("url.inventory"); }
}
