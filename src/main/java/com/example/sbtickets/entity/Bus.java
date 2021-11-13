package com.example.sbtickets.entity;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "Bus")
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "carNumber")
    private Integer carNumber;
    @Column(name = "color")
    private String color;
    @Column(name = "manufacturer")
    private String manufacturer;
    @Column(name = "lifeCar")
    private String lifeCar;
    @Column(name = "numberSeats")
    private String numberSeats;
    @Column(name = "yearUse")
    private Integer yearUse;
    @Column(name = "dateMantain")
    private Date dateMantain;

    public Bus() {
    }

    public Bus(Integer carNumber, String color, String manufacturer, String lifeCar, String numberSeats, Integer yearUse, Date dateMantain) {
        this.carNumber = carNumber;
        this.color = color;
        this.manufacturer = manufacturer;
        this.lifeCar = lifeCar;
        this.numberSeats = numberSeats;
        this.yearUse = yearUse;
        this.dateMantain = dateMantain;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(Integer carNumber) {
        this.carNumber = carNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getLifeCar() {
        return lifeCar;
    }

    public void setLifeCar(String lifeCar) {
        this.lifeCar = lifeCar;
    }

    public String getNumberSeats() {
        return numberSeats;
    }

    public void setNumberSeats(String numberSeats) {
        this.numberSeats = numberSeats;
    }

    public Integer getYearUse() {
        return yearUse;
    }

    public void setYearUse(Integer yearUse) {
        this.yearUse = yearUse;
    }

    public Date getDateMantain() {
        return dateMantain;
    }

    public void setDateMantain(Date dateMantain) {
        this.dateMantain = dateMantain;
    }
}
