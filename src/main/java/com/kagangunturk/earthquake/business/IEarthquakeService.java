package com.kagangunturk.earthquake.business;

import com.kagangunturk.earthquake.model.EarthquakeRequest;
import com.kagangunturk.earthquake.model.EarthquakeResponse;
import java.io.IOException;


public interface IEarthquakeService {

    EarthquakeResponse getAll(EarthquakeRequest earthquakeRequest) throws IOException;
}
