package com.api.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.api.stepdefinition.CartStepDefinition;
import com.api.utils.Readers.PropertiesFile;

import com.api.utils.RestAssuredRequestFilter;
import com.github.dzieciou.testing.curl.CurlRestAssuredConfigFactory;
import com.github.dzieciou.testing.curl.Options;

import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


/*
 * Represents a the base of all of the controllers.
 * Controllers is the middle layer b/w models and REST requests
 * 
*/

public class BaseController {
	
	private static final Logger LOG = LogManager.getLogger(CartStepDefinition.class);
	public Response response;
	private static final String CONTENT_TYPE = PropertiesFile.getProperty("content.type");

	 public static Response sendRequest(String method, String endpoint, String requestBody) {
		 try {
			//  RestAssured.requestSpecification = RestAssured.given().log().ifValidationFails(LogDetail.NONE);
			RestAssured.reset();
			Options options = Options.builder().logStacktrace().build();
			RestAssuredConfig config = CurlRestAssuredConfigFactory.createConfig(options); 
	        // Set base URI
	        RestAssured.baseURI = PropertiesFile.getProperty("baseURL");
	        // Create request specification
	        RequestSpecification request = RestAssured.given()
	        		.config(config)
	        		.filter(new RestAssuredRequestFilter())
	                .header("Content-Type", CONTENT_TYPE)
	                .contentType(CONTENT_TYPE)
	                .accept(CONTENT_TYPE)
	                .body(requestBody);
	
	        // Perform the request based on the method parameter
	        Response response = null;
	        switch (method.toUpperCase()) {
	            case "POST":
	                response = request.post(endpoint);
	                break;
	            case "GET":
	                response = request.get(endpoint);
	                break;
	            case "PUT":
	                response = request.put(endpoint);
	                break;
	            case "DELETE":
	                response = request.delete(endpoint);
	                break;
	            default:
	                throw new IllegalArgumentException("Invalid HTTP method: " + method);
	        }
	
	        return response;
		 }catch(Exception e) {
			 LOG.error("Got an exception", e);
			 return null;
		 }
    }
}
