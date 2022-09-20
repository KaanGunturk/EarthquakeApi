package com.kagangunturk.earthquake.dataAccess;

import com.kagangunturk.earthquake.model.Earthquake;
import java.io.IOException;
import java.util.List;

public interface IEarthquakeDao {

    public List<Earthquake> getAll(int countOfDays,String country) throws IOException;


}
