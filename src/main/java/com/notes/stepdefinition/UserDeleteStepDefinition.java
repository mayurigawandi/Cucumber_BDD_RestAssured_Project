package com.notes.stepdefinition;

import static org.junit.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.notes.utils.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class UserDeleteStepDefinition {
	TestContext testcontext;
	private static final Logger logger = LogManager.getLogger(UserDeleteStepDefinition.class);

	public UserDeleteStepDefinition(TestContext testcontext) {
		this.testcontext = testcontext;
	}

	@Given("user has access to logout endpoint {string}")
	public void userHasAccessToLogoutEndpoint(String logoutEndpoint) {
		testcontext.sessionData.put("logoutEndpoint", logoutEndpoint);
		logger.info("The logout endpoint is stored in the session data ");
	}

	@When("user sends the request to logout")
	public void userSendsTheRequestToLogout() {
		testcontext.response = testcontext.requestSetup().header("x-auth-token", testcontext.sessionData.get("token"))
				.when().delete(testcontext.sessionData.get("logoutEndpoint").toString());
		logger.info("Logout request sends");
	}

	@And("user should get logout")
	public void userShouldGetLogout() {
		assertEquals("User has been successfully logged out",
				testcontext.response.jsonPath().get("message").toString());
		logger.info("The user is logout");
	}

}
