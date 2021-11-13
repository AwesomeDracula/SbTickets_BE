package com.example.sbtickets.controller;

import com.example.sbtickets.common.UrlConst;
import com.example.sbtickets.entity.Bus;
import com.example.sbtickets.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@RestController
public class BusController {

    @Autowired
    BusService busService;

    @RequestMapping(value = UrlConst.GET_BUS, method = RequestMethod.GET)
    public ResponseEntity<List<Bus>> getBus() {
        List<Bus> result = new ArrayList<>();
        try {
            result = busService.getBus();
        }
        catch (Exception ex){
            return new ResponseEntity<List<Bus>>(result, HttpStatus.FAILED_DEPENDENCY);
        }
        return new ResponseEntity<List<Bus>>(result, HttpStatus.OK);
    }
//    @RequestMapping(value = "/addNewBus", method = RequestMethod.POST)
//    public String newBus(Bus bus) {
//
//        BusService.save(bus);
//        return ("redirect:/getBus");
//
//    }
//
//    @RequestMapping(value = "/addNewBus", method = RequestMethod.GET)
//    public ModelAndView addNewBus() {
//
//        Bus bus = new Bus();
//        return new ModelAndView("newBus", "form", bus);
//
//    }
}
