package com.example.sbtickets.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bus {
    private int id;
    private String carNumber;

    public Bus(){

    }
    public Bus(int id, String carNumber){
        this.id = id;
        this.carNumber = carNumber;
    }
}
