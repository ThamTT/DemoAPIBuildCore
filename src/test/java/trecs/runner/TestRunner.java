package trecs.runner;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/project",
        glue = "trecs/step/definition",
        plugin = {"pretty"}
)
public class TestRunner {
}
