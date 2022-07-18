package mobileApp.stepDefinitions;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import mobileApp.pages.MobileChromeAppPage;
import mobileApp.pages.CommonPage;
import mobileApp.pages.UserRegistrationPage;
import mobileApp.pages.HomePage;
import mobileApp.pages.ProgressBarPage;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import driver.Utilities.DriverUtility;
import helper.Utilities.GeneralUtility;
import helper.Utilities.ExtentTestManager;

public class SelendroidAppSteps extends GeneralUtility {

    public static final Logger log = Logger.getLogger(SelendroidAppSteps.class.getName());
    public String arg_environment = System.getProperty("testType");
    
    Scenario scenario;
    AndroidDriver driver;

     HomePage homePage;
    MobileChromeAppPage chromeOnMobilePage;
    UserRegistrationPage userRegistrationPage;

     ProgressBarPage progressBarPage;
     CommonPage commonPage;

     //setup the logs and driver and pages 
     
    @Before
    public void before(Scenario scenario) {
        try {
        	if(arg_environment.equals("mobileApp"))
        	{
        	String log4jConfPath = "log4j.properties";
            PropertyConfigurator.configure(log4jConfPath);
            this.scenario = scenario;
            DriverUtility.setAndroidDriver();
            this.driver = DriverUtility.getAndroidDriver();
            homePage = new HomePage();
            chromeOnMobilePage = new MobileChromeAppPage();
            userRegistrationPage = new UserRegistrationPage();
            progressBarPage = new ProgressBarPage();
            commonPage = new CommonPage();
        	}
        } catch (Exception e) {
            log.error(e);
            e.printStackTrace();
        }
    }

    @After
    public void afterMobileAppTest() {
    	if(arg_environment.equals("mobileApp"))
    	{
    		DriverUtility.closeAndroidDriver();
    		ExtentTestManager.endTest();
    	}
    }

    
    //  open the app 

    @Given("Launch application")
    public void launch_application() {
        try {
        	testlog = ExtentTestManager.startTest(scenario.getName(), "");

        } catch (Exception e) {
            log.error(e);
            e.printStackTrace();
        }
    }

   
    @When("user is at home page")
    public void user_is_at_home_page() {
     // code will come to solve any issue related to hoem page open 
    		System.out.println("hi..we are at home page");
    }

    @Then("verify the home page title")
    public void verify_the_home_page_title() {
        homePage.verifyHomePageTitle();

    }

    @Then("verify other elements on screem")
    public void verify_other_elements_on_screem()
    {
    	homePage.verifyPresenceOfHomePageElements();
    }
   
    
    // scenario#2: EN button steps
    
    @When("Tap on EN button")
    public void tap_on_EN_button() {
        homePage.tapOnENButton();
    }

    @When("Select option as nono")
    public void select_option_as_nono() {
        homePage.selectOptionAsNono();
    }

    // scenario #3 car selection on chrome page
    
    @When("tap on Chrome logo button")
    public void tap_on_chrome_logo_button() {
        try {
        	chromeOnMobilePage.tapOnChromeLogoButton();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    @Then("verify the text {string}")
    public void verify_the_text(String textToVerify) {
        try {
        	chromeOnMobilePage.verifyTheTextOnChromLogoClick(textToVerify);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }

    }

    @When("user enters its {string} in textbox")
    public void user_enters_its_in_textbox(String name) {
        try {
        	chromeOnMobilePage.specifyNameInTextBox(name);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    @When("selects prefered car as {string}")
    public void selects_prefered_car_as(String carName) {
        try {
        	chromeOnMobilePage.selectPrefferedCar(carName);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    @When("taps on send me your name")
    public void taps_on_send_me_your_name() {
        try {
        	chromeOnMobilePage.tapOnSendMeYourName();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    @Then("verify entered {string} and selected {string}")
    public void verify_entered_and_selected(String name, String carName) {
        try {
        	chromeOnMobilePage.verifyNameAndCarName(name, carName);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    @Then("verify the hello text {string}")
    public void verifyTheHelloText(String textToVerify) {
        try {
        	chromeOnMobilePage.verifyTheTextMyWaySayingHello(textToVerify);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    // scenario #4 : user registration on chrome app page 
    @When("tap on File logo button")
    public void tap_on_file_logo_button() {
        try {
        	userRegistrationPage.tapOnFileLogoButton();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    @Then("verify the text on registration page as {string}")
    public void verifyTheTextOnRegistrationPageAs(String textToVerify) {
        try {
        	userRegistrationPage.verifyRegistrationPageText(textToVerify);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    @Then("Check if the Name eld is pre-populated with {string}")
    public void check_if_the_name_eld_is_pre_populated_with(String string) {
        try {
        	userRegistrationPage.verifyPrePopulatedName();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    @Then("Check if {string} is selected as Programming Language by default")
    public void check_if_is_selected_as_programming_language_by_default(String string) {
        try {
        	userRegistrationPage.verifyPreSelectedProgrammingLanguage();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    @When("Fill in the fields with new values as {string} {string} and {string}")
    public void fill_in_the_fields_with_new_values_as_and(String name, String email, String password) {
        try {
        	userRegistrationPage.setFieldInUserRegistration(name, password, email);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    @When("Select {string}")
    public void select(String string) {
        try {
        	userRegistrationPage.acceptTnC();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    @When("Tap on Register User verify")
    public void tap_on_register_user_verify() {
        try {
        	userRegistrationPage.tapOnRegisterUser();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    @Then("Verify the user details on the next screen as  {string} {string} and {string}")
    public void verify_the_user_details_on_the_next_screen_as_and(String name, String email, String password) {
        try {
        	userRegistrationPage.verifyRegistrationDetails(name, password, email);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    @When("Tap on Register User link")
    public void tap_on_register_user_link() {
        try {
        	userRegistrationPage.tapOnRegisterUserButton();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    @Then("click on link {string}")
    public void click_on_link(String string) {
        try {
        	chromeOnMobilePage.clickLinkHere();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }

    }
    
    @Then("verify default car {string} name")
    public void verify_default_care_name(String carname)
    {
    	   try {
    		   chromeOnMobilePage.verifyDefaulCar();
           } catch (Exception e) {
               e.printStackTrace();
               log.error(e.getMessage());
           }
	
    }

    @Then("Check if the screen navigates to the home screen.")
    public void check_if_the_screen_navigates_to_the_home_screen() {
        try {
        	userRegistrationPage.verifyNavigationToHomeScreen();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }

    }
    
    
    // scenario 5 progress bar check
    @When("Tap on Show Progress Bar Button")
    public void tap_on_show_progress_bar_button() {
        try {
            progressBarPage.tapOnProgressBarTab();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    @Then("Wait for the loader to disappear")
    public void wait_for_the_loader_to_disappear() {
        try {
            progressBarPage.waitForLoader();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    @Then("Verify the elements on the user registration on screen")
    public void verify_the_elements_on_the_user_registration_on_screen() {
        try {
            progressBarPage.verifyUserRegistrationPage();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    // scenario 6 toast element and text
    
    @And("Tap on Display Toast")
    public void tapOnDisplayToast() {
        try {
            commonPage.tapOnToastButton();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    @Then("Verify the toast text")
    public void verifyTheToastText() {
        try {
            commonPage.verifyDisplaysAToast();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }
   // pop up 
    @And("Tap on popup")
    public void tapOnPopup() {
        try {
            commonPage.tapOnDispalyPopUp();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    @Then("dismiss the popup")
    public void dismissThePopup() {
        try {
            commonPage.dismissDisplaysPopup();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    // scenario - unhandled exception
    @And("Tap on Press to throw unhandled exception")
    public void tapOnPressToThrowUnhandledException() {
        try {
            commonPage.verifyUnhandledException();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

  
    @And("Tap on Type to throw unhandled exception")
    public void tapOnTypeToThrowUnhandledException() {
        try {
            commonPage.verifyTypeAndUnhandledException("test");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

  
  
}
