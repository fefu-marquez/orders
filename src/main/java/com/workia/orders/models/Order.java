package com.workia.orders.models;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;
    @ManyToOne
    private Client client;
    private Double totalPrice;
    private Date createdAt;

    public Double calculateTotalPrice() {
        Double total = 0.0;

        if (orderDetails != null) {
            for (OrderDetail detail : orderDetails) {
                total += detail.calculatePartialTotalPrice();
            }
        }

        return total;
    }
}
