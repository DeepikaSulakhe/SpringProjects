package com.inventorymanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductDTO {
	
	private Long id;
	
	@NotBlank(message = "Product name is required")
	@Size(min=3,max=50,message="Name must be between 3 abd 50 Characters")
	private String name;
	
	@NotBlank(message="Category is required")
	private String category;
	
	@NotNull(message="Price is required")
	@Positive(message = "Price must be greater than 0")
	private Double price;
	
	public ProductDTO() {
		
	}

	public ProductDTO(String name, String category, Double price) {
		this.name = name;
		this.category = category;
		this.price = price;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	

}
