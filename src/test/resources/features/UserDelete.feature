@userDelete
Feature: Log out and delete user account

Background: create an auth token
    Given user has access to auth endpoint "/users/login"
    When user creates auth token with credential "practiceapi01@gmail.com" & "1234567"
    Then user should receive success response code 200

  Scenario: Check the log out user account
	Given user has access to logout endpoint "/users/logout"
	When user sends the request to logout
	Then user should get success response 200
	And user should get logout
	


 