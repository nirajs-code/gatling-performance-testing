package config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigManager.class);

    private ConfigManager(){};
    private static final String ENV = System.getProperty(Constants.ENV, "test");
    private static final Properties PROP = loadConfig();

//    public static final String testType = System.getProperty("testType", "smoke"); // Test type (default: smoke)

    private static Properties loadConfig() {
        Properties properties = new Properties();
        String config_path = String.format("config/%s/appConfig.properties", ENV);

        try (InputStream in = ConfigManager.class.getClassLoader().getResourceAsStream(config_path)) {
            if(in == null){
                throw new IllegalStateException("Missing config for env: " + ENV);
            }
            properties.load(in);
            LOGGER.info("Properties loaded for env {}", ENV);
        }catch (IOException e){
            throw new RuntimeException("Failed to load config for env: " + ENV, e);
        }
        return properties;
    }

    public static String getBaseUrl() {
        return PROP.getProperty("baseUrl");
    }

    public static String getEnvType() {
        return ENV;
    }

    public static String getConfigProperty(String key){
        return PROP.getProperty(key);
    }

}
