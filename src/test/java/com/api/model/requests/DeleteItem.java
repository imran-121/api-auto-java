package com.api.model.requests;


import com.api.model.ModelBase;

/*
 * This class is object representation for making request for deleting an item from cart
*/

public class DeleteItem extends ModelBase {

    private String id;
    
    public DeleteItem() {
    	
    }
    
    public DeleteItem(String id) {
    	this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
	
}