package webApp.pages;

import com.aventstack.extentreports.Status;

import driver.Utilities.DriverUtility;
import helper.Utilities.GeneralUtility;
import helper.Utilities.ExtentTestManager;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.IOException;

public class DroppablePage extends GeneralUtility {

	public static final Logger log = Logger.getLogger(DroppablePage.class.getName());
	WebDriver driver;

	@FindBy(xpath = "//li/a[@href='https://jqueryui.com/demos/']")
	WebElement verifyHomePage;

	@FindBy(xpath = "//a[contains(text(),'Droppable')]")
	WebElement droppableButton;

	@FindBy(xpath = "//h1[@class='entry-title']")
	WebElement verifyDroppabletitle;

	@FindBy(xpath = "//iframe[@src='/resources/demos/droppable/default.html']")
	WebElement dragDropFrame;

	@FindBy(id = "draggable")
	WebElement dragSource;

	@FindBy(id = "droppable")
	WebElement dragDestination;

	@FindBy(xpath = "//div[@id='droppable']/p")
	WebElement verifyDroppedSuccess;

	public DroppablePage() {
		this.driver = DriverUtility.getDriver();
		PageFactory.initElements(driver, this);

		// this.child=ExtentTestManager.getTest();
	}

	public void verifyHomePage() throws InterruptedException {
		explicitWait(driver, verifyHomePage);

		Assert.assertEquals("Demos", verifyHomePage.getText());
		testlog.log(Status.INFO, "Home Page Verified");
	}

	public void clickDroppableButton() throws InterruptedException {

		droppableButton.click();

		Assert.assertEquals("Droppable", verifyDroppabletitle.getText());
		testlog.log(Status.INFO, "Droppable button clicked successfully");

	}

	public void dragDropInDroppableInteraction() throws InterruptedException {

		driver.switchTo().frame(dragDropFrame);
		scrollTillElementView(driver, dragSource);
		Actions act = new Actions(driver);
		act.dragAndDrop(dragSource, dragDestination).build().perform();

	}

	public void verifyDragDropInDroppableInteraction() throws IOException, InterruptedException {

		Assert.assertEquals("Dropped!", verifyDroppedSuccess.getText());

		log.info("Dragged and Dropped successfully");
		testlog.log(Status.INFO, "Dragged and Dropped successfully");

		testlog.log(Status.INFO, "Dragged and Dropped - " + ExtentTestManager.getTest()
				.addScreenCaptureFromPath(getScreenshot(driver, "DraggedDropped")).toString());

	}

}
