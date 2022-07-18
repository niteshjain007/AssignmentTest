package webApp.pages;

import com.aventstack.extentreports.Status;

import driver.Utilities.DriverUtility;
import helper.Utilities.GeneralUtility;
import helper.Utilities.ExtentTestManager;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SelectablePage extends GeneralUtility {

	public static final Logger log = Logger.getLogger(SelectablePage.class.getName());
	WebDriver driver;

	@FindBy(xpath = "//li/a[@href='https://jqueryui.com/demos/']")
	WebElement verifyHomePage;

	@FindBy(xpath = "//a[contains(text(),'Selectable')]")
	WebElement selectableButton;

	@FindBy(xpath = "//h1[@class='entry-title']")
	WebElement verifySelectabletitle;

	@FindBy(xpath = "//iframe[@src='/resources/demos/selectable/default.html']")
	WebElement selectFrame;

	@FindBy(xpath = "//ol[@id='selectable']/li")
	List<WebElement> listOfItems;

	@FindBy(id = "selectable")
	WebElement itemView;

	public SelectablePage() {
		this.driver = DriverUtility.getDriver();
		;
		PageFactory.initElements(driver, this);
	}

	public void clickSelectableButton() {
		selectableButton.click();

		Assert.assertEquals("Selectable", verifySelectabletitle.getText());
		testlog.log(Status.INFO, "Selectable Button Clicked");
	}

	public boolean verifyThatItemsAreSelected(List<String> items) throws IOException {

		driver.switchTo().defaultContent();
		scrollTillElementView(driver, driver.findElement(By.cssSelector(".demo-frame")));
		driver.switchTo().frame(driver.findElement(By.cssSelector(".demo-frame")));
		scrollTillElementView(driver, itemView);
		boolean isItemSelected = true;
		List<WebElement> itemElement = driver.findElements(By.cssSelector(".ui-selectable li"));
		for (WebElement option : itemElement) {

			for (int i = 0; i < items.size(); i++) {
				if (option.getText().equals(items.get(i))) {
					if (option.getAttribute("class").contains("ui-selected") != true) {
						isItemSelected = false;
					}
				}
			}
		}

		if (isItemSelected) {
			log.info("Items selected successfully");
		}
		return isItemSelected;
	}

	public void selectItemTab(String itemsToSelect) throws InterruptedException, IOException {

		explicitWait(driver, selectFrame);
		driver.switchTo().frame(selectFrame);
		scrollTillElementView(driver, itemView);

		String[] inputItemList = itemsToSelect.split(",");
		List<WebElement> webElementList = new ArrayList<>();
		for (WebElement option : listOfItems) {

			for (int i = 0; i < inputItemList.length; i++) {
				if (option.getText().equals(inputItemList[i].toString())) {
					webElementList.add(option);
				}
			}
		}

		for (int i = 0; i < webElementList.size(); i++) {
			Actions builder = new Actions(driver);
			builder.keyDown(Keys.CONTROL).click(webElementList.get(i)).keyUp(Keys.CONTROL);
			Action selectMultiple = builder.build();
			selectMultiple.perform();
		}

	}

}
