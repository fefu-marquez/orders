package com.workia.orders.models;

import java.util.List;

import lombok.Data;

@Data
public class Order {
    private Long id;
    private List<OrderDetail> orderDetails;
    private Client client;
    private Double totalPrice;

    public Double calculateTotalPrice() {
        Double total = 0.0;

        if (orderDetails != null) {
            for (OrderDetail detail : orderDetails) {
                total += detail.calculatePartialTotalPrice();
            }
        }

        return total;
    }
}
