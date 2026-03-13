package org.javalord.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inventory", url = "http://inventory-service:8003")
public interface InventoryClient {

    @RequestMapping(method = RequestMethod.POST, value = "/api/v1/inventory")
        boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity);



}
