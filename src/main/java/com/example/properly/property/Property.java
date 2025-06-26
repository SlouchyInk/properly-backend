package com.example.properly.property;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Property implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private String unitNumber;

    public Property() {}

    public Property(String address, String unitNumber) {
        this.address = address;
        this.unitNumber = unitNumber;
    }

    public Long getId() {return id;}
    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {return address;}
    public void setAddress(String address) {
        this.address = address;
    }

    public String getUnitNumber() {return unitNumber;}
    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }
}
