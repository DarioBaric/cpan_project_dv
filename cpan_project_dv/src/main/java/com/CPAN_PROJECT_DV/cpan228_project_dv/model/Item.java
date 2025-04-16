package com.CPAN_PROJECT_DV.cpan228_project_dv.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "brand", nullable = false)
    private Brand brand;

    @Column(name = "year_created", nullable = false)
    private int yearOfCreation;

    @Column(nullable = false)
    private double price;

    @Column (name = "quantity", nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "distribution_id", nullable = true) 
    private Distribution distribution;

    public Item(Long id, String name, Brand brand, int yearOfCreation, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.yearOfCreation = yearOfCreation;
        this.price = price;
        this.quantity = quantity;
    }
}