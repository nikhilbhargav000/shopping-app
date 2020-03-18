package com.infy.shopping.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.infy.shopping.model.ResponseMessage;

@ControllerAdvice
public class SAppsExceptionHandler {

	Logger logger = LoggerFactory.getLogger(SAppsExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseMessage> exceptionHandler(Exception e) {
		
		ResponseMessage response = new ResponseMessage(HttpStatus.SERVICE_UNAVAILABLE.value(), e.getMessage());
		logger.error("Exception occurred", e);
		return ResponseEntity.status(HttpStatus.OK).body(response);
		
	}
	
	@ExceptionHandler(SAppsException.class)
	public ResponseEntity<ResponseMessage> sAppExceptionHandler(SAppsException e) {
		
		ResponseMessage response = new ResponseMessage(e.getResponseCode(), e.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
}
