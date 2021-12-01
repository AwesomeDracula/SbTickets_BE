package com.example.sbtickets.dao.impl;

import com.example.sbtickets.bean.WagesDriverBean;

import java.util.List;

public interface WagesDriverImplement {
    public List<WagesDriverBean> getList(Integer driverId, String scrapTime);
}
