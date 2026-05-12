package scenarioBuilder;

import io.gatling.javaapi.core.ScenarioBuilder;

import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;

public class Session {

    public static ScenarioBuilder getSession() {
        return scenario("Scenario").exec(http("Session").get("/session"));
    }
}
