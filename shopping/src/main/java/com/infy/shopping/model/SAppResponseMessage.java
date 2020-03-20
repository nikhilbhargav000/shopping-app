package com.infy.shopping.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class SAppResponseMessage {

	private int status;
	private String value;
	private String message;
	
	public SAppResponseMessage(int status, String message) {
		this.status = status;
		this.message = message;
	}
}
