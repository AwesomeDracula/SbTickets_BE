package com.example.sbtickets.controller;

import com.example.sbtickets.bean.AllTripBusByLastPointBean;
import com.example.sbtickets.bean.TripBusBean;
import com.example.sbtickets.bean.TripBusCustomerBean;
import com.example.sbtickets.bean.WrapperResponse;
import com.example.sbtickets.common.UrlConst;
import com.example.sbtickets.dao.TripBusCustomerDao;
import com.example.sbtickets.dao.TripBusDriverDao;
import com.example.sbtickets.entity.*;
import com.example.sbtickets.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    TripBusCustomerDao tripBusCustomerDao;

    @Autowired
    CustomerService customerService;

    @Autowired
    SendToEmailService sendToEmailService;

    @RequestMapping(value = UrlConst.HOMEADIM.CREATE_TRIP_BUS, method = RequestMethod.POST)
    public ResponseEntity<WrapperResponse> creatTripBus(@RequestBody TripBusBean tripBusBean) {
        WrapperResponse result = new WrapperResponse();
        try {
            Bus bus = busService.findBus(tripBusBean.getBusId());
            LineBus lineBus = lineBusService.getLineBusById(tripBusBean.getLineBusId());
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
                driverBus.setWages((double) (lineBus.getComplexity()*60000));
                driverBus.setDate(tripBus.getTimeTrip());
                driverBus.setRoleCar("1");
                driverBus.setScrapDateTime(tripBus.getTimeTrip().toString().substring(0,10));
                tripBusDriverDao.insertTripBusDriver(driverBus);

                TripBusDriver assistantDriver = new TripBusDriver();
                assistantDriver.setDriverId(tripBusBean.getAssistantBusId());
                assistantDriver.setTripbusId(tripBus.getId());
                assistantDriver.setWages((double) (lineBus.getComplexity()*30000));
                assistantDriver.setDate(tripBus.getTimeTrip());
                assistantDriver.setScrapDateTime(tripBus.getTimeTrip().toString().substring(0,10));
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
            Bus bus = busService.findBus(tripBusBean.getBusId());
            LineBus lineBus = lineBusService.getLineBusById(tripBusBean.getLineBusId());
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
            driverBus.setWages((double) (lineBus.getComplexity()*60000));
            driverBus.setDate(tripBus.getTimeTrip());
            driverBus.setRoleCar("1");
            driverBus.setTripbusId(tripBusBean.getTripBusId());
            tripBusDriverDao.editTripBusDriver(driverBus);

            // update lai phu xe
            TripBusDriver assistantDriver = new TripBusDriver();
            assistantDriver.setDriverId(tripBusBean.getAssistantBusId());
            assistantDriver.setTripbusId(tripBus.getId());
            assistantDriver.setWages((double) (lineBus.getComplexity()*30000));
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
            result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<WrapperResponse>(result, HttpStatus.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
        return new ResponseEntity<WrapperResponse>(result, HttpStatus.valueOf(HttpStatus.OK.value()));
    }

    @RequestMapping(value = UrlConst.HOME_USER.BOOK_SEAT, method = RequestMethod.POST)
    public ResponseEntity<WrapperResponse> bookSeat(@RequestBody TripBusCustomerBean tripBusCustomerBean){
        WrapperResponse result = new WrapperResponse();
        try {
            TripBus tripBus = tripBusService.findTripBusById(tripBusCustomerBean.getTripBusId());
            if(tripBus.getBus().getNumberSeats() - 2 <= tripBus.getNumberGuest()){
                result.setMsg("This trip bus is full of guests");
                result.setStatus(HttpStatus.FORBIDDEN.value());
            }
            if(tripBusService.checkIfCustomerHadTicket(tripBusCustomerBean.getTripBusId(), tripBusCustomerBean.getCustomerId())){
                result.setMsg("This customer has already booked 1 ticket in this trip bus");
                result.setStatus(HttpStatus.FORBIDDEN.value());
            } else {
                TripBusCustomer newSeat = new TripBusCustomer();
                newSeat.setTripbusId(tripBusCustomerBean.getTripBusId());
                newSeat.setCustomerId(tripBusCustomerBean.getCustomerId());
                newSeat.setRoleCar(tripBusCustomerBean.getSeatBooked());
                tripBusCustomerDao.insertTripBusCustomer(newSeat);
                Integer currentPassengerNum = tripBus.getNumberGuest();
                tripBus.setNumberGuest(currentPassengerNum + 1);
                tripBusService.updateTripBus(tripBus);
                sendToEmailService.sendToEmail(tripBusCustomerBean.getSeatBooked(),tripBusService.findTripBus(tripBusCustomerBean.getTripBusId()),customerService.getCustomerDetail(tripBusCustomerBean.getCustomerId()));
                result.setMsg("Seats booked successfully");
                result.setStatus(HttpStatus.OK.value());
            }
        } catch (Exception ex){
            logger.error(ex.getMessage());
            result.setMsg(ex.getMessage());
            result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<WrapperResponse>(result, HttpStatus.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
        return new ResponseEntity<WrapperResponse>(result, HttpStatus.valueOf(HttpStatus.OK.value()));
    }

    @RequestMapping(value = UrlConst.HOME_USER.FIND_BY_TRIPBUS, method = RequestMethod.POST)
    public ResponseEntity<WrapperResponse> bookSeat(@RequestBody AllTripBusByLastPointBean tripBusByLastPointBean){
        WrapperResponse result = new WrapperResponse();
        try {
            result.setMsg("Seats booked successfully");
            result.setStatus(HttpStatus.OK.value());
            result.setBody(tripBusService.findByFirtLastPointObject(tripBusByLastPointBean));
        } catch (Exception ex){
            logger.error(ex.getMessage());
            result.setMsg(ex.getMessage());
            result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<WrapperResponse>(result, HttpStatus.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
        return new ResponseEntity<WrapperResponse>(result, HttpStatus.valueOf(HttpStatus.OK.value()));
    }
}
