package com.verivox.hooks;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.verivox.utility.BaseTest;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

	@Before
	public void beforeHook() throws Throwable{
		System.out.println("this is before hooks");
		new BaseTest().initialization();
	}
	
	@After
	public void afterHook(Scenario scenario) {
		System.out.println("this is after hooks");
		if(scenario.isFailed()) {
			final byte[] screenshot=((TakesScreenshot)BaseTest.driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", scenario.getName());
		}
		new BaseTest().quitDriver();
	}
}
