package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(


        plugin = {
                "pretty",
                "html:build/reports/CucumberSpecificReport/Cucumbertestreport.html",
                "json:build/reports/CucumberSpecificReport/CucumberJson/cucumber.json"
        },
        features = {"src/test/resources/features/apiFeatures"},
        glue ={"src/test/java/apiTest/stepDefinitions"},
        tags = "@apiTest",
        dryRun = false, monochrome = true


)
public class API_Runner {
}
