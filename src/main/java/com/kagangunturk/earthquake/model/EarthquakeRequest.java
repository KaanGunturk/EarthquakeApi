package com.kagangunturk.earthquake.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EarthquakeRequest {

    private int countOfDays;
    private String country;

}
