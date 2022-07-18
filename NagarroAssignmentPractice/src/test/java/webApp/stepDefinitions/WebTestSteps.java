package webApp.stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import webApp.pages.ControlgroupPage;
import webApp.pages.DroppablePage;
import webApp.pages.SelectablePage;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import driver.Utilities.DriverUtility;
import helper.Utilities.GeneralUtility;
import helper.Utilities.ExtentTestManager;
import helper.Utilities.PropertyUtility;

import java.util.ArrayList;
import java.util.List;

public class WebTestSteps extends GeneralUtility {

	public String arg_environment = System.getProperty("testType");
	public static final Logger log = Logger.getLogger(WebTestSteps.class.getName());

    Scenario scenario;
    WebDriver driver;
    DroppablePage droppablePage;
    SelectablePage selectablePage;
    ControlgroupPage controlgroupPage;
    @Before
    public void before(Scenario scenario) {
    	if(arg_environment.equals("web"))
    	{
    
    	this.scenario = scenario;
        DriverUtility.setDriver();
        this.driver = DriverUtility.getDriver();
        droppablePage = new DroppablePage();
        selectablePage = new SelectablePage();
        controlgroupPage = new ControlgroupPage();
    	}
    }
    
    @After
    public void after() {
    	if(arg_environment.equals("web"))
    	{
    
    	DriverUtility.closeDriver();
        ExtentTestManager.endTest();
    	}
    }

	  @Given("User Navigates to website") 
	  public void User_Navigates_to_website()
	  { 
		  try
		  {
			  testlog = ExtentTestManager.startTest(scenario.getName(), "");
	  driver.get(PropertyUtility.readConfig("jqueryURI"));
	  droppablePage.verifyHomePage();
	  } catch (Exception e) 
		  { log.error(e);
	  e.printStackTrace();
	  }
		  }
	 
    @When("User selects option {string} from left menu under Interactions")
    public void user_selects_option_from_left_menu_under_Interactions(String string) {
        try {
            if(string.equalsIgnoreCase("Droppable"))
            droppablePage.clickDroppableButton();
            else if (string.equalsIgnoreCase("Selectable")) {
                selectablePage.clickSelectableButton();
                
            } else if (string.equalsIgnoreCase("Controlgroup")) {
                controlgroupPage.clickOnControlGroupButton();
            }
        }
        catch (Exception e)
        {
            log.error(e);
            e.printStackTrace();
        }
    }
    @When("User Drag Drage me to my target to ˜Drop here field")
    public void User_Drag_Drage_me_to_my_target_to_Drop_Here() {
        try {

            droppablePage.dragDropInDroppableInteraction();
        }
        catch (Exception e)
        {
            log.error(e);
            e.printStackTrace();
        }
    }

        @Then("Drop here field should contain dragged item")
    public void Drop_here_field_should_contain_dragged_item() {
        try {

            droppablePage.verifyDragDropInDroppableInteraction();
        }
        catch (Exception e)
        {
            log.error(e);
            e.printStackTrace();
        }
    }
    
    @When("User Selects items <Item>")
    public void user_selects_items(DataTable dataTable) {
        try {
            List<List<String>> items = dataTable.cells();
            String itemList = items.get(0).get(0);
            for (int i = 1; i < items.size(); i++) {
                itemList = itemList + "," + items.get(i).get(0);
            }
            selectablePage.selectItemTab(itemList);
        }
        catch(Exception e)
        {
            log.error(e);
            e.printStackTrace();
        }


    }
    @Then("All specified item <Item> must get selected")
    public void all_specified_item_must_get_selected(DataTable dataTable) {
        try {
        	List<String> items= new ArrayList<String>();
        	items = getItesmInList(dataTable);
            Assert.assertTrue(selectablePage.verifyThatItemsAreSelected(items));
        }
        catch(Exception e)
        {
            log.error(e);
            e.printStackTrace();
        }
    }
    private List<String> getItesmInList(DataTable dataTable) {
    	List<String> mylist = new ArrayList<>();
    	try {
             List<List<String>> items = dataTable.cells();
             
             for (int i = 0; i < items.size(); i++) {
            	 mylist.add(items.get(i).get(0));
             }
             } catch(Exception e)
         {
                 log.error(e);
                 e.printStackTrace();
             }
    	 
    	 return mylist;
    }

	@When("User Book Car with details {string},{string},{string}")
    public void user_book_car_with_details(String carType, String transmissionType, String numberOfCar) {
        try {
            controlgroupPage.controlgroupTab(carType,transmissionType,numberOfCar);
        }
        catch(Exception e)
        {
            log.error(e);
            e.printStackTrace();
        }
    }

}
