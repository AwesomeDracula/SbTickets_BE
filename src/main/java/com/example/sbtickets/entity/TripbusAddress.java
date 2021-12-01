package com.example.sbtickets.entity;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tripbus_address")
public class TripbusAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "address")
    private String address;

    public TripbusAddress(Integer id, String address) {
        this.id = id;
        this.address = address;
    }

    public TripbusAddress() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
