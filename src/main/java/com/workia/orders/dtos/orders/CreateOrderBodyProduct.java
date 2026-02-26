package com.workia.orders.dtos.orders;

import lombok.Data;

@Data
public class CreateOrderBodyProduct {
    private String name;
    private Integer quantity;
}
