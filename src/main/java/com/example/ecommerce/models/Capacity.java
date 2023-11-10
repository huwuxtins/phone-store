package com.example.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name="capacity")
public class Capacity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int sizeInGB;

    @OneToMany(mappedBy = "capacity", fetch = FetchType.EAGER)
    private List<PhoneStorage> phoneStorages;

    @OneToMany(mappedBy = "capacity", fetch = FetchType.EAGER)
    @JsonBackReference
    private List<PhoneDetail> phoneDetails;

    public Capacity(int sizeInGB) {
        this.sizeInGB = sizeInGB;
    }
}
