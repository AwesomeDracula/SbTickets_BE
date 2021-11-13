package com.example.sbtickets.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import com.example.sbtickets.entity.Bus;

import java.util.List;
@Service
public class BusService implements BusImplement {

    @Override
    public List<Bus> getBus(){
        List<Bus> listBus = new ArrayList<>();
        listBus.add(new Bus(01, "AB1234"));
        listBus.add(new Bus(02, "AB5678"));
        listBus.add(new Bus(03, "CD1234"));
        listBus.add(new Bus(04, "CD5678"));
        return listBus;
    }

}
