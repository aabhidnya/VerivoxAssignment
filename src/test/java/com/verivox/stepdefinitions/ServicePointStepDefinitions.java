package com.verivox.stepdefinitions;

import com.verivox.stepdefinitions.pages.AddressServiceChecksPage;
import com.verivox.utility.BaseTest;

import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;

import org.testng.Assert;

public class ServicePointStepDefinitions extends BaseTest{
	
	public ServicePointStepDefinitions() {
		Given("the address checking service endpoint", () -> {
		    System.out.println("steup to check endpoint is done");
		});

		When("I request the cities for postcode {string}", (String postcode) -> {
		    new AddressServiceChecksPage().sendRequestForCity(postcode);
		});

		Then("I should receive a response with {string}", (String city) -> {
		    Assert.assertTrue(new AddressServiceChecksPage().getRequestStatus() == 200);
		    List<String> convertedCityList = Stream.of(city.split(",", -1))
		    		  .collect(Collectors.toList());
		    convertedCityList.forEach(item -> Assert.assertTrue(new AddressServiceChecksPage().getCitiesFromResponse().contains(item.trim())));
		});
		
		When("I request the streets for {string} postcode {string}", (String city, String postcode) -> {
			new AddressServiceChecksPage().sendRequestForStreets(city, postcode);
		});

		Then("I should receive a response with {string} streets", (String count) -> {
			Assert.assertTrue(new AddressServiceChecksPage().getRequestStatus() == 200);
			Assert.assertTrue(new AddressServiceChecksPage().getStreetsFromResponse().size() == Integer.parseInt(count));
			
		});
		
		Then("street names with special characters and dashes displayed correctly {string}", (String streetNames) -> {
			
			List<String> streetNamesList = Stream.of(streetNames.split(",", -1))
		    		  .collect(Collectors.toList());
			streetNamesList.forEach(item -> Assert.assertTrue(new AddressServiceChecksPage().getStreetsFromResponse().contains(item.trim())));
		});

	}

}
