package org.javalord.inventoryservice.repository;

import org.javalord.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    boolean findBySkuCodeAndQuantityIsGreaterThanEquals(String skuCode, Integer quantity);
}
