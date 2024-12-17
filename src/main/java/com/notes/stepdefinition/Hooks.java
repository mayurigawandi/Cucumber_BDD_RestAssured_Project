package com.notes.stepdefinition;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

	private static final Logger logger = LogManager.getLogger(Hooks.class);

	@Before
	public void startTest(Scenario scenario) {

		logger.info("*******************************************************************************************");
		logger.info("Scenario: " + scenario.getName());
		logger.info("*******************************************************************************************");

	}

}
