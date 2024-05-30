package bmt;

import org.json.JSONObject;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author justi
 * Tests that the constructor works.
 */
public class WeatherTest {
    
@Test
public void testWeatherConstructor() {
        JSONObject precipitation = new JSONObject("{\"amount\":5,\"type\":\"rain\"}");
        Weather weather = new Weather("San Francisco", 65, 75, 52, "NE", "5 mph", "2024-05-20T00:00:00Z", "2024-05-20T12:00:00Z", true, "Partly cloudy", "Partly cloudy throughout the day.", "icon_url", precipitation);

        assertEquals("San Francisco", weather.name);
        assertEquals(65, weather.temperature);
        assertEquals(75, weather.humidity);
        assertEquals(52, weather.dewpointVal);
        assertEquals("NE", weather.windDirection);
        assertEquals("5 mph", weather.windSpeed);
        assertEquals("2024-05-20T00:00:00Z", weather.startTime);
        assertEquals("2024-05-20T12:00:00Z", weather.endTime);
        assertTrue(weather.isDaytime);
        assertEquals("Partly cloudy", weather.shortForecastText);
        assertEquals("Partly cloudy throughout the day.", weather.longForecastText);
        assertEquals("icon_url", weather.icon);
        assertEquals(precipitation.toString(), weather.precipitation.toString());
}

}
