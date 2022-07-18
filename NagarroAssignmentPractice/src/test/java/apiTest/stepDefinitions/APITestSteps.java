package apiTest.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import helper.Utilities.GeneralUtility;
import helper.Utilities.ExtentTestManager;
import helper.Utilities.RegresAPIPage;

public class APITestSteps extends GeneralUtility {

    public static final Logger log = Logger.getLogger(APITestSteps.class.getName());
    public String arg_environment = System.getProperty("testType");
    Scenario scenario;
    RegresAPIPage regresAPIPage;

    @Before
    public void beforeAPITest(Scenario scenario) {
    	if(arg_environment.equals("api"))
    	{
    	this.scenario = scenario;
    	testlog = ExtentTestManager.startTest(scenario.getName(), "");
        regresAPIPage = new RegresAPIPage();
    	}
    }

    @Given("The API base URI is {string}")
    public void the_API_base_URI_is(String URL)
    {
    	regresAPIPage.setBaseURI(URL);
    }
    @When("Request is to GET  users details on page{string}")
    public void request_is_to_GET_user_details_onPage(String pageNumber) {
        try {
            regresAPIPage.getUsers(pageNumber);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Then("Verify if status code is {int}")
    public void verify_if_status_code_is(int responseCode)
    {
    	try {
            regresAPIPage.verifyStatusCodeOfGetUser(responseCode);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    	
    }
    @Then("Verify if the status code is {int}")
    public void Verify_if_the_status_code(Integer responseCode) {
        try {
            regresAPIPage.verifyStatusCodeOfGetUser(responseCode);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Then("Verify the value of property {string} for userID {string} is {string}")
    public void Verify_the_value_of_property(String field, String id, String value) {
        try {
            regresAPIPage.verifyUser(field, id, value);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
    @Given("Request is to POST a user with {string} and {string}")
    public void Request_is_to_POST_a_user(String name, String job) {
    regresAPIPage.createUser(name,job);
    }
    @Then("Verify if id is generated")
    public void Verify_if_id_is_generated() {
       regresAPIPage.verifyID();
    }
    @Then("Verify the Response JSON Schema")
    public void Verify_the_Response_JSON_Schema() {
       regresAPIPage.validateJSONSchemaForPostUser();
    }


    @After
    public void after() {
    	if(arg_environment.equals("api"))
    	{
    	
      ExtentTestManager.endTest();
    	}
    }
   
 
}