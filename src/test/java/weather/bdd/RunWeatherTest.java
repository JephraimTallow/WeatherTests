package weather.bdd;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
 
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = { "pretty", "html:target/bdd"},
        snippets = SnippetType.CAMELCASE,
        features = "src/test/resources/bdd/weather.feature"
)

public class RunWeatherTest {

}
