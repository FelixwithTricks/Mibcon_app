package com.interview.app.rest.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
@Entity
@Table(name="Automobil")
public class Car {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;
    @Column(name="ID_modelu")
    private long modelId;
    @Column(name="Barva")
    private String color;
    @Column(name="Vykon")
    private String performance;
    @Column(name="Spotreba")
    private String consumption;
    @Column(name="Datum_vytvoreni")
    private Date creationDate;
    @Column(name="Automobil_je_pojizdny")
    private Boolean isMobile;
}
