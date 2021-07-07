package com.verivox.stepdefinitions.pages;

import java.util.List;

import com.verivox.utility.ServiceEndpointHandler;

import io.restassured.response.Response;

public class AddressServiceChecksPage {
	
	public static Response res;
	
	public void sendRequestForCity(String postcode) {
		res = new ServiceEndpointHandler().GETAddressCheckpointForCity(postcode);
	}
	
	public void sendRequestForStreets(String city, String postcode) {
		res = new ServiceEndpointHandler().GETAddressCheckpointForStreets(postcode, city);
	}
	
	public int getRequestStatus() {
		return res.getStatusCode();
	}
	
	public List<String> getCitiesFromResponse() {
		return res.getBody().jsonPath().getList("Cities");
	}
	
	public List<String> getStreetsFromResponse() {
		return res.getBody().jsonPath().getList("Streets");
	}
}
