package mobileApp.pages;

import com.aventstack.extentreports.Status;

import driver.Utilities.DriverUtility;
import helper.Utilities.GeneralUtility;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HomePage extends GeneralUtility {

	public static final Logger log = Logger.getLogger(HomePage.class.getName());
	
	@FindBy(id = "io.selendroid.testapp:id/buttonStartWebview")
	public WebElement chromeLogoIcon;
	
	@FindBy(id= "io.selendroid.testapp:id/startUserRegistration")
	public WebElement registrationIcon;
	
	@FindBy(id= "io.selendroid.testapp:id/my_text_field")
	public WebElement textField;
	
	@FindBy(id= "io.selendroid.testapp:id/topLevelElementTest")
	public WebElement displayAndbocusBtn;
	
	
	
	@FindBy(id = "android:id/title")
	WebElement homepageTitle;

	@FindBy(id = "io.selendroid.testapp:id/buttonTest")
	WebElement enButton;

	@FindBy(id = "android:id/button2")
	WebElement EndActivityNoButton;

	AndroidDriver driver ;
	public HomePage() {
		this.driver = DriverUtility.getAndroidDriver();
		PageFactory.initElements(driver, this);
	}


	
	public void verifyHomePageTitle()
	{
		try {
			if (homepageTitle.getText().equals("selendroid-test-app")) {
				testlog.log(Status.PASS, "Home Page Title is :: selendroid-test-app ");
				log.info("Homepage title is - " + homepageTitle.getText());
			} else {
				testlog.log(Status.FAIL, "Home Page Title is not :: selendroid-test-app but it found to be  " + homepageTitle.getText());
			}
		}
		catch (Exception e)
		{
			testlog.log(Status.FAIL, "Exception occurred while accessing home page title <textarea> "+e+"</textarea>");
		}
	}

	


	public void verifyPresenceOfHomePageElements() {
		if(registrationIcon.isDisplayed()
				&&
				chromeLogoIcon.isDisplayed()
				&&
				textField.isDisplayed()
				&&
				displayAndbocusBtn.isDisplayed())
		{
			Assert.assertTrue(true);
			testlog.log(Status.INFO," home page elements looking good  ");
		}
		else {
			testlog.log(Status.FAIL,"home page lements not displayed ");
		}
		
	}

	
	public void tapOnENButton() {

		enButton.click();
		testlog.log(Status.INFO,"Tapped successfully on EN button ");
	}
	public void selectOptionAsNono()
	{

		EndActivityNoButton.click();
		testlog.log(Status.INFO,"Selected EN Option as NO ");


	}
}
