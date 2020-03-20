package com.infy.shopping.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.infy.shopping.model.SAppResponseMessage;

@ControllerAdvice
public class SAppsExceptionHandler {

	Logger logger = LoggerFactory.getLogger(SAppsExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<SAppResponseMessage> exceptionHandler(Exception e) {
		
		SAppResponseMessage response = new SAppResponseMessage(HttpStatus.SERVICE_UNAVAILABLE.value(), e.getMessage());
		logger.error("Exception occurred", e);
		return ResponseEntity.status(HttpStatus.OK).body(response);
		
	}
	
	@ExceptionHandler(SAppsException.class)
	public ResponseEntity<SAppResponseMessage> sAppExceptionHandler(SAppsException e) {
		
		SAppResponseMessage response = new SAppResponseMessage(e.getResponseCode(), e.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
}
