package com.inventorymanagement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.inventorymanagement.dto.InventoryDTO;
import com.inventorymanagement.dto.ProductResponseDTO;
import com.inventorymanagement.entity.Inventory;
import com.inventorymanagement.mapper.InventoryMapper;
import com.inventorymanagement.repository.InventoryRepository;

@Service
public class InventoryServiceImpl  implements InventoryService{
	
	@Autowired
	private InventoryRepository inventRepo;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public InventoryDTO addInventory(InventoryDTO idto) {
		
		ProductResponseDTO product = restTemplate.getForObject("http://INVENTORYMANAGEMENTSYSTEM-PRODUCT/productapi/products/"
				+ idto.getProductId(),ProductResponseDTO.class);
		if(product == null) {
			throw new RuntimeException("Product Not Found By Id in Inventory");
		}
		Inventory invent = InventoryMapper.mapToEntity(idto);
		
		if(idto.getQuantity() > 0) {
			invent.setStockStatus("In Stcok");
		}else {
			invent.setStockStatus("Out of Stcok");
		}
		return InventoryMapper.mapToDTO(inventRepo.save(invent));
	}

	@Override
	public List<InventoryDTO> getAllInventory() {
		//List<Inventory> invents = inventRepo.findAll();
		return inventRepo.findAll().stream().map(InventoryMapper::mapToDTO).collect(Collectors.toList());
	}

	@Override
	public InventoryDTO getInventoryByProductId(Long productId) {
		Inventory invent = inventRepo.findByProductId(productId).orElseThrow(() -> new RuntimeException("Product Not Found in Inventory"));
		return InventoryMapper.mapToDTO(invent);
	}

	@Override
	public InventoryDTO updateInventoryById(Long id, InventoryDTO idto) {
		Inventory invent = inventRepo.findById(id).orElseThrow(() -> new RuntimeException("Inventory Record Not Found"));
		invent.setProductId(idto.getProductId());
		invent.setQuantity(idto.getQuantity());
		invent.setWareHouseLocation(idto.getWareHouseLocation());
		if(idto.getQuantity() > 0) {
			invent.setStockStatus("In Stock");
		}else {
			invent.setStockStatus("Out of Stock");
		}
		
		
		return InventoryMapper.mapToDTO(inventRepo.save(invent));
	}

	@Override
	public void deleteInventory(Long id) {
		inventRepo.deleteById(id);
		
	}

	@Override
	public String reduceStock(Long productId, Integer quantity) {
		Inventory invent = inventRepo.findByProductId(productId).orElseThrow(() -> new RuntimeException("Product Not Found in Inventory"));
		
		if(invent.getQuantity()< quantity) {
			throw new RuntimeException("Insufficient Stock");
		}
		Integer updatedQuantity = invent.getQuantity() - quantity;
		invent.setQuantity(updatedQuantity);
		
		if(invent.getQuantity() == 0) {
			invent.setStockStatus("Out of Stock");
		}
		
		inventRepo.save(invent);
		
		return "Redcued Successfully";
		 
	}

}
