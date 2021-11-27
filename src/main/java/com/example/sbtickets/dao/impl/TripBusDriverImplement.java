package com.example.sbtickets.dao.impl;

import com.example.sbtickets.entity.TripBusDriver;

public interface TripBusDriverImplement {
    public void insertTripBusDriver(TripBusDriver tripBusDriver);
    public void deleteTripBusDriver(Integer tripBusId);
    public void editTripBusDriver(TripBusDriver tripBusDriver);
}
