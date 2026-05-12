package scenarios;

import config.ConfigManager;
import config.Constants;
import io.gatling.core.structure.ScenarioBuilder;
import io.gatling.javaapi.core.Assertion;
import io.gatling.javaapi.core.PopulationBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.*;

import java.util.List;
import java.util.Optional;

import static io.gatling.javaapi.core.CoreDsl.global;
import static io.gatling.javaapi.http.HttpDsl.*;

public class BaseSimulations extends Simulation {

    private static String testType = System.getProperty(Constants.TEST_TYPE, ConfigManager.getConfigProperty("testType"));
    // Define HTTP configuration
    protected HttpProtocolBuilder httpProtocol =
            http.baseUrl(ConfigManager.getBaseUrl())
                    .acceptHeader("application/json")
                    .userAgentHeader(
                            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36");

    protected static final HttpProtocolBuilder withAuthenticationHeader(HttpProtocolBuilder protocolBuilder) {
        return protocolBuilder.header(
                "Authorization",
                session -> Optional.ofNullable(session.getString("AccessToken")).orElse(""));
    }

    static final List<Assertion> assertions = List.of(
            global().responseTime().percentile(90.0).lt(500),
            global().failedRequests().percent().lt(5.0));

//    static final List<Assertion> getAssertions() {
//        return switch (testType) {
//            case "capacity", "soak", "stress", "breakpoint", "ramp-hold" -> assertions;
//            case "smoke" -> List.of(global().failedRequests().count().lt(1L));
//            default -> assertions;
//        };
//    }

    // Define different load injection profiles
//    static final PopulationBuilder injectionProfile(ScenarioBuilder scn) {
//        return switch (testType) {
//            case "capacity" -> scn.injectOpen(
//                    incrementUsersPerSec(1)
//                            .times(4)
//                            .eachLevelLasting(10)
//                            .separatedByRampsLasting(4)
//                            .startingFrom(10));
//            case "soak" -> scn.injectOpen(constantUsersPerSec(1).during(180));
//            case "stress" -> scn.injectOpen(stressPeakUsers(200).during(20));
//            case "breakpoint" -> scn.injectOpen(rampUsers(300).during(120));
//            case "ramp-hold" -> scn.injectOpen(
//                    rampUsersPerSec(0).to(20).during(30),
//                    constantUsersPerSec(20).during(60));
//            case "smoke" -> scn.injectOpen(atOnceUsers(1));
//            default -> scn.injectOpen(atOnceUsers(1));
//        };
//   }

}
