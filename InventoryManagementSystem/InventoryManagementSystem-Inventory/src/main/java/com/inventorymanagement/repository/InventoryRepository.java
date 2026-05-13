package com.inventorymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.inventorymanagement.entity.Inventory;
import java.util.Optional;


@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long>{
	
	@Query("select i from Inventory i where i.productId = ?1 ")
	Optional<Inventory> findByProductId(Long productId);

}
