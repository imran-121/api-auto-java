package com.api.stepdefinition;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;

import com.api.utils.Generators;
import com.api.utils.TestContext;



public class Hooks {

	private static final Logger LOG = LogManager.getLogger(Hooks.class);
	
	@Before
	public void testStart(Scenario scenario) {
		
		TestContext.setCookie(Generators.generateStaticCookie());
	    TestContext.setUUid(Generators.generateUUID());
		
		LOG.info("*****************************************************************************************");
		LOG.info("	Scenario: "+scenario.getName());
		LOG.info("*****************************************************************************************");
	}
	
	@BeforeAll 
	 public static void beforeAll() { 
		String log4jConfigFile = "config/log4j.properties";
        PropertyConfigurator.configure(ClassLoader.getSystemResource(log4jConfigFile));
		 // Runs before all scenarios
        LOG.info("=====================================================================");
        LOG.info("					EXECUTION STARTED 							    ");
		LOG.info("=====================================================================");
	 }
	
	
	@AfterAll 
	 public static void afterAll() { 
		
		 // Runs before all scenarios
       LOG.info("=====================================================================");
       LOG.info("					EXECUTION ENDED 							    ");
	   LOG.info("=====================================================================");
	 }
	
	
	
	
}
