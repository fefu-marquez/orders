package com.workia.orders.models;

import lombok.Data;

@Data
public class Client {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}