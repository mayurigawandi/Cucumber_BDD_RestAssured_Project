package com.notes.test;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty:target/cucumber/cucumber.txt",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
		// "html:/target/cucumber/report",
		"json:/target/cucumber/cucumber.json", "com.notes.utils.MyTestListener" }, features = {
				"src/test/resources/features" }, glue = { "com.notes.stepdefinition" }
// ,dryRun=true
		, monochrome = true, snippets = SnippetType.CAMELCASE
//,tags = "@updateUserProfileFromJSON and not @userDelete,@userRegistration,@viewUserProfile,@userLogin,@updateUsingDataTable"
// ,publish=true
)

public class TestRunner {

}
