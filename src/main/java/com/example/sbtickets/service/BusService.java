package com.example.sbtickets.service;
import com.example.sbtickets.bean.BusBean;
import com.example.sbtickets.entity.Bus;
import com.example.sbtickets.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BusService implements BusImplement {
    @Autowired
    BusRepository busRepository;
    @Override
    public List<Bus> getBus() {
        List<Bus> listBus = busRepository.findAll();
        return  listBus;
    }

    @Override
    public Bus findBus(Integer carNumber) {
        return null;
    }

    @Override
    public Bus createBus(Bus bus) {
        Bus newBus = busRepository.save(bus);
        return newBus;
    }

    @Override
    public void updateBus(Integer id, Bus bus) {
        Optional<Bus> dbBus = busRepository.findById(id);
        Bus foundBus = dbBus.get();
        foundBus.setColor(bus.getColor());
        foundBus.setCarNumber(bus.getCarNumber());
        foundBus.setDateMantain(bus.getDateMantain());
        foundBus.setManufacturer(bus.getManufacturer());
        foundBus.setLifeCar(bus.getLifeCar());
        foundBus.setYearUse(bus.getYearUse());
        foundBus.setNumberSeats(bus.getNumberSeats());
        busRepository.save(foundBus);
    }

    @Override
    public void deleteBus(Integer id) {
        busRepository.deleteById(id);
        return;
    }
}
