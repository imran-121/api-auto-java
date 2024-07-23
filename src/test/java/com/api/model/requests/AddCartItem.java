package com.api.model.requests;


import com.api.model.ModelBase;
import com.fasterxml.jackson.annotation.JsonProperty;


/*
 * This class is object representation for making request for adding an item to cart
*/

//@Data
public class AddCartItem extends ModelBase{
	private String cookie;
	
	@JsonProperty("prod_id")
	private int prod_id;
	private boolean flag;
	private String id;
	
	
	public AddCartItem(){
		
	}
	
	public AddCartItem(String cookie, int prod_id, boolean flag, String UUId){
			this.cookie = cookie;
			this.prod_id = prod_id;
			this.flag = flag;
			this.id = UUId;
	}
	
	public AddCartItem(String cookie, String prod_id, boolean flag, String UUId){
		this.cookie = cookie;
		this.prod_id = Integer.parseInt(prod_id);
		this.flag = flag;
		this.id = UUId;
	}
	
	
	public String getCookie() {
		return this.cookie;
	}
	
	public int getProdId() {
		return this.prod_id;
	}
	
	public boolean getFlag() {
		return this.flag;
	}
	
	public String getId() {
		return this.id;
	}
	
	
	
	public String setCookie(String cookie) {
		return this.cookie = cookie;
	}
	
	public int setProdId(int prod_id) {
		return this.prod_id = prod_id;
	}
	
	public boolean setFlag(boolean flag) {
		return this.flag = flag;
	}
	
}
