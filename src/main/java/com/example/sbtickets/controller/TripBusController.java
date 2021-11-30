package com.example.sbtickets.controller;

import com.example.sbtickets.bean.TripBusBean;
import com.example.sbtickets.bean.TripBusDriverBean;
import com.example.sbtickets.bean.WrapperResponse;
import com.example.sbtickets.common.UrlConst;
import com.example.sbtickets.dao.TripBusDriverDao;
import com.example.sbtickets.entity.Bus;
import com.example.sbtickets.entity.LineBus;
import com.example.sbtickets.entity.TripBus;
import com.example.sbtickets.entity.TripBusDriver;
import com.example.sbtickets.service.BusService;
import com.example.sbtickets.service.LineBusService;
import com.example.sbtickets.service.TripBusService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class TripBusController {

    private static final Logger logger = Logger.getLogger(TripBusController.class);

    @Autowired
    BusService busService;

    @Autowired
    LineBusService lineBusService;

    @Autowired
    TripBusService tripBusService;

    @Autowired
    TripBusDriverDao tripBusDriverDao;

    @RequestMapping(value = UrlConst.HOMEADIM.CREATE_TRIP_BUS, method = RequestMethod.POST)
    public ResponseEntity<WrapperResponse> creatTripBus(@RequestBody TripBusBean tripBusBean) {
        WrapperResponse result = new WrapperResponse();
        try {
            Bus bus = busService.findBus(tripBusBean.getBusId()).get();
            LineBus lineBus = lineBusService.findLineBus(tripBusBean.getLineBusId());
            TripBus tripBus = new TripBus();
            tripBus.setBus(bus);
            tripBus.setLineBus(lineBus);
            tripBus.setNumberGuest(tripBusBean.getNumberGuest());
            tripBus.setTimeTrip(tripBusBean.getTimeTrip());
            tripBus.setPriceTrip(tripBusBean.getPriceTrip());
            tripBus = tripBusService.createTripBus(tripBus);
            if(tripBus != null){
                TripBusDriver driverBus = new TripBusDriver();
                driverBus.setDriverId(tripBusBean.getDriverId());
                driverBus.setTripbusId(tripBus.getId());
                driverBus.setWages(1200.0);
                driverBus.setDate(tripBus.getTimeTrip());
                driverBus.setRoleCar("1");
                tripBusDriverDao.insertTripBusDriver(driverBus);

                TripBusDriver assistantDriver = new TripBusDriver();
                assistantDriver.setDriverId(tripBusBean.getAssistantBusId());
                assistantDriver.setTripbusId(tripBus.getId());
                assistantDriver.setWages(600.0);
                assistantDriver.setDate(tripBus.getTimeTrip());
                assistantDriver.setRoleCar("0");
                tripBusDriverDao.insertTripBusDriver(assistantDriver);

                result.setMsg("Creat TripBus Sucessfull");
                result.setStatus(HttpStatus.OK.value());
            }
            else{
                return new ResponseEntity<WrapperResponse>(result, HttpStatus.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            result.setMsg(ex.getMessage());
            result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<WrapperResponse>(result, HttpStatus.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
        return new ResponseEntity<WrapperResponse>(result, HttpStatus.valueOf(HttpStatus.OK.value()));
    }

    @RequestMapping(value = UrlConst.HOMEADIM.DELETE_TRIP_BUS, method = RequestMethod.PUT)
    public ResponseEntity<WrapperResponse> deleteTripBus(@PathVariable("id") Integer id) {
        WrapperResponse result = new WrapperResponse();
        try {
            tripBusService.deleteTripBus(id);
            tripBusDriverDao.deleteTripBusDriver(id);
            result.setMsg("Delete TripBus Sucessfull");
            result.setStatus(HttpStatus.OK.value());
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
            result.setMsg(ex.getMessage());
            result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<WrapperResponse>(result, HttpStatus.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
        return new ResponseEntity<WrapperResponse>(result, HttpStatus.valueOf(HttpStatus.OK.value()));
    }

    @RequestMapping(value = UrlConst.HOMEADIM.FIND_TRIP_BUS, method = RequestMethod.GET)
    public ResponseEntity<WrapperResponse> findTripBus(@PathVariable("id") Integer id) {
        WrapperResponse result = new WrapperResponse();
        try {
            result.setMsg("Delete TripBus Sucessfull");
            result.setStatus(HttpStatus.OK.value());
            result.setBody(tripBusService.findTripBus(id));
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
            result.setMsg(ex.getMessage());
            result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<WrapperResponse>(result, HttpStatus.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
        return new ResponseEntity<WrapperResponse>(result, HttpStatus.valueOf(HttpStatus.OK.value()));
    }

    @RequestMapping(value = UrlConst.HOMEADIM.EDIT_TRIP_BUS, method = RequestMethod.POST)
    public ResponseEntity<WrapperResponse> editTripBus(@RequestBody TripBusBean tripBusBean) {
        WrapperResponse result = new WrapperResponse();
        try {
            Bus bus = busService.findBus(tripBusBean.getBusId()).get();
            LineBus lineBus = lineBusService.findLineBus(tripBusBean.getLineBusId());
            TripBus tripBus = new TripBus();
            tripBus = tripBusService.findTripBusById(tripBusBean.getTripBusId());
            tripBus.setBus(bus);
            tripBus.setLineBus(lineBus);
            tripBus.setNumberGuest(tripBusBean.getNumberGuest());
            tripBus.setTimeTrip(tripBusBean.getTimeTrip());
            tripBus.setPriceTrip(tripBusBean.getPriceTrip());
            tripBusService.updateTripBus(tripBus); // update TripBus

            // update lai tai xe
            TripBusDriver driverBus = new TripBusDriver();
            driverBus.setDriverId(tripBusBean.getDriverId());
            driverBus.setTripbusId(tripBus.getId());
            driverBus.setWages(1200.0);
            driverBus.setDate(tripBus.getTimeTrip());
            driverBus.setRoleCar("1");
            driverBus.setTripbusId(tripBusBean.getTripBusId());
            tripBusDriverDao.editTripBusDriver(driverBus);

            // update lai phu xe
            TripBusDriver assistantDriver = new TripBusDriver();
            assistantDriver.setDriverId(tripBusBean.getAssistantBusId());
            assistantDriver.setTripbusId(tripBus.getId());
            assistantDriver.setWages(600.0);
            assistantDriver.setDate(tripBus.getTimeTrip());
            assistantDriver.setRoleCar("0");
            assistantDriver.setTripbusId(tripBusBean.getTripBusId());
            tripBusDriverDao.editTripBusDriver(assistantDriver);
            result.setMsg("Update TripBus Sucessfull");
            result.setStatus(HttpStatus.OK.value());
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
            result.setMsg(ex.getMessage());
            result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<WrapperResponse>(result, HttpStatus.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
        return new ResponseEntity<WrapperResponse>(result, HttpStatus.valueOf(HttpStatus.OK.value()));
    }

    @RequestMapping(value = UrlConst.HOMEADIM.GET_ALL_TRIP_BUS, method = RequestMethod.GET)
    public ResponseEntity<WrapperResponse> getAllTripBus() {
        WrapperResponse result = new WrapperResponse();
        try {
            result.setMsg("Get All TripBus Sucessfull");
            result.setStatus(HttpStatus.OK.value());
            HashMap<String, Object> listData = new HashMap<>();
            listData.put("listTripBus", tripBusService.listTripBus());
            listData.put("listTripBusDriver", tripBusDriverDao.getListBusDriver());
            result.setBody(listData);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            result.setMsg(ex.getMessage());
            result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<WrapperResponse>(result, HttpStatus.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
        return new ResponseEntity<WrapperResponse>(result, HttpStatus.valueOf(HttpStatus.OK.value()));
    }


}