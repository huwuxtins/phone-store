package com.example.ecommerce.models;

import com.example.ecommerce.models.pk.PhoneKey;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "phone_storage")
public class PhoneStorage {
    @EmbeddedId
    private PhoneKey id;

    @JoinColumn(name = "phone_id")
    @MapsId("phone_id")
    @ManyToOne
    private Phone phone;

    @JoinColumn(name = "color_id")
    @MapsId("color_id")
    @ManyToOne
    private Color color;

    @JoinColumn(name = "capacity_id")
    @MapsId("capacity_id")
    @ManyToOne
    private Capacity capacity;

    private int quantity;

    private Date updatedAt;
}
