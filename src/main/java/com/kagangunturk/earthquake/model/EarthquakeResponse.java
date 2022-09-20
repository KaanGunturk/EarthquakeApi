package com.kagangunturk.earthquake.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EarthquakeResponse {

    private String message;
    private List<EarthquakeDto> earthquakeDtos;

}
