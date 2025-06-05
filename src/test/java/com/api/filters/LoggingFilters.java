package com.api.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class LoggingFilters implements Filter {
	private static final Logger logger = LogManager.getLogger(LoggingFilters.class);
	
	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {
		// consist of all the information about the request so log the request
		logRequest(requestSpec);
		Response response  = ctx.next(requestSpec, responseSpec); //Request is going to be executed
		//Log Response
		logResponse(response);
		return response; // test for assertion
	}
	
	public void logRequest(FilterableRequestSpecification requestSpec) {
		logger.info("BASE URI :"+ requestSpec.getBaseUri());
		logger.info("REQUEST HEADER :"+ requestSpec.getHeaders());
		logger.info("BODY/PAYLOAD :"+ requestSpec.getBody());
		}
	public void logResponse(Response response) {
		logger.info("STATUS CODE :"+ response.getStatusCode());
		logger.info("REQUEST HEADER :"+ response.getHeaders());
		logger.info("BODY/PAYLOAD :"+ response.getBody().prettyPrint());
	}
}
