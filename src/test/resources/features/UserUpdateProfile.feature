#Author: your.email@your.domain.com
@userUpdateProfile
Feature: User update the profile information

Background: create an auth token
    Given user has access to auth endpoint "/users/login"
    When user creates auth token with credential "practiceapi01@gmail.com" & "1234567"
    Then user should receive success response code 200
    
@updateUsingDataTable   
Scenario Outline: update the user profile information
  Given user has access to the udpate profile endpoint "/users/profile"
	When user updates the information "<name>", "<phone>", "<company>"
	Then user should get success response 200
	And user should validate updated details "<name>", "<phone>", "<company>"
Examples: 
	| name         | phone      | company       |
	| API Practice | 1234567890 | DGSL Banglore |
	
@updateUserProfileFromJSON
  Scenario Outline: To update a user profile information using JSON data
    Given user has access to the udpate profile endpoint "/users/profile"
    When user makes a request to update information "<dataKey>" from JSON file "<JSONFile>"
    Then user should get success response 200
  Examples: 
      | dataKey         | JSONFile           |
      | UserAPIPractice | updateProfile.json |