package com.example.sbtickets.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationBean {
    private String token;
    private String message;
    private int status;

    public AuthenticationBean(){

    }
}
