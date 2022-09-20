package com.kagangunturk.earthquake.dataAccess;


import com.kagangunturk.earthquake.model.Earthquake;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Repository
public class EarthquakeDao implements IEarthquakeDao {

    private final String URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson";

    @Override
    public List<Earthquake> getAll(int countOfDays, String country) throws IOException {

        List<Earthquake> earthquakes = new ArrayList<>();

        LocalDate endDate = LocalDate.now();
        LocalDate startDate = LocalDate.now().minusDays(countOfDays);
        URL url = new URL(this.URL + "&starttime=" + startDate + "&endtime=" + endDate + "&orderby=time-asc");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();


        if (conn.getResponseCode() == 200) {
            Scanner scan = new Scanner(url.openStream());

            String inputLine;
            StringBuilder stringBuilder = new StringBuilder();
            while (scan.hasNext()) {
                inputLine = scan.nextLine();
                stringBuilder.append(inputLine);
            }
            JSONObject json = new JSONObject(stringBuilder.toString());
            List<String> list = new ArrayList<String>();
            JSONArray array = json.getJSONArray("features");
            for (int i = 0; i < array.length(); i++) {
                list.add(String.valueOf(array.getJSONObject(i).getJSONObject("properties")));

            }
            for (int i = 0; i < list.size(); i++) {
                Earthquake result = new Earthquake();
                JSONObject object = new JSONObject(list.get(i));
                try {
                    if (object.getString("place").endsWith(country)) {
                        result.setMagnitude(object.getFloat("mag"));
                        result.setTime(object.getLong("time"));
                        result.setCountry(object.getString("place"));
                        earthquakes.add(result);
                    }
                } catch (JSONException e) {

                }

            }

        }else{
            return null;
        }

        return earthquakes;
    }
}
