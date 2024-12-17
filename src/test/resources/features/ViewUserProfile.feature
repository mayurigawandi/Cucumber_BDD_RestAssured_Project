@viewUserProfile
Feature: To retrieve the user profile 

Background: create an auth token
    Given user has access to auth endpoint "/users/login"
    When user creates auth token with credential "practiceapi01@gmail.com" & "1234567"
    Then user should receive success response code 200
    
@viewUserWithValidAuth
Scenario Outline: Test the view user details API with valid authorization 
		Given user has access to retrieve user profile endpoint "/users/profile"
		When user enter the valid auth token 
		Then user should get success response for get profile 200
		And user profile details are retrieved 
		| id   | name   | email   |
		| <id> | <name> | <email> |
		Examples:
		| id                       | name         | email                   |
		| 674d3c7718b6cc0152407e14 | API Practice | practiceapi01@gmail.com |