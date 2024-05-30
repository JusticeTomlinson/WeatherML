package bmt;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
public class ForecastTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testDisplayWeather() {
        // Setup
        ArrayList<Weather> weeklyWeather = new ArrayList<>();
        Weather weather = new Weather(
            "Sunny",        // Name
            25,             // Temperature
            50,             // Humidity
            15,             // Dewpoint Value
            "North",        // Wind Direction
            "10",             // Wind Speed
            "Morning",      // Start Time
            "Evening",      // End Time
            true,           // Is Daytime
            "Clear sky",    // Short Forecast Text
            "Clear throughout the day", // Long Forecast Text
            null,           // Icon
            null            // Precipitation (JSONObject)
        );

        weeklyWeather.add(weather);
        Forecast forecast = new Forecast("USA", "California", "San Francisco", weeklyWeather);

        // Execute
        forecast.displayWeather();

        // Verify
        String expectedOutput = "Region: San Francisco, California, USA\n" +
                                "Weather Information:\n" +
                                "Name: Sunny\n" +
                                "Temperature: 25°C\n" +
                                "Humidity: 50%\n" +
                                "Dewpoint Value: 15°C\n" +
                                "Wind Direction: North\n" +
                                "Wind Speed: 10 km/h\n" +
                                "Start Time: Morning\n" +
                                "End Time: Evening\n" +
                                "Is Daytime: Yes\n" +
                                "Short Forecast: Clear sky\n" +
                                "Long Forecast: Clear throughout the day\n" +
                                "Icon: null\n" +
                                "Precipitation: \n"; 

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testGetWeatherInfoString() {
        // Setup
        ArrayList<Weather> weeklyWeather = new ArrayList<>();

        Weather weather = new Weather(
            "Sunny",        // Name
            25,             // Temperature
            50,             // Humidity
            15,             // Dewpoint Value
            "North",        // Wind Direction
            "10",             // Wind Speed
            "Morning",      // Start Time
            "Evening",      // End Time
            true,           // Is Daytime
            "Clear sky",    // Short Forecast Text
            "Clear throughout the day", // Long Forecast Text
            null,           // Icon
            null            // Precipitation (JSONObject)
        );        

        weeklyWeather.add(weather);
        Forecast forecast = new Forecast("USA", "California", "San Francisco", weeklyWeather);

        // Execute
        String result = forecast.getWeatherInfoString();

        // Verify
        String expectedString = "Region San Francisco, California, USA Weather Information Name Sunny Temperature 25 C Humidity 50 % Dewpoint Value 15 C Wind Direction North Wind Speed 10 km/h Is Daytime Yes Short Forecast Clear sky ";
        assertEquals(expectedString, result);

}
}
