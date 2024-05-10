package bmt;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;


public class App {

    public static void main(String[] args) throws IOException, InterruptedException {
        Float[] historicalTemperature;
        // String startDate = LocalDate.of(2024, 1, 1);
        // String endDate = LocalDate.of(2024, 1, 7); // Example date range
        Float[] longlat = getLongLat("United?States", "Washington", "Seattle"); // Spaces need to be replaced with question mark
        getForecast(longlat[0], longlat[1]);
        }
    

public static Float[] getLongLat(String country, String state, String city) throws IOException, InterruptedException {
    Float longitudeFloat;
    Float latituFloat;

    String weatherUrl = String.format("https://nominatim.openstreetmap.org/search?");
    weatherUrl += String.format("&country=%s&state=%s&city=%s", country, state, city);
    weatherUrl += String.format("&format=json");
    JSONObject jsonObject = getJObject(weatherUrl);

    longitudeFloat = jsonObject.getFloat("lon");
    latituFloat = jsonObject.getFloat("lat");
    System.out.println(longitudeFloat);
    System.out.println(latituFloat);
    return new Float[]{longitudeFloat, latituFloat};
}

public static JSONObject getForecast(Float longiFloat, Float latitudeFloat) throws IOException, InterruptedException  {

        float latitude = Math.round(latitudeFloat * 10000) / 10000f;
        float longitude = Math.round(longiFloat * 10000) / 10000f;

        // extract the forecast endpoint
        String apiUrl = String.format("https://api.weather.gov/points/%s,%s", latitude, longitude); 
        System.out.println(apiUrl);
        JSONObject jsonObject = getJObject(apiUrl);
        JSONObject properties = jsonObject.getJSONObject("properties");
        String forecastURL = properties.getString("forecast");
        
        // extract the forecast information
        JSONObject forecastJSON = getJObject(forecastURL);

        return forecastJSON;

    }

public static JSONObject getJObject(String URIBuilder) throws IOException, InterruptedException {

    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .GET()
        .headers("accept", "application/json")
        .uri(URI.create(URIBuilder))
        .build();

    HttpResponse <String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    System.out.println(response);
    
    String responseBody = response.body();
    JSONObject jsonObject;

    if (responseBody.charAt(0) == '[') {
        JSONArray jsonArray = new JSONArray(responseBody);
        String obj = jsonArray.get(0).toString();
        jsonObject = new JSONObject(obj);

    }
    else{
         jsonObject = new JSONObject(responseBody);
    }

    return jsonObject;
} 

public static Forecast createForecastObject(JSONObject forecastJSON) {
    JSONObject properties = forecastJSON.getJSONObject("properties");
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
        String isDaytime = period.getString("isDaytime");
        String shortForecastText = period.getString("shortForecastText");
        String longForecastText = period.getString("longForecastText");
        String icon = period.getString("icon");
        String precipitation = period.getString("probabilityOfPrecipitation");

        Weather weatherObject = new Weather(name, temperature, humidity, dewpointVal, windDirection, windSpeed, startTime, endTime, isDaytime, shortForecastText, longForecastText, icon, precipitation);
        
        weatherList.add(weatherObject);
    }
    

    Forecast forecastObject = new Forecast(weatherList);



    return forecastObject;


}




// public static String formatURL(String input) {
//     return input.replace(" ", "+");
// }
}