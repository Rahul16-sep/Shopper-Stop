package com.rahul.shopping.app.request;

public class CategoryRequest {
	
	private String name;
	
	public CategoryRequest() {
	}
	
	public CategoryRequest(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean findByName(String name2) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
