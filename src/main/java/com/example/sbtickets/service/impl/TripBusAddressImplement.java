package com.example.sbtickets.service.impl;

import com.example.sbtickets.entity.TripbusAddress;

import java.util.List;

public interface TripBusAddressImplement {
    public  List<TripbusAddress> listAddress();
    public  TripbusAddress  findTripBusAddress(Integer id);
}
