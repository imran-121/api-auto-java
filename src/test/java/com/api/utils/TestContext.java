package com.api.utils;

import java.util.HashMap;
import java.util.Map;


import io.restassured.response.Response;


/*
 * This class is used for sharing the information among the tests and steps
*/
public class TestContext {
		
	public Response response;
	public static Map<String, Object> session = new HashMap<String, Object>();
	
	
	public static void setCookie(String cookie) {
		session.put("cookie",cookie);
	}
		
	public static String getCookie() {
		return session.get("cookie").toString();
	}
	
	public static void setUUid(String uuid) {
		session.put("uuid",uuid);
	}
		
	public static String getsetUUid() {
		return session.get("uuid").toString();
	}
}
