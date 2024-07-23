package com.api.stepdefinition;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.api.controller.CartController;
import com.api.model.responses.Cart;
//import com.api.utils.Readers;
import com.api.utils.Readers.JsonReader;
import com.api.utils.TestContext;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;


/*
 * This class is step definition for cart.feature
*/


public class CartStepDefinition extends TestContext{
	private static final Logger LOG = LogManager.getLogger(CartStepDefinition.class);
	
	public CartStepDefinition() {
		//TestContext.setCookie(Generators.generateCookie());
	}
	
	@Given("user has an cookie from homepage")
	public void userHasAccessToHomepage() {	
		
		assertNotNull(TestContext.getCookie());
		assertNotNull(TestContext.getsetUUid());
		
		
	}
	

	@Given("user can access product {string} at endpoint {string} with {string}")
	public void userCanAccessProduct(String productId, String endPoint, String expStatusCode){	
		
		TestContext.session.put("prod_id", productId);
		
		Response response = CartController.getProduct(productId, endPoint);
		
		assertEquals(response.getStatusCode(), Integer.parseInt(expStatusCode));
	}
	
	@When("user makes a request to add the product using endpoint {string}")
	public void userAddProduct(String endPoint) {		
		
		Response response = CartController.addProduct(TestContext.getCookie()
				,(String) TestContext.session.get("prod_id"),false,TestContext.getsetUUid(),endPoint);
		assertEquals(response.getStatusCode(), 200);

	}
	
	@Then("user should be able to see the product details {string} at endpoint {string}")
	public void productIsAdded(String jsonFile,String endPoint) {	
		try {
			Response response = CartController.getProductsInCart(TestContext.getCookie(), false, endPoint);
			
			Cart actCartItems = Cart.mapToCart(response.getBody().asString());
			Cart expCartItems = Cart.mapToCart(JsonReader.getRequestBody(jsonFile,"CartItemsSetA"));

			//Readers.JsonReader.validateJSONSchema("cart_schema.json", response.getBody().asString());
	
			CartController.removeProductsFromCart(TestContext.getsetUUid(), "/deleteitem");
			
			
			assertTrue(actCartItems.equals(expCartItems));
		}catch (Exception e) {
			LOG.error("Got an exception", e);
		}
	}
	

	
	@Then("user should be able to remove the product using endpoint {string}")
	public void productIsRemoved(String endPoint) {	
		try {
			
			CartController.removeProductsFromCart(TestContext.getsetUUid(), "/deleteitem");
			 
			Response response = CartController.getProductsInCart(TestContext.getCookie(), false, "/viewcart");
			Cart CartItems = Cart.mapToCart(response.getBody().asString());
			int prodId = Integer.parseInt((String) TestContext.session.get("prod_id"));
			
			assertFalse(CartItems.searchItemByProdIdExists(prodId));
			
			
			
		}catch (Exception e) {
			LOG.error("Got an exception", e);
		}
	}
}

