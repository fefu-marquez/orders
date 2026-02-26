package com.workia.orders.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;

import com.workia.orders.dtos.orders.CreateOrderBody;
import com.workia.orders.dtos.orders.CreateOrderResponse;
import com.workia.orders.services.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;


@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/")
    public CreateOrderResponse postMethodName(@RequestBody CreateOrderBody newOrder) {
        if (newOrder.getProducts() == null || newOrder.getProducts().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Products list cannot be empty");
        } else if (newOrder.getClient() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client information is required");
        }

        if (newOrder.getProducts().stream().anyMatch(product -> product.getQuantity() == null || product.getQuantity() <= 0)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product quantity must be greater than zero");
        }

        return this.orderService.createOrder(newOrder);
    }
    
}
