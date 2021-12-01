package com.example.sbtickets.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tripbus_customer")
public class TripBusCustomer implements Serializable {
    @Id
    @Column(name = "tripbus_id")
    private Integer tripbusId;
    @Id
    @Column(name = "customer_id")
    private Integer customerId;
    @Column(name = "role_car")
    private Integer roleCar; // number of seat on tripbus

    public TripBusCustomer() {
    }

    public Integer getTripbusId() {
        return tripbusId;
    }

    public void setTripbusId(Integer tripbusId) {
        this.tripbusId = tripbusId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getRoleCar() {
        return roleCar;
    }

    public void setRoleCar(Integer roleCar) {
        this.roleCar = roleCar;
    }
}
