package com.web3.ecommerceback.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "car")
public class Car implements Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE)

    private long id ;
   @Column(nullable = false , length = 100)
    private String name;

    private String description;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String color;

    @Column(name = "motor_type" , nullable = false)
    private String motorType;

    @Column(nullable = false)
    private int power;

    @Column(name = "place_number" , nullable = false)
    private int placeNumber;

    @Column(nullable = false , columnDefinition = "boolean default false")
    private boolean status; //pinned or not

    @Column(nullable = false)
    private double price;

    private String type;

    @ElementCollection
    private List<String> images;
}
