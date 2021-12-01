package com.example.sbtickets.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "line_bus")
public class LineBus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @OneToOne
    @JoinColumn(name = "first_id")
    private TripbusAddress firtPoint;
    @OneToOne
    @JoinColumn(name = "last_id")
    private TripbusAddress lastPoint;
    @Column(name = "length")
    private Integer length;
    @Column(name = "complexity")
    private Integer complexity;

    public LineBus () {

    }

    public LineBus(Integer id, TripbusAddress firtPoint, TripbusAddress lastPoint, Integer length, Integer complexity) {
        this.id = id;
        this.firtPoint = firtPoint;
        this.lastPoint = lastPoint;
        this.length = length;
        this.complexity = complexity;
    }

    public LineBus(TripbusAddress firtPoint, TripbusAddress lastPoint, Integer length, Integer complexity) {
        this.firtPoint = firtPoint;
        this.lastPoint = lastPoint;
        this.length = length;
        this.complexity = complexity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TripbusAddress getFirtPoint() {
        return firtPoint;
    }

    public void setFirtPoint(TripbusAddress firtPoint) {
        this.firtPoint = firtPoint;
    }

    public TripbusAddress getLastPoint() {
        return lastPoint;
    }

    public void setLastPoint(TripbusAddress lastPoint) {
        this.lastPoint = lastPoint;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getComplexity() {
        return complexity;
    }

    public void setComplexity(Integer complexity) {
        this.complexity = complexity;
    }
}