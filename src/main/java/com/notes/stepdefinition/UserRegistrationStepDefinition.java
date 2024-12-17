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

public class UserRegistrationStepDefinition {
	private static final Logger logger = LogManager.getLogger(UserRegistrationStepDefinition.class);
	private TestContext testcontext;

	// dependency Injection
	public UserRegistrationStepDefinition(TestContext testcontext) {
		this.testcontext = testcontext;
	}

	@Given("user has access to endpoint {string}")
	public void userHasAccessToEndpoint(String registerEndpoint) {
		testcontext.sessionData.put("registerEndpoint", registerEndpoint);
		logger.info("The register endpoint is stored in session data ");

	}

	@When("user makes request with valid user details")
	public void userMakesRequestWithValidUserDetails(DataTable dataTable) {
		Map<String, String> validRegisterData = dataTable.asMaps().get(0);
		testcontext.response = testcontext.requestSetup().formParam("name", validRegisterData.get("name"))
				.formParam("email", validRegisterData.get("email"))
				.formParam("password", validRegisterData.get("password")).when()
				.post(testcontext.sessionData.get("registerEndpoint").toString());
		logger.info("The register request is send with valid details");
		CompleteUserResponse completeUserResponse = ResponseValidator.deserializedResponse(testcontext.response,
				CompleteUserResponse.class);
		assertNotNull("User not registered", completeUserResponse);
	}

	@Then("user should get the success response code {int}")
	public void userShouldGetTheSuccessResponseCode(Integer statusCode) {
		assertEquals(Long.valueOf(statusCode), Long.valueOf(testcontext.response.getStatusCode()));
		logger.info("The API request is successfully executed for registration endpoint");
	}

	@And("user should get the user id")
	public void userShouldGetTheUserId() {
		assertNotNull("user id is not null", testcontext.response.jsonPath().get("data.id"));
		testcontext.sessionData.put("userId", testcontext.response.jsonPath().get("data.id"));
		logger.info("User id is generated and sotred in session data" + testcontext.response.jsonPath().get("data.id"));

	}

	@When("user makes request with invalid user details")
	public void userMakesRequestWithInvalidUserDetails(DataTable dataTable) {
		Map<String, String> invalidRegisterData = dataTable.asMaps().get(0);
		testcontext.response = testcontext.requestSetup().formParam("name", invalidRegisterData.get("name"))
				.formParam("email", invalidRegisterData.get("email"))
				.formParam("password", invalidRegisterData.get("password")).when()
				.post(testcontext.sessionData.get("registerEndpoint").toString());
		logger.info("User send the request with invalid detais for registration");
	}

	@Then("user should get the error response code {int}")
	public void userShouldGetTheErroResponseCode(Integer statusCode) {
		assertEquals(Long.valueOf(statusCode), Long.valueOf(testcontext.response.getStatusCode()));
		logger.info("The expected error response is received for invalid register details is received");
	}

}
