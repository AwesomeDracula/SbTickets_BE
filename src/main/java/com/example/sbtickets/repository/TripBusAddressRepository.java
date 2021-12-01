package com.example.sbtickets.repository;

import com.example.sbtickets.entity.TripbusAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripBusAddressRepository extends JpaRepository<TripbusAddress, Integer> {
}
