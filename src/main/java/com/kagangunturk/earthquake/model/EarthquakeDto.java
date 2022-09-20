package com.kagangunturk.earthquake.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EarthquakeDto {

    private String country;
    private Float magnitude;
    private Date date;
}
