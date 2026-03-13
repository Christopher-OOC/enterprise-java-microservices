package org.javalord.inventoryservice.controller;

import lombok.RequiredArgsConstructor;
import org.javalord.inventoryservice.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(
            @RequestParam String skuCode,
            @RequestParam Integer quantity) {
        return  inventoryService.isInStock(skuCode, quantity);
    }

}
