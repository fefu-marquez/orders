package com.workia.orders.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workia.orders.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    public Product findByName(String name);
}
