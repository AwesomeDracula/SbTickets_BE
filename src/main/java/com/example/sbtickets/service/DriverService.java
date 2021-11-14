package com.example.sbtickets.service;

import com.example.sbtickets.bean.DriverBean;
import com.example.sbtickets.entity.Driver;
import com.example.sbtickets.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DriverService implements DriverImplement{

    @Autowired
    DriverRepository driverRepository;
    @Override
    public List<Driver> getDriver() {
        List<Driver> listDriver = driverRepository.findAll();
        return  listDriver;
    }


    @Override
    public Driver findDriver(String name) {
        return null;
    }

    @Override
    public Driver createDriver(Driver driver) {
        Driver newDriver = driverRepository.save(driver);
        return newDriver;
    }

    @Override
    public void updateDriver(Integer id, Driver driver) {
        Optional<Driver> dbDriver = driverRepository.findById(id);
        Driver foundDriver = dbDriver.get();
        foundDriver.setAddress(driver.getAddress());
        foundDriver.setCodeLicense(driver.getCodeLicense());
        foundDriver.setDob(driver.getDob());
        foundDriver.setImage(driver.getImage());
        foundDriver.setSeniority(driver.getSeniority());
        foundDriver.setNationalId(driver.getNationalId());
        foundDriver.setTypeLicense(driver.getTypeLicense());
        foundDriver.setName(driver.getName());
        driverRepository.save(foundDriver);
        return;
    }

    @Override
    public void deleteDriver(Integer id) {
        driverRepository.deleteById(id);
        return;
    }
}
