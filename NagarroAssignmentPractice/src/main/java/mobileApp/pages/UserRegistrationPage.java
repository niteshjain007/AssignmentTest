package mobileApp.pages;

import com.aventstack.extentreports.Status;

import driver.Utilities.DriverUtility;
import helper.Utilities.GeneralUtility;
import helper.Utilities.ExtentTestManager;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.time.Duration;
import static org.testng.Assert.assertEquals;

public class UserRegistrationPage extends GeneralUtility {

	public static final Logger log = Logger.getLogger(UserRegistrationPage.class.getName());

	@FindBy(id = "android:id/title")
	public WebElement homepageTitle;

	@FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView[1]")
	public WebElement verifyRegistrationpage;

	@FindBy(id = "io.selendroid.testapp:id/buttonTest")
	public WebElement verifyHomePage;

	@FindBy(id = "io.selendroid.testapp:id/inputUsername")
	public WebElement inputUsername;

	@FindBy(id = "io.selendroid.testapp:id/inputEmail")
	public WebElement inputEmail;

	@FindBy(id = "io.selendroid.testapp:id/inputPassword")
	public WebElement inputPassword;

	@FindBy(id = "io.selendroid.testapp:id/inputName")
	public WebElement defaultInputName;

	@FindBy(id = "android:id/text1")
	public WebElement defaultProgrammingLanguageg;

	@FindBy(id = "io.selendroid.testapp:id/input_adds")
	public WebElement TCCheckBox;

	@FindBy(id = "io.selendroid.testapp:id/btnRegisterUser")
	public WebElement registerUserButton;

	@FindBy(id = "io.selendroid.testapp:id/label_name_data")
	public WebElement verifyRegisteredDefaultName;

	@FindBy(id = "io.selendroid.testapp:id/label_username_data")
	public WebElement verifyRegisteredUsername;

	@FindBy(id = "io.selendroid.testapp:id/label_password_data")
	public WebElement verifyRegisteredPassword;

	@FindBy(id = "io.selendroid.testapp:id/label_email_data")
	public WebElement verifyRegisteredEmail;

	@FindBy(id = "io.selendroid.testapp:id/label_preferedProgrammingLanguage_data")
	public WebElement verifyRegisteredProgrammingLanguage;

	@FindBy(id = "io.selendroid.testapp:id/buttonRegisterUser")
	public WebElement verificationPageRegisterUserButton;

	AndroidDriver driver;

	public UserRegistrationPage() {
		this.driver = DriverUtility.getAndroidDriver();
		PageFactory.initElements(driver, this);
	}

	public void tapOnFileLogoButton() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

		driver.findElement(By.id("io.selendroid.testapp:id/startUserRegistration")).click();

		Thread.sleep(3000);
		driver.hideKeyboard();

		assertEquals("selendroid-test-app", homepageTitle.getText());
		testlog.log(Status.PASS, "selendroid-test-app :: successfully verified as homepageTitle");
	}

	public void verifyRegistrationPageText(String textToVerify) {
		assertEquals("Welcome to register a new User", verifyRegistrationpage.getText());
		testlog.log(Status.PASS, "Welcome to register a new User :: successfully verified");
	}

	public void verifyPrePopulatedName() {
		assertEquals("Mr. Burns", defaultInputName.getText());
		testlog.log(Status.PASS, "Default Input Name is Mr. Burns");
	}

	public void verifyPreSelectedProgrammingLanguage() {
		assertEquals("Ruby", defaultProgrammingLanguageg.getText());
		testlog.log(Status.PASS, "Default Programming Language is Ruby");
	}

	public void setFieldInUserRegistration(String name, String password, String emailID) {

		inputUsername.sendKeys(name);
		inputEmail.sendKeys(emailID);
		inputPassword.sendKeys(password);

		testlog.log(Status.INFO, "Name is - " + name + ", Email ID - " + emailID + ", paassword - " + password);

	}

	public void acceptTnC() throws IOException {

		TCCheckBox.click();
		testlog.log(Status.INFO, "Successfully checked TnC ");

		testlog.log(Status.INFO, "File logo Form data - " + ExtentTestManager.getTest()
				.addScreenCaptureFromPath(getScreenshot(driver, "FileLogoFormData")).toString());

	}

	public void tapOnRegisterUser() {
		registerUserButton.click();
		testlog.log(Status.INFO, "Successfully taped on Register User button");

	}

	public void verifyRegistrationDetails(String name, String password, String emailID) throws IOException {
		// Verify registered user
		assertEquals("Mr. Burns", verifyRegisteredDefaultName.getText());
		assertEquals(name, verifyRegisteredUsername.getText());
		assertEquals(password, verifyRegisteredPassword.getText());
		assertEquals(emailID, verifyRegisteredEmail.getText());
		assertEquals("Ruby", verifyRegisteredProgrammingLanguage.getText());

		testlog.log(Status.PASS,
				"Successfully verified Default name, username, password, email ID, programming language");

		testlog.log(Status.INFO, "File logo item added - " + ExtentTestManager.getTest()
				.addScreenCaptureFromPath(getScreenshot(driver, "FileLogoData")).toString());

	}

	public void tapOnRegisterUserButton() {
		verificationPageRegisterUserButton.click();
		testlog.log(Status.INFO, "Successfully taped on Register User button");
	}

	public void verifyNavigationToHomeScreen() {

		assertEquals("EN Button", verifyHomePage.getText());
		testlog.log(Status.PASS, "Verified homepage and text verified is - " + verifyHomePage.getText());

	}

}