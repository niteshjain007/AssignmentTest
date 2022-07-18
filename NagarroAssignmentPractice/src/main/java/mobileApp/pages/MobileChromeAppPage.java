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
import org.testng.Assert;

import static org.testng.Assert.assertEquals;

import java.io.IOException;


public class MobileChromeAppPage extends GeneralUtility {

	public static final Logger log = Logger.getLogger(MobileChromeAppPage.class.getName());

	@FindBy(id = "android:id/title")
	public WebElement homepageAppTitle;

	@FindBy(id = "io.selendroid.testapp:id/buttonStartWebview")
	public WebElement chromeLogoButton;

	@FindBy(xpath = "//android.view.View[@package='io.selendroid.testapp']")
	public WebElement verifyFormPage;

	@FindBy(xpath = "//android.widget.EditText[@package='io.selendroid.testapp']")
	public WebElement inputNameTxt;

	@FindBy(xpath = "//android.widget.Spinner[@package='io.selendroid.testapp'][@scrollable='false']")
	public WebElement selectCarButton;

	@FindBy(xpath = "//android.view.View[@index='0']")
	public WebElement verifyFormPageHeading;

	@FindBy(xpath = "//android.view.View[@index='3']")
	public WebElement verifyAddedName;

	@FindBy(xpath = "//android.view.View[@index='5']")
	public WebElement verifyAddedCar;

	@FindBy(xpath = "//android.view.View[@index='9']")
	public WebElement StartAgainButton;
	AndroidDriver driver;

	public MobileChromeAppPage() {
		this.driver = DriverUtility.getAndroidDriver();
		PageFactory.initElements(driver, this);
	}

	
	public void tapOnChromeLogoButton()
	{
		explicitWait(driver, chromeLogoButton);

		chromeLogoButton.click();
		testlog.log(Status.INFO, "chrome logo tap completed !! ");
	}

	public void verifyTheTextOnChromLogoClick(String textToVerify)
	{
		if(driver.findElement(By.xpath("//android.view.View[@content-desc=\"Hello, can you please tell me your name?\"]")).isDisplayed()) {
			testlog.log(Status.PASS, textToVerify + " :: verification done !! ");
			Assert.assertTrue(true);
		}
		else {
			testlog.log(Status.FAIL, textToVerify + " is not same as  "+verifyFormPage.getText());
			Assert.assertTrue(false);
		}
	}

	public void specifyNameInTextBox(String name)
	{
		inputNameTxt.click();
		inputNameTxt.clear();
		inputNameTxt.sendKeys(name);
		testlog.log(Status.INFO, "specified name is :" + name);
	}

	public void selectPrefferedCar(String carName) throws IOException {

		selectCarButton.click();

		String carValue = "//android.widget.CheckedTextView[@package='io.selendroid.testapp'][@text='" + carName + "']";
		driver.findElement(By.xpath(carValue)).click();
		testlog.log(Status.INFO, "specified car name is  " + carName);
		testlog.log(Status.INFO, "selected item is :" + ExtentTestManager.getTest()
				.addScreenCaptureFromPath(getScreenshot(driver, "ItemSelected")).toString());

	}

	public void tapOnSendMeYourName()
	{
		driver.findElement(By.xpath("//android.widget.Button[@text='Send me your name!']")).click();

	}

	public void verifyTheTextMyWaySayingHello(String textToVerify)
	{
		waitUntilInvisibilityAndroid(verifyFormPageHeading,driver);

		//verifyFormPageHeading = driver.findElement(By.xpath("//android.view.View[@text='"+textToVerify+"']"));
		if(textToVerify.equals(verifyFormPageHeading.getText())) {
			testlog.log(Status.PASS, textToVerify + " :: test verficatiomn done. ");
			Assert.assertTrue(true);
		}
		else {
			testlog.log(Status.FAIL, textToVerify + " is not same as - "+verifyFormPageHeading.getText());
			Assert.assertTrue(false);
		}

	}


	public void verifyDefaulCar() {
		
		if(driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Volvo\"]")).isDisplayed())
		{
			testlog.log(Status.INFO, "default car volvo is working fine");
			testlog.log(Status.PASS, "verifyDefaulCar done!!");
		}
		else
		{
			testlog.log(Status.FAIL, "default car volso not displayed");
				Assert.assertTrue(false);
		}
	}

	public void verifyNameAndCarName(String name, String carName) throws IOException {
		String expextedName = "\"" + name + "\"";

		// assert added name
		verifyAddedName = driver.findElement(By.xpath("//android.widget.TextView[@text='\""+name+"\"']"));
		assertEquals(expextedName, verifyAddedName.getText());

		// Assert added car
		carName = carName.toLowerCase();
		String expextedCar = "\"" + carName + "\"";
		verifyAddedCar =  driver.findElement(By.xpath("//android.widget.TextView[@text='\""+carName+"\"']"));
		assertEquals(expextedCar, verifyAddedCar.getText());

		testlog.log(Status.INFO, "data vedifcaiton working fine");
		testlog.log(Status.PASS, "verifyNameAndCarName");

	}
	public void clickLinkHere()  {


		StartAgainButton.click();
		testlog.log(Status.INFO, "Clicked link <here>");

	}


}