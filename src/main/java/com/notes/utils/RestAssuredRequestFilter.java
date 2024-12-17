package com.notes.utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class RestAssuredRequestFilter implements Filter {
	private static final Logger logger = LogManager.getLogger(RestAssuredRequestFilter.class);

	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext context) {
		Response response= context.next(requestSpec, responseSpec);
		logger.info("-------------------------------------------------------------------------");
		logger.info(" Request Method = " + requestSpec.getMethod() + 
				"\n Request URI = " + requestSpec.getURI() + 
				"\n Request Header = " + requestSpec.getHeaders() + 
				"\n Request Body = " + requestSpec.getBody() + 
				"\n Response Status = " + response.getStatusCode() +
				"\n Response Header Accept = " +response.getHeader("accept") +
				"\n Response Body = " +response.getBody());
		logger.info("-------------------------------------------------------------------------");
		return response;
	}
	
}
