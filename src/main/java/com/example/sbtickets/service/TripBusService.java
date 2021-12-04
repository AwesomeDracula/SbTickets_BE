package com.example.sbtickets.service;

import com.example.sbtickets.bean.AllTripBusByLastPointBean;
import com.example.sbtickets.bean.CountTripBusForMonth;
import com.example.sbtickets.bean.ObjectByTripBus;
import com.example.sbtickets.bean.TripBusByCusomer;
import com.example.sbtickets.dao.TripBusCustomerDao;
import com.example.sbtickets.entity.Customer;
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
import java.util.Date;
import java.util.List;


@Service("TripBusService")
public class TripBusService implements TripBusImplement {
    private static final Logger logger = Logger.getLogger(TripBusService.class);
    @Autowired
    TripBusRepository tripBusRepository;


    @Autowired
    TripBusCustomerDao tripBusCustomerDao;

    @Autowired
    CustomerService customerService;

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
                if(item.getLineBus().getfirstPoint().getId() == allTripBusByLastPointBean.getFirstPoint() && item.getLineBus().getLastPoint().getId() == allTripBusByLastPointBean.getLastPoint() && dateTime.equals(allTripBusByLastPointBean.getDateTime())){
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
                if(item.getLineBus().getfirstPoint().getId() == allTripBusByLastPointBean.getFirstPoint() && item.getLineBus().getLastPoint().getId() == allTripBusByLastPointBean.getLastPoint() && dateTime.equals(allTripBusByLastPointBean.getDateTime())){
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

    @Override
    public List<CountTripBusForMonth> getCountTripBusForMonth() {
        List<CountTripBusForMonth> listData = new ArrayList<>();
        try {
            Date date = new Date();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String momentTime = "01";
            Integer count1 = 0;
            Integer count2 = 0;
            Integer count3 = 0;
            Integer count4 = 0;
            Integer count5 = 0;
            for(TripBus item : tripBusRepository.findAll()){
                String dateTime = df.format(item.getTimeTrip());
                if(Integer.valueOf(dateTime.substring(5,7)) == Integer.valueOf(momentTime)){
                    if(item.getTimeTrip().getDate() >= 1 && item.getTimeTrip().getDate() <= 7){
                        count1++;
                    }
                    if(item.getTimeTrip().getDate() >= 8 && item.getTimeTrip().getDate() <= 14){
                        count2++;
                    }
                    if(item.getTimeTrip().getDate() >= 15 && item.getTimeTrip().getDate() <= 21){
                        count3++;
                    }
                    if(item.getTimeTrip().getDate() >= 23 && item.getTimeTrip().getDate() <= 28){
                        count4++;
                    }
                    else if(item.getTimeTrip().getDay() > 28){
                        count5++;
                    }
                }
            }
            listData.add(new CountTripBusForMonth(count1,1));
            listData.add(new CountTripBusForMonth(count2,2));
            listData.add(new CountTripBusForMonth(count3,3));
            listData.add(new CountTripBusForMonth(count4,4));
            listData.add(new CountTripBusForMonth(count5,5));
            return  listData;
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
        }
        return null;
    }

    @Override
    public List<TripBusByCusomer> getListTripBusByCustomer(Integer id) {
        try {
            List<TripBusCustomer> listDataBusCustomer = tripBusCustomerDao.findByTripBusId(id);
            List<TripBusByCusomer> listData = new ArrayList<>();
            for(TripBusCustomer item : listDataBusCustomer){
                TripBusByCusomer tripBusByCusomer = new TripBusByCusomer();
                Customer customer = customerService.getCusById(item.getCustomerId());
                tripBusByCusomer.setTripBus(id);
                tripBusByCusomer.setAddress(customer.getAddress());
                tripBusByCusomer.setBirthday(customer.getBirthDay());
                tripBusByCusomer.setCmt(customer.getCmt());
                tripBusByCusomer.setBookseat(item.getRoleCar());
                tripBusByCusomer.setFullName(customer.getFullName());
                listData.add(tripBusByCusomer);
            }
            return listData;
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
        }
        return null;
    }
}
