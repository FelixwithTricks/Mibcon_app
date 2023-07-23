package com.interview.app.rest.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="Vyrobce")
public class Manufacturer {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;
    @Column(name="Nazev")
    private String name;
    @Column(name="Ulice")
    private String street;
    @Column(name="Mesto")
    private String city;
    @Column(name="PSC")
    private int zipCode;
    @Column(name="Zeme")
    private String country;
}
