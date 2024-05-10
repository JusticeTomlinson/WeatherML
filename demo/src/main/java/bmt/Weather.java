package bmt;

import org.json.JSONObject;

public class Weather {
    String name;
    int temperature;
    int humidity;
    int dewpointVal;
    String windDirection;
    String windSpeed;
    String startTime;
    String endTime;
    Boolean isDaytime;
    String shortForecastText;
    String longForecastText;
    String icon;
    JSONObject precipitation;

    public Weather(String name, int temperature, int humidity, int dewpointVal, String windDirection, String windSpeed, String startTime, String endTime, 
                Boolean isDaytime, String shortForecastText, String longForecastText, String icon, JSONObject precipitation) {
        this.name = name;
        this.temperature = temperature;
        this.humidity = humidity;
        this.dewpointVal = dewpointVal;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isDaytime = isDaytime;
        this.shortForecastText = shortForecastText;
        this.longForecastText = longForecastText;
        this.icon = icon;
        this.precipitation = precipitation;
    }  
}
