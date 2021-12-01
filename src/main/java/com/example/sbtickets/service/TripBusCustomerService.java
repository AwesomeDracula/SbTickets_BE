package com.example.sbtickets.service;

import com.example.sbtickets.entity.TripBusCustomer;
import com.example.sbtickets.service.impl.TripBusCustomerImplement;
import org.springframework.stereotype.Service;

@Service
public class TripBusCustomerService implements TripBusCustomerImplement {
    @Override
    public TripBusCustomer findByTripBusId(Integer id) {
        return null;
    }
}
