package com.notes.stepdefinition;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.notes.model.CompleteUserResponse;
import com.notes.utils.ReadJson;
import com.notes.utils.ResponseValidator;
import com.notes.utils.TestContext;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class UpdateProfileStepDefinition {
	private static final Logger logger = LogManager.getLogger(UpdateProfileStepDefinition.class);
	TestContext testcontext;

	public UpdateProfileStepDefinition(TestContext testcontext) {
		this.testcontext = testcontext;
	}

	@Given("user has access to the udpate profile endpoint {string}")
	public void userHasAccessToTheUpdateProfileEndpoint(String updateProfileEndpoint) {
		testcontext.sessionData.put("updateProfileEndpoint", updateProfileEndpoint);
		logger.info("The update endpoint is inserted in sessiondata");
	}

	@When("user updates the information {string}, {string}, {string}")
	public void userUpdatesTheInformation(String name, String phone, String company) {
		testcontext.response = testcontext.requestSetup().header("x-auth-token", testcontext.sessionData.get("token"))
				.formParam("name", name).formParam("phone", phone).formParam("company", company).when()
				.patch(testcontext.sessionData.get("updateProfileEndpoint").toString());
		System.out.println("Response Body: " + testcontext.response.asString());
		CompleteUserResponse completeUserResponse = ResponseValidator.deserializedResponse(testcontext.response,
				CompleteUserResponse.class);
		assertNotNull("User information not updated", completeUserResponse);
		logger.info("The update information is deserialized successfully ");
	}

	@And("user should validate updated details {string}, {string}, {string}")
	public void userShouldValidateUpdatedDetails(String name, String phone, String company) {

		assertEquals("Name is updated", name, testcontext.response.jsonPath().get("data.name").toString());
		assertEquals("Phone is updated", phone, testcontext.response.jsonPath().get("data.phone").toString());
		assertEquals("Company name is updated", company,
				testcontext.response.jsonPath().get("data.company").toString());
		logger.info("The updated information is validated");
	}

	@When("user makes a request to update information {string} from JSON file {string}")
	public void userMakesARequestToUpdateInformation(String dataKey, String JSONFile) {
		Map<String, String> jsonFileData = ReadJson.getJsonData(JSONFile, dataKey);
		testcontext.response = testcontext.requestSetup().header("x-auth-token", testcontext.sessionData.get("token"))
				.formParam("phone", jsonFileData.get("name").toString())
				.formParam("name", jsonFileData.get("phone").toString())
				.formParam("company", jsonFileData.get("company").toString()).when()
				.patch(testcontext.sessionData.get("updateProfileEndpoint").toString());
		System.out.println("Response Body: " + testcontext.response.getBody().toString());
		CompleteUserResponse completeUserResponse = ResponseValidator.deserializedResponse(testcontext.response,
				CompleteUserResponse.class);
		assertNotNull("User information not updated", completeUserResponse);
		logger.info("The json file data is updated in request");
	}

}