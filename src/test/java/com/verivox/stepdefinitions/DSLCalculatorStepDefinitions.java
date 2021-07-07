package com.verivox.stepdefinitions;

import com.verivox.stepdefinitions.pages.DSLCalculatorPage;
import com.verivox.utility.BaseTest;
import com.verivox.utility.TestUtils;

import java.util.List;

import org.testng.Assert;

public class DSLCalculatorStepDefinitions extends BaseTest {

	public static int pressCount = 0;
	
	public DSLCalculatorStepDefinitions() {
		When("I enter {string} for my area code", (String areaCode) -> {
			new DSLCalculatorPage().enterAreaCode(areaCode);
		});
		
		When("I select the {int} Mbit\\/s bandwidth option", (Integer bandwidth) -> {
		    new DSLCalculatorPage().selectBandWidth(bandwidth);
		});
		
		When("I click the Jetzt vergleichen button", () -> {
			new DSLCalculatorPage().clickOnJetztVergleichenButton();
		});
		
		Then("I should see a page that lists the available tariffs for my selection", () -> {
			//at least 5 internet tariffs are displayed
			Assert.assertTrue(new DSLCalculatorPage().getCountOfErmittelteTarife() > 5);
			List<Integer> values = new DSLCalculatorPage().getDowloadSpeedForDisplayedPlans();
			//the displayed tariffs provide at least 100 Mbit/s download speed
			values.forEach((i) -> Assert.assertTrue(i > 99));
		});
		
		When("I display the tariff Result List page", () -> {
		});

		Then("I should see the total number of available tariffs listed in the Ermittelte Tarife section", () -> {
			new DSLCalculatorPage().getTotalCountFromHeader();
			Assert.assertTrue(new DSLCalculatorPage().getCountOfErmittelteTarife() > 5);
		});

		When("I scroll to the end of the Result List page", () -> {
		    new DSLCalculatorPage().scrollToViewLoadMoreTaiff();
		});

		Then("I should see only the first {int} tariffs displayed", (Integer count) -> {
			Assert.assertTrue(new DSLCalculatorPage().getCountOfErmittelteTarife() == count);
		});

		When("I click on the button labeled {string}", (String buttonName) -> {
		   new TestUtils().clickOnButtonByText(buttonName);
		   pressCount++;
		});

		Then("I should see the next {int} tariffs displayed", (Integer count) -> {
			int finalcount = pressCount*20 + count;
			System.out.println(finalcount);
			Assert.assertTrue(new DSLCalculatorPage().getCountOfErmittelteTarife() == finalcount);
		});

		Then("I can continue to load any additional tariffs until all tariffs have been displayed", () -> {
			new DSLCalculatorPage().loadAllTariff();
			Assert.assertTrue(new DSLCalculatorPage().getCountOfErmittelteTarife() == DSLCalculatorPage.countInHeader);
		});
	}
}
