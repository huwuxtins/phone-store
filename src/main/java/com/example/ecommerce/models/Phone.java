package com.example.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "phone")
public class Phone {
    @Id
    private String id;

    private String name;
    private String description;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "brand")
    private Brand brand;

    @OneToMany(mappedBy = "phone", fetch = FetchType.EAGER)
    private List<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "phone", fetch = FetchType.EAGER)
    private List<PhoneRating> ratings;

    @OneToOne
    @JoinColumn(name = "specification_id")
    private Specification spec;

    @OneToMany(mappedBy = "phone", fetch = FetchType.EAGER)
    private List<PhoneStorage> phoneStorages;

    @OneToMany(mappedBy = "phone", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<PhoneDetail> phoneDetails;

    @Override
    public String toString() {
        return "Phone{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", brand=" + brand +
                '}';
    }
}
