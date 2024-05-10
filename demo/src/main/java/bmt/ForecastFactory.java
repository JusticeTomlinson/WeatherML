package bmt;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class ForecastFactory {
    String country;
    String state;
    String city;

    public ForecastFactory(String country, String state, String city){
        this.country = country;
        this.state = state;
        this.city = city;
    }

    public void creation() throws IOException, InterruptedException {

    Float[] longlat = Location.getLongLat(country, state, city); // Spaces need to be replaced with question mark
    String historicalJSON_URL = RequestHandler.getForecastURL(longlat[0], longlat[1]);
    JSONObject historicalJSON = RequestHandler.getForecastSeries(historicalJSON_URL);
    Forecast forecasts = createForecastObject(historicalJSON);
    int x = 0;
    }

    public static Forecast createForecastObject(JSONObject forecastJSON) {
        JSONArray periods = forecastJSON.getJSONArray("periods");
        ArrayList<Weather> weatherList = new ArrayList<>();

        for (int i=0; i < periods.length(); i++) {
            JSONObject period = periods.getJSONObject(i);
            JSONObject relativeHumidity = period.getJSONObject("relativeHumidity");
            JSONObject dewpoint = period.getJSONObject("dewpoint");

            String name = period.getString("name");
            int temperature = period.getInt("temperature");
            int humidity = relativeHumidity.getInt("value");
            int dewpointVal = dewpoint.getInt("value");        
            String windDirection = period.getString("windDirection");
            String windSpeed = period.getString("windSpeed");
            String startTime = period.getString("startTime");
            String endTime = period.getString("endTime");
            Boolean isDaytime = period.getBoolean("isDaytime");
            String shortForecastText = period.getString("shortForecast");
            String longForecastText = period.getString("detailedForecast");
            String icon = period.getString("icon");
            JSONObject precipitation = period.getJSONObject("probabilityOfPrecipitation");

            Weather weatherObject = new Weather(name, temperature, humidity, dewpointVal, windDirection, windSpeed, startTime, endTime, 
                isDaytime, shortForecastText, longForecastText, icon, precipitation);
            
            weatherList.add(weatherObject);
        }

        Forecast forecastObject = new Forecast(weatherList);

        return forecastObject;
    }
}