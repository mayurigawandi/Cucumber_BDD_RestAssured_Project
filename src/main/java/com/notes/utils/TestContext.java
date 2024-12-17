package com.notes.utils;

import java.util.HashMap;
import java.util.Map;
import com.github.dzieciou.testing.curl.CurlRestAssuredConfigFactory;
import com.github.dzieciou.testing.curl.Options;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestContext {
	 
		public Response response;
		public Map<String,Object> sessionData = new HashMap<String,Object>();
		private static final String Content_Type = ReadProperties.getProperty("content.type");
		//private static final String accept = ReadProperties.getProperty("accept");
		
		public RequestSpecification requestSetup() {
			RestAssured.reset();
			Options options = Options.builder().logStacktrace().build();
			RestAssuredConfig config = CurlRestAssuredConfigFactory.createConfig(options);
			RestAssured.baseURI = ReadProperties.getProperty("baseURL");
			return RestAssured.given()
					.config(config)
					.filter(new RestAssuredRequestFilter())
					.contentType(Content_Type);
			
					//.accept(accept);
			}
		
	}
	
	
