package project.apis.webservices.acceptancetests;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(tags = "",
        plugin = "pretty",
        features = "src/test/resources/features",
        glue = "canales.apis.webservices.steps")
public class Runner {
}
