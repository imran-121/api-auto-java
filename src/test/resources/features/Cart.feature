@cart
Feature: Add and remove products from cart

  Background: create an session
    Given user has an cookie from homepage
   
	@api @sanity @highpriority
	Scenario Outline: Add a new product to the cart
    Given user can access product "<productid>" at endpoint "/view" with "<statuscode>"
    When user makes a request to add the product using endpoint "/addtocart"
    Then user should be able to see the product details "<inputjson>" at endpoint "/viewcart"
    
    Examples:
    	| productid   | statuscode | inputjson 					|
      | 			1 		|  	200      | itemsListCart.json |
      
  @api
  Scenario Outline: Add a new product to the cart
    Given user can access product "<productid>" at endpoint "/view" with "<statuscode>"
    When user makes a request to add the product using endpoint "/addtocart"
    Then user should be able to remove the product using endpoint "/deleteitem"
    
    Examples:
    	| productid   | statuscode | 
      | 			1 		|  	200      | 