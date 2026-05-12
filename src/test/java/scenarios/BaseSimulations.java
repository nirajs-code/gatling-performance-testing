package scenarios;

import config.ConfigManager;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class BaseSimulations extends Simulation {

    // Define HTTP configuration
    protected HttpProtocolBuilder httpProtocol =
            http.baseUrl(ConfigManager.getBaseUrl())
                    .acceptHeader("application/json")
                    .userAgentHeader(
                            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36");


}
