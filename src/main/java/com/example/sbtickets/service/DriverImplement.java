package com.example.sbtickets.service;

import com.example.sbtickets.bean.DriverBean;
import com.example.sbtickets.entity.Driver;

import java.util.List;

public interface DriverImplement {
    public List<Driver> getDriver();
    public Driver findDriver(String name);
    public Driver createDriver(Driver driver);
    public void updateDriver(Integer id, Driver driver);
    public void deleteDriver(Integer id);
}
