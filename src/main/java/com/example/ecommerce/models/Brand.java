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
@Table(name="brand")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany(mappedBy = "brand", fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Phone> phones;

    public Brand(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "name='" + name + '\'' +
                '}';
    }
}
