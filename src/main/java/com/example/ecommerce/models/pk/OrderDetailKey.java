package com.example.ecommerce.models.pk;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class OrderDetailKey implements Serializable {
    @Column(name = "order_id")
    private long orderId;

    @Column(name = "product_id")
    private long productId;
}
