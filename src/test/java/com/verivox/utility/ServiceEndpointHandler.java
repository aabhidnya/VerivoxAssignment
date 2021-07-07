package com.verivox.utility;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class ServiceEndpointHandler {

	String serviceUrl = "https://service.verivox.de";

	// Get city list alloacated to the given postcode
	public Response GETAddressCheckpointForCity(String postcode) {
		Response res;
		RestAssured.baseURI = serviceUrl;
		RestAssured.basePath = "/geo/latestv2/cities/" + postcode;
		res = given().relaxedHTTPSValidation().get();
		return res;
	}

	// send GET request and return response object
	public Response GETAddressCheckpointForStreets(String postcode, String city) {
		Response res;
		RestAssured.baseURI = serviceUrl;
		RestAssured.basePath = "/geo/latestv2/cities/" + postcode + "/" + city + "/streets";
		res = given().relaxedHTTPSValidation().get();
		return res;
	}
}
