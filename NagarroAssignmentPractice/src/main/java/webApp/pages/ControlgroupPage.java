package webApp.pages;

import com.aventstack.extentreports.Status;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import driver.Utilities.DriverUtility;
import helper.Utilities.GeneralUtility;
import helper.Utilities.ExtentTestManager;


import java.io.IOException;

public class ControlgroupPage extends GeneralUtility {

	public static final Logger log = Logger.getLogger(ControlgroupPage.class.getName());

	
	@FindBy(id = "horizontal-spinner")
	WebElement horizontalNumberOfCarsInput;

	@FindBy(xpath = "//div[@class='controlgroup-vertical ui-controlgroup ui-controlgroup-vertical']//span[@id='ui-id-8-button']")
	WebElement compactCarDropDownVerticalButton;

	@FindBy(id = "vertical-spinner")
	WebElement verticalNumberOfCarsInput;

	@FindBy(xpath = "//label[@for='insurance'][@class='ui-button ui-widget ui-checkboxradio-label ui-controlgroup-item']")
	WebElement HorizontalInsuranceCheckbox;

	@FindBy(xpath = "//label[@for='insurance-v'][@class='ui-button ui-widget ui-checkboxradio-label ui-controlgroup-item']")
	WebElement VerticalInsuranceCheckbox;

	@FindBy(id = "book")
	WebElement bookButton;

	
	@FindBy(xpath = "//li/a[@href='https://jqueryui.com/demos/']")
	WebElement verifyHomepage;

	@FindBy(xpath = "//a[contains(text(),'Controlgroup')]")
	WebElement controlgroupButton;

	@FindBy(xpath = "//h1[@class='entry-title']")
	WebElement verifyControlgrouptitle;

	@FindBy(xpath = "//iframe[@src='/resources/demos/controlgroup/default.html']")
	WebElement selectControlgroupFrame;

	@FindBy(id = "car-type-button")
	WebElement compactCarDropDownButton;
	
	
	WebDriver driver;
	public ControlgroupPage() {
		this.driver =  DriverUtility.getDriver();;
		PageFactory.initElements(driver, this);
	}

	public void clickOnControlGroupButton()
	{
		controlgroupButton.click();

		Assert.assertEquals("Controlgroup", verifyControlgrouptitle.getText());
	}
	
	public void controlgroupTab(String carType, String transmissionType, String numberofCar) throws InterruptedException, IOException {


		driver.switchTo().frame(selectControlgroupFrame);

		scrollTillElementView(driver, compactCarDropDownButton);

		compactCarDropDownButton.click();

		String selectCompactCar = "//ul[@aria-labelledby='car-type-button']/li/div[contains(text(),'" + carType
				+ "')]";
		driver.findElement(By.xpath(selectCompactCar)).click();

		String transmissionType1 = "//div[@class='controlgroup ui-controlgroup ui-controlgroup-horizontal ui-helper-clearfix']//label[@class='ui-button ui-widget ui-checkboxradio-radio-label ui-checkboxradio-label ui-controlgroup-item'][contains(text(),'"
				+ transmissionType + "')]";
		driver.findElement(By.xpath(transmissionType1)).click();

		if (isClickableWeb(HorizontalInsuranceCheckbox, driver)) {
			HorizontalInsuranceCheckbox.click();
		} else {
			log.info("Checkbox already clicked");
		}

		horizontalNumberOfCarsInput.sendKeys(numberofCar);

		compactCarDropDownVerticalButton.click();

		bookButton.click();

		testlog.log(Status.INFO, "Contro group url - " + driver.getCurrentUrl());
		log.info("Current URL - " + driver.getCurrentUrl());

		testlog.log(Status.INFO, "Control group - " + ExtentTestManager.getTest()
				.addScreenCaptureFromPath(getScreenshot(driver, "ControlGroup")).toString());

	}

}
