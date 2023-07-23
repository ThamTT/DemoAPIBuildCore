package trecs;

import io.cucumber.java.Scenario;
import net.serenitybdd.core.PendingStepException;
import net.serenitybdd.core.annotations.events.BeforeScenario;

public class Hooks {
  @BeforeScenario
  public void beforeScenario(Scenario scenario) {
    if (scenario.getSourceTagNames().contains("@manual")) {
      throw new PendingStepException("Manual test");
    }
  }
}
