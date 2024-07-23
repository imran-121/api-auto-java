package com.api.model.requests;
import com.api.model.ModelBase;


/*
 * This class is object representation for making request to access an item
*/

public class ProductId extends ModelBase {
    private int id;

    // Default constructor
    public ProductId() {}

    // Parameterized constructor
    public ProductId(int id) {
        this.id = id;
    }
    public ProductId(String id) {
        //this.id = Integer.parseInt(id);
    	this.id = Integer.parseInt(id);
    }
    // Getter for id
    public int getId() {
        return id;
    }

    // Setter for id
    public void setId(int id) {
        this.id = id;
    }
    
    public void setId(String id) {
        this.id = Integer.parseInt(id);
    }
    
    // Overriding toString method for better readability
    @Override
    public String toString() {
    	 return "ProductId{id=" + id + "}";
    }

    // Overriding equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductId productId = (ProductId) o;

        return id == productId.id;
    }

    // Overriding hashCode method
    @Override
    public int hashCode() {
        return id;
    }
}