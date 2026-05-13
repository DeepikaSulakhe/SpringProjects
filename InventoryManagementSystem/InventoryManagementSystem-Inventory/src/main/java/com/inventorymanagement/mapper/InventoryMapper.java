package com.inventorymanagement.mapper;

import com.inventorymanagement.dto.InventoryDTO;
import com.inventorymanagement.entity.Inventory;

public class InventoryMapper {
	
	public static InventoryDTO mapToDTO(Inventory invent) {
		InventoryDTO inventDto = new InventoryDTO();
		inventDto.setId(invent.getId());
		inventDto.setProductId(invent.getProductId());
		inventDto.setQuantity(invent.getQuantity());
		inventDto.setWareHouseLocation(invent.getWareHouseLocation());
		inventDto.setStockStatus(invent.getStockStatus());
		
		return inventDto;
		
	}
	
	public static Inventory mapToEntity(InventoryDTO inventDto) {
		Inventory invent = new Inventory();
		invent.setId(inventDto.getId());
		invent.setProductId(inventDto.getProductId());
		invent.setQuantity(inventDto.getQuantity());
		invent.setWareHouseLocation(inventDto.getWareHouseLocation());
		invent.setStockStatus(inventDto.getStockStatus());
		
		return invent;
		
	}
	
	

}
