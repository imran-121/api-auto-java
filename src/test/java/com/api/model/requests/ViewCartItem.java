package com.api.model.requests;

import com.api.model.ModelBase;

/*
 * This class is object representation for making request to view cart items
*/

public class ViewCartItem extends ModelBase {
	private String cookie;
	private boolean flag;
	
	public ViewCartItem(String cookie, boolean flag){
		this.cookie = cookie;
		this.flag = flag;
	}
	

	public String getCookie() {
		return this.cookie;
	}
	
	public boolean getFlag() {
		return this.flag;
	}
	
	public String setCookie(String cookie) {
		return this.cookie = cookie;
	}
	
	public boolean setFlag(boolean flag) {
		return this.flag = flag;
	}
	//

	
	
}
