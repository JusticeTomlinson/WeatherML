package bmt;
import java.io.IOException;

import org.json.JSONObject;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;

public class LocationTest {

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        PowerMockito.mockStatic(RequestHandler.class);
    }

@Test
public void testGetLongLatValidInput() throws Exception {

    String country = "USA";
    String state = "California";
    String city = "San Francisco";
    JSONObject fakeResponse = new JSONObject();
    fakeResponse.put("lon", -122.4194);
    fakeResponse.put("lat", 37.7749);


    when(RequestHandler.sendRequest(anyString(), anyString(), any(), anyString())).thenReturn(fakeResponse);


    Float[] result = Location.getLongLat(country, state, city);


    assertNotNull(result);
    assertEquals(Float.valueOf((float) -122.4194), result[0]);
    assertEquals(Float.valueOf((float) 37.7749), result[1]);
    
}

    @Test
    public void testGetLongLatInvalidInput() throws Exception {

        String country = "UnknownCountry";
        String state = "UnknownState";
        String city = "UnknownCity";
        JSONObject fakeResponse = new JSONObject();
        fakeResponse.put("lon", JSONObject.NULL);
        fakeResponse.put("lat", JSONObject.NULL);


        when(RequestHandler.sendRequest(anyString(), anyString(), any(), anyString())).thenReturn(fakeResponse);


        Float[] result = Location.getLongLat(country, state, city);


        assertNull("Longitude should be null for invalid input", result[0]);
        assertNull("Latitude should be null for invalid input", result[1]);
    }

    @Test(expected = IOException.class)
    public void testGetLongLatWithIOException() throws Exception {

        String country = "USA";
        String state = "California";
        String city = "San Francisco";


        when(RequestHandler.sendRequest(anyString(), anyString(), any(), anyString())).thenThrow(new IOException("Failed to reach server"));


        Location.getLongLat(country, state, city); 
    }

    @Test(expected = InterruptedException.class)
    public void testGetLongLatWithInterruptedException() throws Exception {

        String country = "USA";
        String state = "California";
        String city = "San Francisco";


        when(RequestHandler.sendRequest(anyString(), anyString(), any(), anyString())).thenThrow(new InterruptedException("Request interrupted"));


        Location.getLongLat(country, state, city); // This should throw InterruptedException
    }}

