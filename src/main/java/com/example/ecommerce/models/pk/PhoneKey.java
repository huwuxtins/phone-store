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
public class PhoneKey implements Serializable {
    @Column(name = "phone_id")
    private String phoneId;

    @Column(name = "color_id")
    private long colorId;

    @Column(name = "capacity_id")
    private long capacityId;
}
