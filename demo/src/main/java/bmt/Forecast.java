package bmt;

import java.util.ArrayList;

public class Forecast {

    String country;
    String state;
    String city;
    ArrayList<Weather> weeklyWeather;

    public Forecast(String country, String state, String city, ArrayList<Weather> weeklyWeather){
        this.country = country;
        this.state = state;
        this.city = city;
        this.weeklyWeather = weeklyWeather;
    }

    public void displayWeather() {
        Weather chosenWeather = this.weeklyWeather.get(0);
        System.out.println("Region: " + this.city + ", " + this.state + ", " + this.country);
        System.out.println("Weather Information:");
        System.out.println("Name: " + chosenWeather.name);
        System.out.println("Temperature: " + chosenWeather.temperature + "°C");
        System.out.println("Humidity: " + chosenWeather.humidity + "%");
        System.out.println("Dewpoint Value: " + chosenWeather.dewpointVal + "°C");
        System.out.println("Wind Direction: " + chosenWeather.windDirection);
        System.out.println("Wind Speed: " + chosenWeather.windSpeed + " km/h");
        System.out.println("Start Time: " + chosenWeather.startTime);
        System.out.println("End Time: " + chosenWeather.endTime);
        System.out.println("Is Daytime: " + (chosenWeather.isDaytime ? "Yes" : "No"));
        System.out.println("Short Forecast: " + chosenWeather.shortForecastText);
        System.out.println("Long Forecast: " + chosenWeather.longForecastText);
        System.out.println("Icon: " + chosenWeather.icon);
        System.out.println("Precipitation: " + chosenWeather.precipitation.toString(2)); // Pretty print the JSON object with indentation
    }

    public String getWeatherInfoString() {
        Weather chosenWeather = this.weeklyWeather.get(0);
        StringBuilder sb = new StringBuilder();
        sb.append("Region ").append(this.city).append(", ").append(this.state).append(", ").append(this.country).append(" ");
        sb.append("Weather Information ");
        sb.append("Name ").append(chosenWeather.name).append(" ");
        sb.append("Temperature ").append(chosenWeather.temperature).append(" C ");
        sb.append("Humidity ").append(chosenWeather.humidity).append(" % ");
        sb.append("Dewpoint Value ").append(chosenWeather.dewpointVal).append(" C ");
        sb.append("Wind Direction ").append(chosenWeather.windDirection).append(" ");
        sb.append("Wind Speed ").append(chosenWeather.windSpeed).append(" km/h ");
        // sb.append("Start Time ").append(chosenWeather.startTime).append(" ");
        // sb.append("End Time ").append(chosenWeather.endTime).append(" ");
        sb.append("Is Daytime ").append(chosenWeather.isDaytime ? "Yes" : "No").append(" ");
        sb.append("Short Forecast ").append(chosenWeather.shortForecastText).append(" ");
        // sb.append("Long Forecast ").append(chosenWeather.longForecastText).append(" ");
        // sb.append("Precipitation: ").append(chosenWeather.precipitation.toString(2)).append("\n"); // Pretty print the JSON object with indentation
        return sb.toString();
    }


}