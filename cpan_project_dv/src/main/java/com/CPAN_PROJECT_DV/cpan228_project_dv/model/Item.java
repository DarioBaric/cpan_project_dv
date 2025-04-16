package com.CPAN_PROJECT_DV.cpan228_project_dv.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne
    @JoinColumn(name = "distribution_id")
    private Distribution distribution;

    public Distribution getDistribution() {
        return distribution;
    }
    
    public void setDistribution(Distribution distribution) {
        this.distribution = distribution;
    }
    

}
