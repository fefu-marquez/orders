package com.workia.orders.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workia.orders.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
}
