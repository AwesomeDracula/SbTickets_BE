package com.example.sbtickets.service.impl;

import com.example.sbtickets.entity.Customer;

public interface CustomerImplement {
    public boolean addCustomer(Customer customer);
    public Customer getCusById(Integer id);
}
