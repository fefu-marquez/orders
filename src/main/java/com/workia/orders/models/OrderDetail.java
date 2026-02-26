package com.workia.orders.models;

import lombok.Data;

@Data
public class OrderDetail {
    private Long id;
    private Integer quantity;
    private Double partialTotalPrice;
    private Product product;

    public Double calculatePartialTotalPrice() {
        Double price = 0.0;
        
        if (product != null && quantity != null) {
            price = product.getPrice() * quantity;
        }

        return price;
    }
}
