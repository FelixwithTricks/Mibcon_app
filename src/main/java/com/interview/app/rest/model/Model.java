package com.interview.app.rest.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
@Entity
@Table(name="Model")
public class Model {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;
    @Column(name="ID_Vyrobce")
    private long manufacturerId;
    @Column(name="Nazev")
    private String name;
    @Column(name="Kategorie")
    private String category;
    @Column(name="Cennove_rozpeti")
    private int price;
    @Column(name="Rok_uvedeni_na_trh")
    private Date dateOfMarketIntroduction;
    @Column(name="Vyroba_je_aktivni")
    private boolean activeProduction;
}
