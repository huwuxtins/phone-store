package com.example.ecommerce.models;

import com.example.ecommerce.models.pk.OrderDetailKey;
import jakarta.persistence.*;

@Entity
@Table(name = "order_detail")
public class OrderDetail {
    @EmbeddedId
    private OrderDetailKey id;

    @JoinColumn(name = "order_id")
    @MapsId("order_id")
    @ManyToOne
    private Order order;

    @JoinColumn(name = "phone_id")
    @MapsId("phone_id")
    @ManyToOne
    private Phone phone;

    private int quantity;
}
