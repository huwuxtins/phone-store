package com.example.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name="color")
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany(mappedBy = "color", fetch= FetchType.EAGER, cascade = CascadeType.ALL)
    private List<PhoneStorage> phoneStorages;

    @OneToMany(mappedBy = "color", fetch= FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonBackReference
    private List<PhoneDetail> phoneDetails;

    public Color(String name) {
        this.name = name;
    }
}
