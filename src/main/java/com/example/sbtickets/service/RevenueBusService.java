package com.example.sbtickets.service;

import com.example.sbtickets.bean.RevenueBusBean;
import com.example.sbtickets.entity.TripBus;
import com.example.sbtickets.repository.TripBusRepository;
import com.example.sbtickets.service.impl.RevenueBusImplement;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class RevenueBusService implements RevenueBusImplement  {
    private static final Logger logger = Logger.getLogger(BusService.class);
    @Autowired
    TripBusRepository tripBusRepository;
    public class  SortByBusId implements Comparator<TripBus> {
        public int compare(TripBus a, TripBus b) {
            return a.bus.getId() - b.bus.getId();
        }
    }
    @Override
    public List<RevenueBusBean> listBusRevenue() {
        try{
            List<TripBus> listTripBus = tripBusRepository.findAll();
            Collections.sort(listTripBus, new SortByBusId());
            List<RevenueBusBean> result = new ArrayList<RevenueBusBean>();
            for(int i=0;i<listTripBus.size();i++){
                RevenueBusBean busRevenue = new RevenueBusBean();
                busRevenue.setBusId(listTripBus.get(i).bus.getId());
                busRevenue.setCarNumber(listTripBus.get(i).bus.getCarNumber());
                busRevenue.setTripBusId(listTripBus.get(i).getId());
                busRevenue.setNumberGuest(listTripBus.get(i).getNumberGuest());
                busRevenue.setPriceTrip(listTripBus.get(i).getPriceTrip());
                busRevenue.setTimeTrip(listTripBus.get(i).getTimeTrip());
                busRevenue.setRevenue((listTripBus.get(i).getNumberGuest()*listTripBus.get(i).getPriceTrip()));
                result.add(busRevenue);
            }
            return result;
        }
        catch (Exception ex){
            logger.error(ex);
        }
        return null;
    }
    @Override
    public List<RevenueBusBean> getDriverById(Integer busId){
        try{
            List<TripBus> listTripBus = tripBusRepository.findAll();
            Collections.sort(listTripBus, new SortByBusId());
            List<RevenueBusBean> result = new ArrayList<RevenueBusBean>();
            List<RevenueBusBean> revenueBus = new ArrayList<RevenueBusBean>();
            for(int i=0;i<listTripBus.size();i++){
                RevenueBusBean busRevenue = new RevenueBusBean();
                busRevenue.setBusId(listTripBus.get(i).bus.getId());
                busRevenue.setCarNumber(listTripBus.get(i).bus.getCarNumber());
                busRevenue.setTripBusId(listTripBus.get(i).getId());
                busRevenue.setNumberGuest(listTripBus.get(i).getNumberGuest());
                busRevenue.setPriceTrip(listTripBus.get(i).getPriceTrip());
                busRevenue.setTimeTrip(listTripBus.get(i).getTimeTrip());
                busRevenue.setRevenue((listTripBus.get(i).getNumberGuest()*listTripBus.get(i).getPriceTrip()));
                result.add(busRevenue);
            }
            for(int i=0;i<result.size();i++){
                if(busId == result.get(i).busId){
                    RevenueBusBean busRevenue = new RevenueBusBean();
                    busRevenue.setBusId(busId);
                    busRevenue.setCarNumber(result.get(i).getCarNumber());
                    busRevenue.setTripBusId(result.get(i).getTripBusId());
                    busRevenue.setNumberGuest(result.get(i).getNumberGuest());
                    busRevenue.setPriceTrip(result.get(i).getPriceTrip());
                    busRevenue.setTimeTrip(result.get(i).getTimeTrip());
                    busRevenue.setRevenue(result.get(i).getRevenue());
                    revenueBus.add(busRevenue);
                }
            }
            return revenueBus;
        }
        catch (Exception ex){
            logger.error(ex);
        }
        return null;
    }
}