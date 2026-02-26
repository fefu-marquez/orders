package com.workia.orders.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    private Double partialTotalPrice;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Order order;

    public Double calculatePartialTotalPrice() {
        Double price = 0.0;
        
        if (product != null && quantity != null) {
            price = product.getPrice() * quantity;
        }

        return price;
    }
}
