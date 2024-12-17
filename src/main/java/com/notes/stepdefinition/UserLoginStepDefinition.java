package com.notes.stepdefinition;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.notes.utils.TestContext;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.datatable.DataTable;

public class UserLoginStepDefinition {
	private static final Logger logger = LogManager.getLogger(UserLoginStepDefinition.class);
	private TestContext testcontext;

	public UserLoginStepDefinition(TestContext testcontext) {
		this.testcontext = testcontext;
	}

	@Given("user has access to login endpoint {string}")
	public void userHasAccessToLoginEndpoint(String loginEndpoint) {
		testcontext.sessionData.put("loginEndpoint", loginEndpoint);
		logger.info("The login endpoint is stored in sessionData ");

	}

	@When("user enter the valid credentials")
	public void userEnterTheValidCredentials(DataTable dataTable) {
		Map<String, String> validLoginDetails = dataTable.asMaps().get(0);
		testcontext.response = testcontext.requestSetup().formParam("email", validLoginDetails.get("email"))
				.formParam("password", validLoginDetails.get("password")).when()
				.post(testcontext.sessionData.get("loginEndpoint").toString());
		logger.info("The login request is sent");

	}

	@Then("user should get success response {int}")
	public void userShouldGetSuccessResponse(Integer statusCode) {
		assertEquals(Long.valueOf(statusCode), Long.valueOf(testcontext.response.getStatusCode()));
		logger.info("The API request is successfully executed for login endpoint");
	}

	@And("user should get the token")
	public void userShouldGetTheToken() {
		assertNotNull("Token is not generated", testcontext.response.jsonPath().get("data.token"));
		logger.info("the token is generated");
		String token = testcontext.response.jsonPath().get("data.token");
		testcontext.sessionData.put("token", token);
		logger.info("The token value is store in the session data" + token);

	}

	@When("user enter the invalid credentials")
	public void userEnterTheInvalidCredentials(DataTable dataTable) {
		Map<String, String> invalidLoginDetails = dataTable.asMaps().get(0);
		testcontext.response = testcontext.requestSetup().formParam("email", invalidLoginDetails.get("email"))
				.formParam("password", invalidLoginDetails.get("password")).when()
				.post(testcontext.sessionData.get("loginEndpoint").toString());
		logger.info("The invalid details are sent to login endpoint");
	}

	@Then("user should get error response {int}")
	public void userShouldGetErrorResponse(Integer statusCode) {
		assertEquals(Long.valueOf(statusCode), Long.valueOf(testcontext.response.getStatusCode()));
		assertEquals("Incorrect email address or password", testcontext.response.jsonPath().get("message").toString());
		logger.info("The expected error response is received in invalid login details");
	}
}
