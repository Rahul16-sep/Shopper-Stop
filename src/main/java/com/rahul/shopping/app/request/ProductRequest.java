package com.rahul.shopping.app.request;

import java.math.BigDecimal;

import com.rahul.shopping.app.model.Category;


public class ProductRequest {
	
	private String name;
	private String brand;
	private String description;
	private BigDecimal price;
	private int inventory;
	private Category category;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public int getInventory() {
		return inventory;
	}
	
	public void setInventory(int inventory) {
		this.inventory = inventory;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}

	public ProductRequest(String name, String brand, String description, BigDecimal price, int inventory,
			Category category) {
		super();
		this.name = name;
		this.brand = brand;
		this.description = description;
		this.price = price;
		this.inventory = inventory;
		this.category = category;
	}
	
	
	public ProductRequest() {
		
	}
	
	
	
	

}
