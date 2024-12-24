package com.rahul.shopping.app.dto;

import java.math.BigDecimal;
import java.util.List;

public class ProductDTO {
	
	private Long id;
	private String name;
	private String brand;
	private String description;
	private BigDecimal price;
	private int inventory;
	private String categoryName;
	private Long categoryId;
	private List<ImageDTO> imageDTOs;
	
	public ProductDTO() {
		super();
	}
	
	
	public ProductDTO(Long id, String name, String brand, String description, BigDecimal price, int inventory,
			String categoryName, Long categoryId, List<ImageDTO> imageDTOs) {
		super();
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.description = description;
		this.price = price;
		this.inventory = inventory;
		this.categoryName = categoryName;
		this.categoryId = categoryId;
		this.imageDTOs = imageDTOs;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


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


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	public Long getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
	public List<ImageDTO> getImageDTOs() {
		return imageDTOs;
	}


	public void setImageDTOs(List<ImageDTO> imageDTOs) {
		this.imageDTOs = imageDTOs;
	}


	
	
	
}
