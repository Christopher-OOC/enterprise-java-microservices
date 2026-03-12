package org.javalord.inventoryservice.service;

import lombok.RequiredArgsConstructor;
import org.javalord.inventoryservice.repository.InventoryRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public boolean isInStock(String skuCode, Integer quantity) {
        return inventoryRepository.findBySkuCodeAndQuantityIsGreaterThanEquals(skuCode, quantity);
    }

}
