package com.example.sbtickets.dao.impl;

import com.example.sbtickets.entity.TripBusCustomer;

import java.util.List;

public interface TripBusCustomerInterface {
    public void insertTripBusCustomer(TripBusCustomer tripBusCustomer);
    public void deleteTripBusCustomer(Integer tripBusId);
    public void editTripBusCustomer(TripBusCustomer tripBusCustomer);
    public List<TripBusCustomer> findByTripBusId(Integer id);
}
