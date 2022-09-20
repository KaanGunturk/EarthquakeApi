package com.kagangunturk.earthquake.business;

import com.kagangunturk.earthquake.dataAccess.IEarthquakeDao;
import com.kagangunturk.earthquake.model.Earthquake;
import com.kagangunturk.earthquake.model.EarthquakeDto;
import com.kagangunturk.earthquake.model.EarthquakeRequest;
import com.kagangunturk.earthquake.model.EarthquakeResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EarthquakeService implements IEarthquakeService {


    @Autowired
    private IEarthquakeDao earthquakeDao;

    @Override
    public EarthquakeResponse getAll(EarthquakeRequest earthquakeRequest) throws IOException {

        EarthquakeResponse earthquakeResponse = new EarthquakeResponse();

        if (!validateCountry(earthquakeRequest.getCountry()) || !validateCountOfDays(earthquakeRequest.getCountOfDays())) {
            earthquakeResponse.setMessage("parameters are invalid");
            return earthquakeResponse;
        }

        List<Earthquake> daoResult = earthquakeDao.getAll(earthquakeRequest.getCountOfDays(), earthquakeRequest.getCountry());

        if (!checkResponseCodeIsNot200(daoResult)){
            earthquakeResponse.setMessage("Response code is not 200");
            return earthquakeResponse;
        }

        if (!checkIfListIsEmpty(daoResult)) {
            earthquakeResponse.setMessage("No Earthquakes were recorded past " + earthquakeRequest.getCountOfDays() + " days.");
            return earthquakeResponse;
        }


        List<EarthquakeDto> earthquakeDtos = this.convertEntityToDto(daoResult);
        earthquakeResponse.setEarthquakeDtos(earthquakeDtos);
        earthquakeResponse.setMessage("Success");

        return earthquakeResponse;


    }

    private boolean validateCountOfDays(int countOfDays) {
        boolean flag = true;

        if (countOfDays < 0) {
            flag = false;
        }
        return flag;
    }

    private boolean validateCountry(String country) {
        boolean flag = true;
        if (StringUtils.isBlank(country)) {
            flag = false;
        }
        return flag;
    }

    private List<EarthquakeDto> convertEntityToDto(List<Earthquake> earthquakes) {
        List<EarthquakeDto> earthquakeDtos = new ArrayList<>();

        for (Earthquake e : earthquakes) {
            EarthquakeDto earthquakeDto = new EarthquakeDto();

            earthquakeDto.setCountry(e.getCountry());
            earthquakeDto.setMagnitude(e.getMagnitude());
            earthquakeDto.setDate(new Date(e.getTime()));

            earthquakeDtos.add(earthquakeDto);
        }
        return earthquakeDtos;
    }

    private boolean checkIfListIsEmpty(List<Earthquake> result) {
        boolean flag = true;
        if (result.size() <= 0) {
            flag = false;
        }
        return flag;
    }

    private boolean checkResponseCodeIsNot200(List<Earthquake> result) {
        boolean flag = true;
        if (result == null) {
            flag = false;
        }
        return flag;

    }

}
