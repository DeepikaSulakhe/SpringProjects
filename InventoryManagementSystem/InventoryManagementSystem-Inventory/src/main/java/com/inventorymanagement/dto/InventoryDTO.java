package com.inventorymanagement.dto;

public class InventoryDTO {
	
	private Long id;
	private Long productId;
	private Integer quantity;
	private String wareHouseLocation;
	private String 	stockStatus;
	
	public InventoryDTO() {
		
	}

	public InventoryDTO(Long productId, Integer quantity, String wareHouseLocation, String stockStatus) {
		this.productId = productId;
		this.quantity = quantity;
		this.wareHouseLocation = wareHouseLocation;
		this.stockStatus = stockStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getWareHouseLocation() {
		return wareHouseLocation;
	}

	public void setWareHouseLocation(String wareHouseLocation) {
		this.wareHouseLocation = wareHouseLocation;
	}

	public String getStockStatus() {
		return stockStatus;
	}

	public void setStockStatus(String stockStatus) {
		this.stockStatus = stockStatus;
	}
	

}
