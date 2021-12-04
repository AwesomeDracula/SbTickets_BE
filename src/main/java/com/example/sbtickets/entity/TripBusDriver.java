package com.example.sbtickets.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tripbus_driver")
public class TripBusDriver implements Serializable {
    @Id
    @Column(name = "tripbus_id")
    private Integer tripbusId;
    @Id
    @Column(name = "driver_id")
    private Integer driverId;
    @Column(name = "role_car")
    private String roleCar;
    @Column(name = "wages")
    private Double  wages;
    @Column(name = "date")
    private Date date;
    @Column(name = "scrap_dateTime")
    private String scrapDateTime;

    public TripBusDriver() {
    }

    public String getScrapDateTime() {
        return scrapDateTime;
    }

    public void setScrapDateTime(String scrapDateTime) {
        this.scrapDateTime = scrapDateTime;
    }

    public Integer getTripbusId() {
        return tripbusId;
    }

    public void setTripbusId(Integer tripbusId) {
        this.tripbusId = tripbusId;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public String getRoleCar() {
        return roleCar;
    }

    public void setRoleCar(String roleCar) {
        this.roleCar = roleCar;
    }

    public Double getWages() {
        return wages;
    }

    public void setWages(Double wages) {
        this.wages = wages;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
