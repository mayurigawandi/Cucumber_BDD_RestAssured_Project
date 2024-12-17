@loginUser
Feature: To check the login feature

@validLogin
Scenario Outline: Test the login API with valid credentials 
	Given user has access to login endpoint "/users/login"
	When user enter the valid credentials
		| email   | password   |
		| <email> | <password> |
	Then user should get success response 200
	And user should get the token
	
	
	Examples: 	
		| email                   | password  |
		| practiceapi01@gmail.com | 1234567   |

@invalidLogin
Scenario Outline: Test the login API with invalid credentials 
	Given user has access to login endpoint "/users/login"
	When user enter the invalid credentials
		| email   | password   |
		| <email> | <password> |
	Then user should get error response 401

	
	Examples: 	
		| email                   | password  |
		| practiceapi01@gmail.com | 1234569   |
		| practiceapi0@gmail.com  | 1234567   |
		
		