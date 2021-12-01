package com.example.sbtickets.service.impl;

import com.example.sbtickets.bean.LineBusBean;
import com.example.sbtickets.entity.LineBus;

import java.util.List;

public interface LineBusImplement {
    public List<LineBus> getLineBus();
    public LineBus findLineBus(Integer id);
    public LineBus createLineBus(LineBus lineBus);
    public void updateLineBus(Integer id, LineBus lineBus);
    public void deleteLineBus(Integer id);
}