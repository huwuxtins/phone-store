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
public class PhoneRatingKey implements Serializable {
    @Column(name = "user_id")
    private long userId;

    @Column(name = "product_id")
    private long productId;
}
