package com.verivox.stepdefinitions;

import org.testng.Assert;

import com.verivox.stepdefinitions.pages.*;
import com.verivox.utility.BaseTest;
import com.verivox.utility.CommonMethods;
import com.verivox.utility.TestUtils;
import org.openqa.selenium.By;

public class LandingPageStepDefinitions extends BaseTest {

	public LandingPageStepDefinitions() throws Throwable {

		Given("that I can open Verivox site", () -> {
			if (new CommonMethods().isCookiePolicyPreferencesEnabled(driver)) {
				new TestUtils().waitForElementVisibility(driver.findElement(By.id("uc-btn-accept-banner")));
				driver.findElement(By.id("uc-btn-accept-banner")).click();
			}
			Assert.assertTrue(new HomePage().pageHeader.isDisplayed());
		});
		When("I navigate to the DSL calculator page", () -> {
			new HomePage().mouseHoverOnSelectedMenu("DSL");
			new HomePage().clickOnSubMenu("DSL-Vergleich");
		});
	}
}
