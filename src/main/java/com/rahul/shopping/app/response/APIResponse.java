package com.rahul.shopping.app.response;

public class APIResponse {
	
	private String message;
	
	private Object data;
	

	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public Object getData() {
		return data;
	}


	public void setData(Object data) {
		this.data = data;
	}


	public APIResponse() {
		super();
	}


	public APIResponse(String message, Object data) {
		super();
		this.message = message;
		this.data = data;
	}
	
	

}
