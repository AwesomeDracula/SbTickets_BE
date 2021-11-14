package com.example.sbtickets.bean;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LineBusBean {
    private Integer id;
    private String firstPoint;
    private String lastPoint;
    private Integer length;
    private Integer complexity;
}
