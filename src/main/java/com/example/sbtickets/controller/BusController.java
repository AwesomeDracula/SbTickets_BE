package com.example.sbtickets.controller;

import com.example.sbtickets.service.BusService;
import com.example.sbtickets.bean.AuthenticationBean;
import com.example.sbtickets.bean.BusBean;
import com.example.sbtickets.bean.WrapperResponse;
import com.example.sbtickets.common.UrlConst;
import com.example.sbtickets.entity.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class BusController {
    @Autowired
    BusService busService;

    @RequestMapping(value = UrlConst.HOMEADIM.GET_BUS, method = RequestMethod.GET)
    public ResponseEntity<WrapperResponse> getBus() {
        WrapperResponse response = new WrapperResponse();
        List<Bus> result = new ArrayList<>();
        try {
            result = busService.getBus();
            response.setBody(result);
            response.setStatus(HttpStatus.OK.value());
        }
        catch (Exception ex){
            response.setMsg("Not found");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<WrapperResponse>(response, HttpStatus.FAILED_DEPENDENCY);
        }
        return new ResponseEntity<WrapperResponse>(response, HttpStatus.OK);
    }

    @RequestMapping(value = UrlConst.HOMEADIM.CREATE_BUS, method = RequestMethod.POST)
    public ResponseEntity<WrapperResponse> createBus(HttpServletRequest request, @RequestBody BusBean bus){
        WrapperResponse response = new WrapperResponse();
        Bus newBus, createdBus;
        try {
            newBus = new Bus(
                    bus.getCarNumber(),
                    bus.getColor(),
                    bus.getManufacturer(),
                    bus.getLifeCar(),
                    bus.getNumberSeats(),
                    bus.getYearUse(),
                    bus.getDateMantain()
            );
            createdBus = busService.createBus(newBus);
            response.setBody(createdBus);
            response.setStatus(HttpStatus.OK.value());
            response.setMsg("Created new Bus successfully");
        } catch (Exception ex){
            response.setMsg("Cannot create new Bus");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<WrapperResponse>(response, HttpStatus.FAILED_DEPENDENCY);
        }
        return new ResponseEntity<WrapperResponse>(response, HttpStatus.OK);
    }

    @RequestMapping(value = UrlConst.HOMEADIM.UPDATE_BUS, method = RequestMethod.PUT)
    public ResponseEntity<WrapperResponse> updateBus(@PathVariable("id") Integer id, @RequestBody BusBean bus){
        WrapperResponse response = new WrapperResponse();
        Bus updatingBus;
        try{
            updatingBus = new Bus(
                    bus.getCarNumber(),
                    bus.getColor(),
                    bus.getManufacturer(),
                    bus.getLifeCar(),
                    bus.getNumberSeats(),
                    bus.getYearUse(),
                    bus.getDateMantain()
            );
            busService.updateBus(id, updatingBus);
            response.setMsg("Updated successfully");
            response.setStatus(HttpStatus.OK.value());
        } catch (Exception ex){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMsg("Updated fail");
            return new ResponseEntity<WrapperResponse>(response, HttpStatus.FAILED_DEPENDENCY);
        }
        return new ResponseEntity<WrapperResponse>(response, HttpStatus.OK);
    }

    @RequestMapping(value = UrlConst.HOMEADIM.DELETE_BUS, method = RequestMethod.DELETE)
    public ResponseEntity<WrapperResponse> deleteBus(@PathVariable("id") Integer id){
        WrapperResponse response = new WrapperResponse();
        try{
            busService.deleteBus(id);
            response.setStatus(HttpStatus.OK.value());
            response.setMsg("Deleted successfully");
        } catch (Exception ex){
            response.setMsg(ex.getMessage());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<WrapperResponse>(response, HttpStatus.FAILED_DEPENDENCY);
        }
        return new ResponseEntity<WrapperResponse>(response, HttpStatus.OK);
    }

    @RequestMapping(value = UrlConst.HOMEADIM.FIND_BUS, method = RequestMethod.GET)
    public ResponseEntity<WrapperResponse> findBus(@PathVariable("id") Integer id) {
        WrapperResponse response = new WrapperResponse();
        try {
            Optional<Bus>  result = null;
            result  = busService.findBus(id);
            response.setStatus(HttpStatus.OK.value());
            response.setMsg("find successfully");
            response.setBody(result);
        }
        catch (Exception ex){
            response.setMsg(ex.getMessage());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<WrapperResponse>(response, HttpStatus.FAILED_DEPENDENCY);
        }
        return new ResponseEntity<WrapperResponse>(response, HttpStatus.OK);
    }

}
