package com.workia.orders.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workia.orders.models.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    public Client findByEmail(String email);
}