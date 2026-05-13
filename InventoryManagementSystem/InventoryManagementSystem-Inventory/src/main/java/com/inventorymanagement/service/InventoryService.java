package com.inventorymanagement.service;

import java.util.List;

import com.inventorymanagement.dto.InventoryDTO;

public interface InventoryService {
	
	InventoryDTO addInventory(InventoryDTO idto);
	List<InventoryDTO> getAllInventory();
	InventoryDTO getInventoryByProductId(Long productId);
	InventoryDTO updateInventoryById(Long id, InventoryDTO idto);
	void deleteInventory(Long id);
	String reduceStock(Long productId, Integer quantity);

}
