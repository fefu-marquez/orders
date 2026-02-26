package com.workia.orders.dtos.orders;

import java.util.List;

import lombok.Data;

@Data
public class CreateOrderBody {
    private CreateOrderBodyClient client;
    private List<CreateOrderBodyProduct> products;
}