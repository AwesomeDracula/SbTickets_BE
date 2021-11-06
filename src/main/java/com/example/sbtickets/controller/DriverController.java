package com.example.sbtickets.controller;

import com.example.sbtickets.bean.AuthenticationBean;
import com.example.sbtickets.common.UrlConst;
import com.example.sbtickets.entity.Driver;
import com.example.sbtickets.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DriverController {

    @Autowired
    DriverService driverService;

    @RequestMapping(value = UrlConst.GET_DRIVER, method = RequestMethod.GET)
    public ResponseEntity<List<Driver>> getDriver() {
        List<Driver> result = new ArrayList<>();
        try {
            result = driverService.getDriver();
        }
        catch (Exception ex){
            return new ResponseEntity<List<Driver>>(result, HttpStatus.FAILED_DEPENDENCY);
        }
        return new ResponseEntity<List<Driver>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = UrlConst.FIND_DRIVER, method = RequestMethod.GET)
    public ResponseEntity<Driver> findDriver(@RequestBody String name) {
        Driver result = new Driver();
        try {
            result = driverService.findDriver(name);
        }
        catch (Exception ex){
            return new ResponseEntity<Driver>(result, HttpStatus.FAILED_DEPENDENCY);
        }
        return new ResponseEntity<Driver>(result, HttpStatus.OK);
    }

}
