package feeder;

import config.ConfigManager;
import io.gatling.javaapi.core.FeederBuilder;

import static io.gatling.javaapi.core.CoreDsl.csv;

public class feederHelper {

    private static String env = ConfigManager.getEnvType();

    public static FeederBuilder<String> productFeeder = csv(String.format("config/%s/products.csv", env)).circular();
}