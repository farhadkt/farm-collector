package com.farhad.farmcollector.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "harvests")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Harvest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double actualHarvestAmount;

    @OneToOne
    @JoinColumn(name = "plant_id", nullable = false) //it is assumed each planting has exactly one corresponding harvest
    private Plant plant;
}
