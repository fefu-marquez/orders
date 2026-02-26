package com.workia.orders.models;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String name;
    private double price;
}
