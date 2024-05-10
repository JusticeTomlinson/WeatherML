package bmt;
import java.io.IOException;

import org.json.JSONObject;



public class Location {

public static Float[] getLongLat(String country, String state, String city) throws IOException, InterruptedException {
    Float longitudeFloat;
    Float latituFloat;

    String weatherUrl = String.format("https://nominatim.openstreetmap.org/search?");
    weatherUrl += String.format("&country=%s&state=%s&city=%s", country, state, city);
    weatherUrl += String.format("&format=json");
    JSONObject jsonObject = RequestHandler.sendRequest(weatherUrl, "GET", null, "application/json");;

    longitudeFloat = jsonObject.getFloat("lon");
    latituFloat = jsonObject.getFloat("lat");
    System.out.println(longitudeFloat);
    System.out.println(latituFloat);
    return new Float[]{longitudeFloat, latituFloat};
}


}