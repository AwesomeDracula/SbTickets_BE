package com.example.sbtickets.service;

import com.example.sbtickets.bean.LineBusBean;
import com.example.sbtickets.entity.LineBus;

import java.util.List;

public interface LineBusImplement {
    public List<LineBus> getLineBus();
    public LineBus getLineBusById(Integer id);
    public LineBus findLineBus(String firstPoint);
    public LineBus createLineBus(LineBus lineBus);
    public void updateLineBus(Integer id, LineBus lineBus);
    public void deleteLineBus(Integer id);
    public void deleteLineBuses(List<Integer> ids);
}