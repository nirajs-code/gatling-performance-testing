package scenarios;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

public class SessionsSimulation extends BaseSimulations {

  // Load VU count from system properties

  private static final int vu = Integer.getInteger("vu", 1);

  private static final Assertion assertion = global().failedRequests().count().lt(1L);

  // Define injection profile and execute the test
  ScenarioBuilder scenario = scenarioBuilder.Session.getSession();

  {
    setUp(scenario.injectOpen(atOnceUsers(vu))).assertions(assertion).protocols(httpProtocol);
  }
}
