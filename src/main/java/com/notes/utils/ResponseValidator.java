package com.notes.utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

public class ResponseValidator {
	  /**
	     * Deserializes the given REST-assured Response into a specified class type.
	     *
	     * @param response the Response object from REST-assured
	     * @param clazz    the target class to deserialize into
	     * @param <T>      the type of the target class
	     * @return an object of the specified type containing the deserialized data
	     */
	    public static <T> T deserializedResponse(Response response, Class<T> clazz) {
	        ObjectMapper mapper = new ObjectMapper();
	        T responseDeserialized = null;
	        try {
	            // Deserialize the response body to the specified class
	            responseDeserialized = mapper.readValue(response.getBody().asString(), clazz);

	            // Log the deserialized object (for debugging)
	            System.out.println("Deserialized Object: " + mapper.writerWithDefaultPrettyPrinter()
	                                                              .writeValueAsString(responseDeserialized));
	        } catch (Exception e) {
	            // Log any exceptions during deserialization
	            System.err.println("Deserialization failed: " + e.getMessage());
	            System.err.println("Response body: " + response.getBody().asString());
	            e.printStackTrace();
	        }

	        return responseDeserialized;
	        
	    }
	    
	}

	
	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


