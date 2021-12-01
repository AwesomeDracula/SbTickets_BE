package com.example.sbtickets.service;

import com.example.sbtickets.bean.AllTripBusByLastPointBean;
import com.example.sbtickets.bean.ObjectByTripBus;
import com.example.sbtickets.dao.TripBusCustomerDao;
import com.example.sbtickets.entity.TripBusCustomer;
import org.apache.log4j.Logger;
import com.example.sbtickets.entity.TripBus;
import com.example.sbtickets.repository.TripBusRepository;
import com.example.sbtickets.service.impl.TripBusImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service("TripBusService")
public class TripBusService implements TripBusImplement {
    private static final Logger logger = Logger.getLogger(TripBusService.class);
    @Autowired
    TripBusRepository tripBusRepository;


    @Autowired
    TripBusCustomerDao tripBusCustomerDao;

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

    @Override
    public List<TripBus> findByFirtLastPoint(AllTripBusByLastPointBean allTripBusByLastPointBean) {
        try{
            List<TripBus> listTripBus = new ArrayList<>();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            for(TripBus item : tripBusRepository.findAll()){
                String dateTime = df.format(item.getTimeTrip());
                if(item.getLineBus().getFirtPoint().getId() == allTripBusByLastPointBean.getFirstPoint() && item.getLineBus().getLastPoint().getId() == allTripBusByLastPointBean.getLastPoint() && dateTime.equals(allTripBusByLastPointBean.getDateTime())){
                    listTripBus.add(item);
                }
            }
            return listTripBus;
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
        }
        return null;
    }

    @Override
    public List<ObjectByTripBus> findByFirtLastPointObject(AllTripBusByLastPointBean allTripBusByLastPointBean) {
        try{
            List<ObjectByTripBus> objectByTripBuses = new ArrayList<>();
            List<TripBus> listTripBus = new ArrayList<>();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            for(TripBus item : tripBusRepository.findAll()){
                String dateTime = df.format(item.getTimeTrip());
                if(item.getLineBus().getFirtPoint().getId() == allTripBusByLastPointBean.getFirstPoint() && item.getLineBus().getLastPoint().getId() == allTripBusByLastPointBean.getLastPoint() && dateTime.equals(allTripBusByLastPointBean.getDateTime())){
                    List<TripBusCustomer> listTripBusCustomer = tripBusCustomerDao.findByTripBusId(item.getId());
                    List<Integer> dataBooked = new ArrayList<>();
                    for(TripBusCustomer bookItem : listTripBusCustomer){
                        dataBooked.add(bookItem.getRoleCar());
                    }
                    objectByTripBuses.add(new ObjectByTripBus(item, dataBooked));
                }
            }

            return objectByTripBuses;

        }
        catch (Exception ex){
            logger.error(ex.getMessage());
        }
        return null;
    }
}
