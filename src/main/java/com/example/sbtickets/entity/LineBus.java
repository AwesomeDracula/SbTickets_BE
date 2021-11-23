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
    @Column(name = "first_point")
    private String firstPoint;
    @Column(name = "last_point")
    private String lastPoint;
    @Column(name = "length")
    private Integer length;
    @Column(name = "complexity")
    private Integer complexity;

    public LineBus () {

    }

    public LineBus(String firstPoint, String lastPoint, Integer length, Integer complexity)
    {
        this.firstPoint = firstPoint;
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

    public String getFirstPoint() {
        return firstPoint;
    }

    public void setFirstPoint(String firstPoint) {
        this.firstPoint = firstPoint;
    }

    public String getLastPoint() {
        return lastPoint;
    }

    public void setLastPoint(String lastPoint) {
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
