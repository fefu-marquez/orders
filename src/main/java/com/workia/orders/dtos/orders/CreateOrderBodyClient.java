package com.workia.orders.dtos.orders;

import lombok.Data;

@Data
public class CreateOrderBodyClient {
    private String name;
    private String email;
}
