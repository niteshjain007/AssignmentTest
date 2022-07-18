#this feature file contains test scenarios related to selendroid app check

@AndroidAppTest

Feature: 

 Scenario: Test Home Page Title
  
    Given Launch application
    When user is at home page
    Then verify the home page title
    And verify other elements on screem

  Scenario: Test EN Button and verify home page title
    Given Launch application
    When user is at home page
    And Tap on EN button
    And Select option as nono
    Then verify the home page title

  
  Scenario Outline: Test Chrome Logo Button and verify prefered car details
    Given Launch application
    When user is at home page
    And tap on Chrome logo button
    Then verify the text "Hello, can you please tell me your name?"
    When user enters its "<userName>" in textbox
    And selects prefered car as "<preferredCarName>"
    And taps on send me your name
    Then verify the hello text "This is my way of saying hello"
    And verify entered "<userName>" and selected "<userName>"
    And click on link "here"
    And verify default car "<defaultCarName>" name 

    Examples:
      | userName   		  | preferredCarName  | defaultCarName	|
      | testUserName		| Mercedes					| Volvo						|

  
  Scenario Outline: Check File Logo and verify registration steps
    Given Launch application
    When user is at home page
    And tap on File logo button
    Then verify the text on registration page as "Welcome to register a new User"
    And Check if the Name eld is pre-populated with "Mr. Burns"
    And Check if "Ruby" is selected as Programming Language by default
    When Fill in the fields with new values as "<name>" "<email>" and "<password>"
    And Select 'I accept adds'
    And Tap on Register User verify
    Then Verify the user details on the next screen as  "<name>" "<email>" and "<password>"
    When Tap on Register User link
    Then Check if the screen navigates to the home screen.
    Examples:
      | name    | email                     | password |
      | testName| test@test.com							| test123	 |

  
  Scenario: Test Progress bar and verify registration page element
    Given Launch application
    When user is at home page
    And Tap on Show Progress Bar Button
    Then Wait for the loader to disappear
    And Verify the elements on the user registration on screen

  
  Scenario: Test Toast and verify its text
    Given Launch application
    When user is at home page
    And Tap on Display Toast
    Then Verify the toast text

  
  Scenario: Test Popup and dismiss it
    Given Launch application
    When user is at home page
    And Tap on popup
    Then dismiss the popup

  
  Scenario: Test Press Unhandled Exception
    Given Launch application
    When user is at home page
    And Tap on Press to throw unhandled exception
    Then verify the home page title

  
  Scenario: Test Type Unhandled Exception
   Given Launch application
    When user is at home page
    And Tap on Type to throw unhandled exception
    Then verify the home page title