package com.kagangunturk.earthquake.api;

import com.kagangunturk.earthquake.business.IEarthquakeService;
import com.kagangunturk.earthquake.model.EarthquakeRequest;
import com.kagangunturk.earthquake.model.EarthquakeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class EarthquakeApi {
    @Autowired
    private IEarthquakeService earthquakeService;

    @PostMapping("/getAll")
    public EarthquakeResponse getAll(@RequestBody EarthquakeRequest request) throws IOException {
        return earthquakeService.getAll(request);
    }

}
