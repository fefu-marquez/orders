package com.workia.orders.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workia.orders.dtos.orders.CreateOrderBody;
import com.workia.orders.dtos.orders.CreateOrderBodyClient;
import com.workia.orders.dtos.orders.CreateOrderBodyProduct;
import com.workia.orders.dtos.orders.CreateOrderResponse;
import com.workia.orders.models.Client;
import com.workia.orders.models.Order;
import com.workia.orders.models.OrderDetail;
import com.workia.orders.models.Product;
import com.workia.orders.repositories.ClientRepository;
import com.workia.orders.repositories.OrderRepository;
import com.workia.orders.repositories.ProductRepository;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ProductRepository productRepository;

    private Client createOrGetClient(CreateOrderBodyClient client) {
        var dbClient = this.clientRepository.findByEmail(client.getEmail());

        if (dbClient == null) {
            Client newClient = new Client();
            newClient.setName(client.getName());
            newClient.setEmail(client.getEmail());

            dbClient = this.clientRepository.save(newClient);
        }

        return dbClient;
    }

    private Product getProduct(CreateOrderBodyProduct product) {
        return this.productRepository.findByName(product.getName());
    }

    private OrderDetail createOrderDetail(CreateOrderBodyProduct product) {
        var dbProduct = this.getProduct(product);

        OrderDetail detail = new OrderDetail();
        detail.setProduct(dbProduct);
        detail.setQuantity(product.getQuantity());
        detail.calculatePartialTotalPrice();

        return detail;
    }

    public CreateOrderResponse createOrder(CreateOrderBody order) {
        Order newOrder = new Order();
        
        var client = this.createOrGetClient(order.getClient());
        newOrder.setClient(client);
        
        newOrder.setCreatedAt(new java.sql.Date(System.currentTimeMillis()));
        
        newOrder.setOrderDetails(order.getProducts().stream().map(product -> this.createOrderDetail(product)).toList());
        newOrder.calculateTotalPrice();

        this.orderRepository.save(newOrder);

        CreateOrderResponse response = new CreateOrderResponse();
        response.setTotal(newOrder.getTotalPrice());
        response.setCreatedAt(newOrder.getCreatedAt());
        
        return response;
    }
    
}
