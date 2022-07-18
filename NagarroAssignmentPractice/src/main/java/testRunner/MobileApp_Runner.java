package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

//import io.cucumber.junit.CucumberOptions;

//import cucumber.api.CucumberOptions;

//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;
//import org.junit.runner.RunWith;



@RunWith(Cucumber.class)
@CucumberOptions(


        plugin = {
                "pretty",
                "html:build/reports/CucumberSpecificReport/Cucumbertestreport.html"
                ,"json:build/reports/CucumberSpecificReport/CucumberJson/cucumber.json"
        },
        features = {"src/test/resources/features/mobileApp"},
        glue ={"src/test/java/mobileApp/stepDefinitions"},
        tags = "@AndroidAppTest",
        dryRun = false, monochrome = true


)
public class MobileApp_Runner {
}
