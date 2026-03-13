package org.javalord.orderservice.service;

import lombok.RequiredArgsConstructor;
import org.javalord.orderservice.client.InventoryClient;
import org.javalord.orderservice.dto.OrderRequest;
import org.javalord.orderservice.model.Order;
import org.javalord.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient  inventoryClient;

    public void placeOrder(OrderRequest orderRequest) {
        var isInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());

        if (isInStock) {
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequest.price());
            order.setQuantity(orderRequest.quantity());
            order.setSkuCode(orderRequest.skuCode());

            orderRepository.save(order);
        }
        else {
            throw new RuntimeException("Order product Not In Stock");
        }
    }

}
