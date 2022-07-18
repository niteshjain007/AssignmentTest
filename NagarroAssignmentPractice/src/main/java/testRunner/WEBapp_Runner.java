package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(


        plugin = {
                "pretty",
                "html:build/reports/CucumberSpecificReport/Cucumbertestreport.html"
                ,"json:build/reports/CucumberSpecificReport/CucumberJson/cucumber.json"
        },
        features = {"src/test/resources/features/webApp"},
        glue ={"src/test/java/webApp/stepDefinitions"},
        tags = "@WebAppTest",
        dryRun = false, monochrome = true


)

public class WEBapp_Runner {
}
