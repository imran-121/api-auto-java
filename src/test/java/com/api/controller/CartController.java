package com.api.controller;



import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.api.model.requests.AddCartItem;


import com.api.model.requests.DeleteItem;
import com.api.model.requests.ProductId;
import com.api.model.requests.ViewCartItem;
import com.api.stepdefinition.CartStepDefinition;


import io.restassured.response.Response;

/*
 * This control has the logic for all of the actions done on cart component

*/
public class CartController extends BaseController{
	
	private static final Logger LOG = LogManager.getLogger(CartStepDefinition.class);
	private static final Response Object = null;
	
	public static Response getProduct(String productId, String endPoint){	
		try {
			
			return sendRequest("POST", endPoint, new ProductId(productId).toJsonString());
			
		}catch (Exception e){
			LOG.error("Got an exception", e);
			return Object;
		}
	}
		
	public static Response addProduct(String cookie, String productId,boolean flag, String uuid, String endPoint) {
		try {
			AddCartItem item = new AddCartItem(cookie,productId,false,uuid);
			Response response = sendRequest("POST", endPoint, item.toJsonString());
			return response;
		}catch (Exception e){
			LOG.error("Got an exception", e);
			return Object;
		}
		
	}
	
	public static Response getProductsInCart(String cookie, boolean flag, String endPoint) {
		try {
			
			ViewCartItem viewCartItem = new ViewCartItem(cookie,false);
			
			Response response = sendRequest("POST", endPoint, viewCartItem.toJsonString());
			
			return response;
		}catch (Exception e){
			LOG.error("Got an exception", e);
			return Object;
		}
		
	}
	
	public static Response removeProductsFromCart(String UUID, String endPoint) {
		try {
			
			DeleteItem deleteItem = new DeleteItem(UUID);
			
			Response response = sendRequest("POST", endPoint, deleteItem.toJsonString());
			
			return response;
		}catch (Exception e){
			LOG.error("Got an exception", e);
			return Object;
		}
		
	}
	
	
	
	
		
}
