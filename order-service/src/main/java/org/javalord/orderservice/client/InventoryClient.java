package org.javalord.orderservice.client;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

public interface InventoryClient {


    @PostExchange(value = "/api/v1/inventory")
    boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity);


}
