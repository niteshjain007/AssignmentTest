@apiTest
Feature: Validate APIs for user Data
@GET
	Scenario Outline: Get user details
  	Given The API base URI is "<URI>"
    When Request is to GET  users details on page"<pageNumber>"
    Then Verify if status code is 200  
    Then Verify the value of property "<property>" for userID "<id>" is "<propValue>"  
   Examples:
     |URI							  |pageNumber |id	|property			|propValue	|
     |https://reqres.in  |2          |10 |first_name		|Byron			|

@POST  
	Scenario Outline: create a new user
  	Given The API base URI is "<URI>" 
    When Request is to POST a user with "<name>" and "<job>"
    Then Verify if the status code is 201
    And Verify if id is generated
    And Verify the Response JSON Schema
    Examples:
      |URI								|name       |  job |
      |https://reqres.in/|Bryant     | BA   |

			