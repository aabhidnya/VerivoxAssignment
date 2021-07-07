package com.verivox.testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		plugin = {"pretty", "summary","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
		features = "src/test/java/com/verivox/features/",
        glue = {"com.verivox.hooks","com.verivox.stepdefinitions"},
        monochrome = true,
       	dryRun = false)

public class TestRunner extends AbstractTestNGCucumberTests { 
	
}
