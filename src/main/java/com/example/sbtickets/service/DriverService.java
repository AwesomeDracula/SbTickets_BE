package com.example.sbtickets.service;

import com.example.sbtickets.entity.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DriverService implements DriverImplement{

//    @Autowired
//    DriverRepository driverRepository;
    @Override
    public List<Driver> getDriver() {
        //driverRepository.findAll();
        List<Driver> listDriver = new ArrayList<>();
        listDriver.add(new Driver(1L,"Trung"));
        listDriver.add(new Driver(2L,"Cuong"));
        listDriver.add(new Driver(3L,"Ha"));
        listDriver.add(new Driver(3L,"Son"));
        return  listDriver;
    }


    @Override
    public Driver findDriver(String name) {
        return new Driver(1L, "Trung");
    }
}
