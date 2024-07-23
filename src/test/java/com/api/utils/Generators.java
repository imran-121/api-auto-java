package com.api.utils;


import java.util.UUID;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import com.api.utils.Readers.PropertiesFile;

/*
 * This class contains the generator functions like cookie generator
 * 
*/

public class Generators {
	private static final Logger LOG = LogManager.getLogger(Generators.class);
	
	
	public static String generateUUID(){
		try {
			return  UUID.randomUUID().toString();
		}catch(Exception e) {
			LOG.error("An exception is raised", e);
			return null;
		}
		
	}
	
	public static String generateCookieWithSelenium() {
		
		
		  WebDriver driver= new ChromeDriver(new
		  ChromeOptions().addArguments("--headless"));
		  
		  try {
		  
		  driver.get(PropertiesFile.getProperty("homePage")); Thread.sleep(4000);
		  String cookie = driver.manage().getCookieNamed("user").toString(); return
		  cookie;
		  
		  } catch (InterruptedException e) {
		  
		  LOG.error("Got an exception", e); return null; 
		  } 
		  finally { 
			  driver.quit(); 
		 }
		 
		
		
	}
	
	public static String generateStaticCookie() {
		return  "user=cebd28f1-1e8d-ba74-15b7-432d2dc966e0";
	}
	

	
}
