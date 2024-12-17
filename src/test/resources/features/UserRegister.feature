@userRegistration
Feature: To create the new user account

@validUserRegister
Scenario Outline: To create the user account with valid details 
		Given user has access to endpoint "/users/register"
		When user makes request with valid user details
			| name   | email   | password   |
			| <name> | <email> | <password> |
		Then user should get the success response code 409
		And user should get the user id 

		Examples:
			| name         | email                   | password |
			| practice API | practiceapi01@gmail.com | 1234567  |
		
@invalidUserRegister
Scenario Outline: To create the user account with invalid details
		Given user has access to endpoint "/users/register"
		When user makes request with invalid user details 
	  	| name   | email   | password   |
		  | <name> | <email> | <password> |
		Then user should get the error response code 400
		
		Examples:
		  | name  | email                 | password |
		  | user1 | usertemp1@gmailcom    | 123456   |
		  | use   | usertemp66@gmail.com  | 1234567  |
		  | user1 | usertemp@gmail.com    | 12345    |
		

