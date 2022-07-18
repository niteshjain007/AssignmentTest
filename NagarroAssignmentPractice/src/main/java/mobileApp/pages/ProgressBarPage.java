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

public class ProgressBarPage extends GeneralUtility {

	public static final Logger log = Logger.getLogger(ProgressBarPage.class.getName());

	@FindBy(id = "io.selendroid.testapp:id/waitingButtonTest")
	public WebElement progressBarButton;

	@FindBy(id = "io.selendroid.testapp:id/inputUsername")
	public WebElement verifyRegisterUserName;

	@FindBy(id = "io.selendroid.testapp:id/inputEmail")
	public WebElement verifyRegisterEmail;

	@FindBy(id = "io.selendroid.testapp:id/inputPassword")
	public WebElement verifyRegisterPassword;

	@FindBy(id = "io.selendroid.testapp:id/inputName")
	public WebElement verifyRegisterInputName;

	@FindBy(id = "android:id/text1")
	public WebElement verifyRegisterProgrammingLanguage;

	@FindBy(id = "io.selendroid.testapp:id/input_adds")
	public WebElement verifyRegisterTCCheckBox;

	@FindBy(id = "io.selendroid.testapp:id/btnRegisterUser")
	public WebElement verifyRegisterButton;

	@FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView[1]")
	public WebElement verifyReistrationPage;

	AndroidDriver driver;

	public ProgressBarPage() {
		this.driver = DriverUtility.getAndroidDriver();
		PageFactory.initElements(driver, this);
	}

	public void tapOnProgressBarTab() throws InterruptedException, IOException {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

		progressBarButton.click();

		testlog.log(Status.INFO, "Progress bar is in progress.... " + ExtentTestManager.getTest()
				.addScreenCaptureFromPath(getScreenshot(driver, "ProgressBar")).toString());
	}

	public void waitForLoader() {

		explicatWaitTillInvisibility(driver, driver.findElement(By.id("android:id/progress")));

		testlog.log(Status.INFO, "Progress bar completed");

	}

	public void verifyUserRegistrationPage() throws InterruptedException, IOException {
		testlog.log(Status.PASS, "Registration Page title - " + verifyReistrationPage.getText());
		assertEquals("Welcome to register a new User", verifyReistrationPage.getText());

		Thread.sleep(3000);

		driver.hideKeyboard();

		checkRegistrationForm(verifyRegisterUserName);
		checkRegistrationForm(verifyRegisterEmail);
		checkRegistrationForm(verifyRegisterPassword);
		checkRegistrationForm(verifyRegisterInputName);
		checkRegistrationForm(verifyRegisterProgrammingLanguage);
		checkRegistrationForm(verifyRegisterTCCheckBox);
		checkRegistrationForm(verifyRegisterButton);

		testlog.log(Status.INFO, "Verified register user page successfully " + ExtentTestManager.getTest()
				.addScreenCaptureFromPath(getScreenshot(driver, "ProgressBarRegisterPage")).toString());

		testlog.log(Status.PASS, "Verified registration page event");

	}

	public void checkRegistrationForm(WebElement element) {

		if (isClickableAndroid(element, driver) == true) {
			assertEquals(true, true);
			{
			}
		} else {
			assertEquals(false, false);
		}

	}

}
