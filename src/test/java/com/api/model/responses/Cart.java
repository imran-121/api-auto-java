package com.api.model.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Objects;


/*
 * This class is object representation for storing response object for cart items
*/

public class Cart {
    
    @JsonProperty("Items")
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public static class Item {
        
        @JsonProperty("cookie")
        private String cookie;
        
        @JsonProperty("id")
        private String id;
        
        @JsonProperty("prod_id")
        private int prodId;

        // Default constructor
        public Item() {
        }

        // Parameterized constructor
        public Item(String cookie, String id, int prodId) {
            this.cookie = cookie;
            this.id = id;
            this.prodId = prodId;
        }

        // Getters and Setters
        public String getCookie() {
            return cookie;
        }

        public void setCookie(String cookie) {
            this.cookie = cookie;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getProdId() {
            return prodId;
        }

        public void setProdId(int prodId) {
            this.prodId = prodId;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "cookie='" + cookie + '\'' +
                    ", id='" + id + '\'' +
                    ", prodId=" + prodId +
                    '}';
        }
        
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Item item = (Item) obj;
            if (prodId != item.prodId) {
                return false;
            }
            /*
            if (cookie != null ? !cookie.equals(item.cookie) : item.cookie != null) {
                return false;
            }
			
			if (id != null ? !id.equals(item.id) : item.id != null) { return false; }
			 */
            return true;
        }
    }

    @Override
    public String toString() {
        return "Cart{" +
                "items=" + items +
                '}';
    }
    
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Cart cart = (Cart) obj;
        if ( items.size() != cart.items.size()) {
            return false;
        }
        
        for (int i = 0; i < items.size(); i++) {
            if (!Objects.equals(items.get(i), cart.items.get(i))) {
                return false;
            }
        }
        return true;
    }
    
    //Cart actCartItems= new ObjectMapper().readValue(response.getBody().asString() , Cart.class);
	//Cart expCartItems = new ObjectMapper().readValue(JsonReader.getRequestBody(jsonFile,"CartItemsSetA") , Cart.class);
    
    public static Cart mapToCart(String string) {
    	try {
			return new ObjectMapper().readValue(string , Cart.class);
		} catch (JsonMappingException e) {
			
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
    
    
    
    public Item getItemByProdId(int prodId) {
        if (items == null) {
            return null;
        }
        for (Item item : items) {
            if (item.getProdId() == prodId) {
                return item;
            }
        }
        return null;
    }
    
    public Boolean searchItemByProdIdExists(int prodId) {
        if (items == null) {
            return false;
        }
        for (Item item : items) {
            if (item.getProdId() == prodId) {
                return true;
            }
        }
        return false;
    }
    
}

