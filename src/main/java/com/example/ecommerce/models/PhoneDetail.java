package com.example.ecommerce.models;

import com.example.ecommerce.models.pk.PhoneKey;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "phone_detail")
public class PhoneDetail {
    @EmbeddedId
    private PhoneKey id;

    @JoinColumn(name = "phone_id")
    @MapsId("phone_id")
    @ManyToOne
    @JsonBackReference
    private Phone phone;

    @JoinColumn(name = "color_id")
    @MapsId("color_id")
    @ManyToOne
    @JsonManagedReference
    private Color color;

    @JoinColumn(name = "capacity_id")
    @MapsId("capacity_id")
    @ManyToOne
    @JsonManagedReference
    private Capacity capacity;

    private String img;

    private double price;

    private Date createdAt;

    private Date updatedAt;

    public PhoneDetail(PhoneKey id, String img, double price) {
        this.id = id;
        this.img = img;
        this.price = price;
    }
}
