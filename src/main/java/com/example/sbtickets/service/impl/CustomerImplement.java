package com.example.sbtickets.service.impl;

import com.example.sbtickets.entity.Customer;

import java.util.List;

public interface CustomerImplement {
    public boolean addCustomer(Customer customer);
    public Integer findCustomerId(Integer accountId);
}
