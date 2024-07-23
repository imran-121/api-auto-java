package com.api.utils;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import com.fasterxml.jackson.databind.JsonNode;

import com.fasterxml.jackson.databind.ObjectMapper;


import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;


public class Readers {
	
	public static class JsonReader {
		private static final Logger LOG = LogManager.getLogger(PropertiesFile.class);
		private static String dataPath = new File(PropertiesFile.getProperty("test.data.path")).getAbsolutePath()+File.separator;
		private static JSONParser parser = new JSONParser();
		private static Object body;
		private static ObjectMapper objectMapper;
		
		
		
		public static String getRequestBody(String jsonFileName, String jsonKey) {	
			
			try {
				body = ((JSONObject)parser.parse(new FileReader(dataPath+jsonFileName))).get(jsonKey);
				if (body == null) {
					throw new RuntimeException("NO DATA FOUND in JSON file '" + jsonFileName +"' for key '"+jsonKey+"'");
				}
			} catch (FileNotFoundException e) {
				throw new RuntimeException("JSON file not found at path: " + dataPath+jsonFileName);
			} catch (IOException e) {
				throw new RuntimeException("IOException while reading file: " + jsonFileName);
			} catch (ParseException e) {
				throw new RuntimeException("Parse Exception occured while Parsing: " + jsonFileName);
			}
			return body.toString();
		}
		
		public static boolean validateJSONSchema(String schemaFileName, String response) {		
			try {

				objectMapper = new ObjectMapper();
				JsonSchemaFactory schemaFactory = JsonSchemaFactory.byDefault();
				
				String schemaFile = PropertiesFile.getProperty("test.json.schema.path") + "/" + schemaFileName;
				JsonNode schemaNode = objectMapper.readTree(new File(schemaFile)); 
				JsonSchema schema = schemaFactory.getJsonSchema(schemaNode);
				JsonNode jsonData = objectMapper.readTree(response);
				ProcessingReport report = schema.validate(jsonData);

				if (report.isSuccess()) {
	
	                return true;
	            } else {
	                //System.out.println("JSON is not valid against the schema.");
	                LOG.error(report.toString());
	                return false;
	            }
	
			} catch(Exception e) {
				LOG.error("IO Exception while closing file input stream", e);
				return false;
			}
		}
		
		
	
	}
		

	
	public static class PropertiesFile {
		
		private static final Logger LOG = LogManager.getLogger(PropertiesFile.class);
		private static FileInputStream fis;
		private static Properties prop = null;

		public static String getProperty(String property) {		
			
			try {
				fis = new FileInputStream(new File("src\\test\\resources\\config\\config.properties"));
				prop = new Properties();
				prop.load(fis);
			} catch(FileNotFoundException fnfe) {
				LOG.error("Properties File Not Found", fnfe);
			} catch(IOException ioe) {
				LOG.error("IO Exception while loading Properties File", ioe);
			} finally {
				try {
					fis.close();
				} catch (IOException e) {
					LOG.error("IO Exception while closing file input stream", e);
				}
			}
			return prop.getProperty(property).trim();
		}
	}

}
