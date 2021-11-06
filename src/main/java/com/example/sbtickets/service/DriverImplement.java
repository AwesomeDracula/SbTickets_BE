package com.example.sbtickets.service;

import com.example.sbtickets.entity.Driver;

import java.util.List;

public interface DriverImplement {
    public List<Driver> getDriver();
    public Driver findDriver(String name);
}
