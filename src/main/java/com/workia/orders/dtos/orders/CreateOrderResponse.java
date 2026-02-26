package com.workia.orders.dtos.orders;

import java.util.Date;

import lombok.Data;

@Data
public class CreateOrderResponse {
    private Double total;
    private Date createdAt;
}
