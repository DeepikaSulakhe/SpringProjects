package com.inventorymanagement.service;

import java.util.List;

import com.inventorymanagement.dto.ProductDTO;

public interface InventoryProductService {

	ProductDTO addProduct(ProductDTO pdto);
	List<ProductDTO> getAllProducts();
	ProductDTO getProductById(Long id);
	ProductDTO updateProduct(Long id,ProductDTO pdto);
	void deleteProduct(Long id);
}
