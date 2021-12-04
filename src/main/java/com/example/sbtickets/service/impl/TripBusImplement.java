package com.example.sbtickets.service.impl;

import com.example.sbtickets.bean.AllTripBusByLastPointBean;
import com.example.sbtickets.bean.CountTripBusForMonth;
import com.example.sbtickets.bean.ObjectByTripBus;
import com.example.sbtickets.bean.TripBusByCusomer;
import com.example.sbtickets.entity.TripBus;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface TripBusImplement {
    public TripBus createTripBus(TripBus tripBus);
    public void deleteTripBus(Integer tripBusId);
    public void updateTripBus(TripBus tripBus);
    public TripBus findTripBusById(Integer id);
    public List<TripBus> listTripBus();
    public TripBus findTripBus(Integer id);
    public List<TripBus> findByFirtLastPoint(AllTripBusByLastPointBean allTripBusByLastPointBean);
    public List<ObjectByTripBus> findByFirtLastPointObject(AllTripBusByLastPointBean allTripBusByLastPointBean);
    public List<CountTripBusForMonth> getCountTripBusForMonth();
    public List<TripBusByCusomer> getListTripBusByCustomer(Integer id);
}
