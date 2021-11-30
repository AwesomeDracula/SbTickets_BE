package com.example.sbtickets.dao.impl;

import com.example.sbtickets.entity.TripBusDriver;

import java.util.List;

public interface TripBusDriverImplement {
    public void insertTripBusDriver(TripBusDriver tripBusDriver);
    public void deleteTripBusDriver(Integer tripBusId);
    public void editTripBusDriver(TripBusDriver tripBusDriver);
    public List<TripBusDriver> getListBusDriver();
}