package com.example.sbtickets.service;

import com.example.sbtickets.entity.TripbusAddress;
import com.example.sbtickets.repository.TripBusAddressRepository;
import com.example.sbtickets.repository.TripBusRepository;
import com.example.sbtickets.service.impl.TripBusAddressImplement;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripBusAddressService implements TripBusAddressImplement {
    private static final Logger logger = Logger.getLogger(TripBusAddressService.class);

    @Autowired
    TripBusAddressRepository tripBusAddressRepository;
    @Override
    public List<TripbusAddress> listAddress() {
        try {
            return tripBusAddressRepository.findAll();
        }
        catch (Exception ex){
            logger.error(ex);
        }
        return null;
    }

    @Override
    public TripbusAddress findTripBusAddress(Integer id) {
        try {
            return tripBusAddressRepository.findById(id).get();
        }
        catch (Exception ex){
            logger.error(ex);
        }
        return null;
    }
}
