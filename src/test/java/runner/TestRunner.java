// To execute test case file.
package runner;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
//
@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/featureFiles/BasicScenarios.feature"},
        glue = {"step.definitions"},
        plugin = {"json:report/cucumber.json"}
)
public class TestRunner {
}