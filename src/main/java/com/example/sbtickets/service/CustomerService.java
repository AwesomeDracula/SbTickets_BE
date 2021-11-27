package com.example.sbtickets.service;

import com.example.sbtickets.entity.Customer;
import com.example.sbtickets.repository.CustomerRepository;
import com.example.sbtickets.service.impl.CustomerImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements CustomerImplement {

    @Autowired
    CustomerRepository customerRepository;
    @Override
    public boolean addCustomer(Customer customer) {
       try {
           customerRepository.save(customer);
       }
       catch (Exception ex){
           ex.printStackTrace();
           return false;
       }
       return true;
    }
}
