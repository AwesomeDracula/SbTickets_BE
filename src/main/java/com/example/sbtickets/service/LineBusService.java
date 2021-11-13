package com.example.sbtickets.service;

import com.example.sbtickets.bean.LineBusBean;
import com.example.sbtickets.entity.LineBus;
import com.example.sbtickets.repository.LineBusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LineBusService implements LineBusImplement{

    @Autowired
    LineBusRepository lineBusRepository;
    @Override
    public List<LineBus> getLineBus() {
        List<LineBus> listLineBus = lineBusRepository.findAll();
        return  listLineBus;
    }

    @Override
    public LineBus findLineBus(Integer id) {
        return null;
    }

    @Override
    public LineBus createLineBus(LineBus lineBus) {
        LineBus newLineBus = lineBusRepository.save(lineBus);
        return newLineBus;
    }

    @Override
    public void updateLineBus(Integer id, LineBus lineBus) {
        Optional<LineBus> dbLineBus = lineBusRepository.findById(id);
        LineBus foundLineBus = dbLineBus.get();
        foundLineBus.setId(id);
        foundLineBus.setFirstPoint(lineBus.getFirstPoint());
        foundLineBus.setLastPoint(lineBus.getLastPoint());
        foundLineBus.setLength(lineBus.getLength());
        foundLineBus.setComplexity(lineBus.getComplexity());
        lineBusRepository.save(foundLineBus);
        return;
    }

    @Override
    public void deleteLineBus(Integer id) {
        lineBusRepository.deleteById(id);
        return;
    }
}
