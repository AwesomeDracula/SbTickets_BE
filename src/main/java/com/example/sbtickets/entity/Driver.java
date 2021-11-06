package com.example.sbtickets.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Driver {
    private Long id;
    private String name;

    public Driver(){

    }

    public Driver(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
