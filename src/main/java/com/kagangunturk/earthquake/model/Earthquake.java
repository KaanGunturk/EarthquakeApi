package com.kagangunturk.earthquake.model;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class Earthquake {

    private String country;
    private Float magnitude;
    private Long time;
}
