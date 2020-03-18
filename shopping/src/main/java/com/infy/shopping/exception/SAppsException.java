package com.infy.shopping.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class SAppsException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int responseCode;
	
	public SAppsException(int responseCode, String message) {
		super(message);
		this.responseCode = responseCode;
	}
	
}
