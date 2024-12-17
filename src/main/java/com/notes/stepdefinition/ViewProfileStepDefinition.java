package com.notes.stepdefinition;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.notes.model.CompleteUserResponse;
import com.notes.utils.ResponseValidator;
import com.notes.utils.TestContext;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ViewProfileStepDefinition {
	private static final Logger logger = LogManager.getLogger(ViewProfileStepDefinition.class);
	TestContext testcontext;

	public ViewProfileStepDefinition(TestContext testcontext) {
		this.testcontext = testcontext;
	}

	@Given("user has access to auth endpoint {string}")
	public void userHasAccessToAuthEndpoint(String authEndpoint) {
		testcontext.sessionData.put("authEndpoint", authEndpoint);
		logger.info("For generate auth token endpoint is stored in session data ");
	}

	@When("user creates auth token with credential {string} & {string}")
	public void userCreatesAuthTokenWithCredential(String email, String password) {
		testcontext.response = testcontext.requestSetup().formParam("email", email).formParam("password", password)
				.when().post(testcontext.sessionData.get("authEndpoint").toString());
		logger.info("The request details are sent to genearate the token");
	}

	@Then("user should receive success response code {int}")
	public void userShouldReceiveSuccessResponseCode(Integer statusCode) {
		assertEquals(Long.valueOf(statusCode), Long.valueOf(testcontext.response.getStatusCode()));
		assertNotNull("the token is generated", testcontext.response.jsonPath().get("data.token"));
		String token = testcontext.response.jsonPath().get("data.token");
		testcontext.sessionData.put("token", token);
		logger.info("The success response is received for token generation request");
	}

	@Given("user has access to retrieve user profile endpoint {string}")
	public void userHasAccessToRetrieveUSerProfileEndpoint(String profileEndpoint) {
		testcontext.sessionData.put("profileEndpoint", profileEndpoint);
		logger.info("The view profile details enpoint is stored in sessiondata");
	}

	@When("user enter the valid auth token")
	public void userEnterTheValidAuthToken() {
		testcontext.response = testcontext.requestSetup().header("x-auth-token", testcontext.sessionData.get("token"))
				.when().get(testcontext.sessionData.get("profileEndpoint").toString());
		logger.info("The view profile request is sent");
		CompleteUserResponse completeUserResponse = ResponseValidator.deserializedResponse(testcontext.response,
				CompleteUserResponse.class);
		assertNotNull("User information not received", completeUserResponse);
	}

	@Then("user should get success response for get profile {int}")
	public void userShouldGetSuccessResponseForGetProfile(Integer statusCode) {
		assertEquals(Long.valueOf(statusCode), Long.valueOf(testcontext.response.statusCode()));
		logger.info("The success response for view profile request is received");
	}

	@And("user profile details are retrieved")
	public void userProfileDetailsAreRetrieved(DataTable dataTable) {
		Map<String, String> userData = dataTable.asMaps().get(0);
		assertEquals(userData.get("id"), testcontext.response.jsonPath().get("data.id").toString());
		assertEquals(userData.get("name"), testcontext.response.jsonPath().get("data.name").toString());
		assertEquals(userData.get("email"), testcontext.response.jsonPath().get("data.email").toString());
		logger.info("User profile data retrieved succussfully");

	}

}
