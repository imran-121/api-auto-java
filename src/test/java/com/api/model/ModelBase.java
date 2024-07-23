package com.api.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * This class is the base for all models

*/
public class ModelBase {
	public String toJsonString() throws JsonProcessingException {
    	return new ObjectMapper().writeValueAsString(this);
    }
}
