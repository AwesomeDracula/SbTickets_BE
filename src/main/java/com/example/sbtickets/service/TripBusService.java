package com.example.sbtickets.service;

import org.apache.log4j.Logger;
import com.example.sbtickets.entity.TripBus;
import com.example.sbtickets.repository.TripBusRepository;
import com.example.sbtickets.service.impl.TripBusImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("TripBusService")
public class TripBusService implements TripBusImplement {
    private static final Logger logger = Logger.getLogger(TripBusService.class);
    @Autowired
    TripBusRepository tripBusRepository;

    @Override
    public TripBus createTripBus(TripBus tripBus) {
        try {
            return tripBusRepository.save(tripBus);
        }
        catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            return null;
        }
    }

    @Override
    public void deleteTripBus(Integer tripBusId) {
        try {
            tripBusRepository.deleteById(tripBusId);
        }
        catch (Exception ex){
            logger.error(ex.getMessage(), ex);
        }
    }

    @Override
    public void updateTripBus(TripBus tripBus) {
        try {
            tripBusRepository.save(tripBus);
        }
        catch (Exception ex){
            logger.error(ex.getMessage(), ex);
        }
    }

    @Override
    public TripBus findTripBusById(Integer id) {
        try{
            return tripBusRepository.getById(id);
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
        }
        return null;
    }

    @Override
    public List<TripBus> listTripBus() {
        try{
            return tripBusRepository.findAll();
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
        }
        return null;
    }

    @Override
    public TripBus findTripBus(Integer id) {
        try{
            return tripBusRepository.findById(id).get();
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
        }
        return null;
    }
}
