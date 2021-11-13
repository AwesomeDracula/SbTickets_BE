package com.example.sbtickets.service;

import com.example.sbtickets.bean.BusBean;
import com.example.sbtickets.entity.Bus;
import java.util.List;

public interface BusImplement {
    public List<Bus> getBus();
    public Bus findBus(String name);
    public Bus createBus(Bus bus);
    public void updateBus(Integer id, Bus bus);
    public void deleteBus(Integer id);
}
