@WebAppTest
Feature: Check action drag select and multiselect

  Scenario: Test Drag and Drop
    Given User Navigates to website
    When User selects option "Droppable" from left menu under Interactions
    And User Drag Drage me to my target to ˜Drop here field
    Then Drop here field should contain dragged item


  Scenario: Test multiSelect action
 		Given User Navigates to website
    When User selects option "Selectable" from left menu under Interactions
    And User Selects items <Item>
    |Item 1|
    |Item 3|
    |Item 7|
    Then All specified item <Item> must get selected
		|Item 1|
    |Item 3|
    |Item 7|
 
  Scenario Outline: Test control group options
    Given User Navigates to website
    When User selects option "Controlgroup" from left menu under Interactions
    And User Book Car with details "<carType>","<transmissionType>","<NumberOfCar>"
  Examples:
    | carType | transmissionType | NumberOfCar |
    |SUV      |Automatic         | 2           |
    |Truck    | Standard         | 1           |

