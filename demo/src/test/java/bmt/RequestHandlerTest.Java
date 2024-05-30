import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.*;
import org.mockito.*;
import java.net.http.HttpResponse;

public class RequestHandlerTest {

    @Test
    public void testSendRequestWithGet() throws Exception {
        // Mock HTTP client and response
        HttpClient client = mock(HttpClient.class);
        HttpResponse<String> response = mock(HttpResponse.class);
        when(client.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(response);
        when(response.body()).thenReturn("{\"key\":\"value\"}");

        Whitebox.setInternalState(RequestHandler.class, "client", client);

        JSONObject result = RequestHandler.sendRequest("http://example.com", "GET", null);

        assertNotNull(result);
        assertEquals("value", result.getString("key"));
    }
    
@Test
public void testGetForecastURL() throws Exception {
    // Mock sendRequest to return a predefined JSON object
    Mockito.when(RequestHandler.sendRequest(anyString(), anyString(), any(), any())).thenReturn(new JSONObject("{\"properties\":{\"forecast\":\"http://forecast.url\"}}"));

    String forecastUrl = RequestHandler.getForecastURL(40.712776f, -74.005974f);

    assertEquals("http://forecast.url", forecastUrl);
}

@Test
public void testGetOpenAIResponse() throws Exception {
    // Setup the expected payload
    JSONObject expectedPayload = new JSONObject()
            .put("model", "text-davinci-003")
            .put("prompt", "test prompt")
            .put("max_tokens", 5);

    // Mock sendRequest to verify it receives the right parameters
    Mockito.when(RequestHandler.sendRequest(eq("https://api.openai.com/v1/completions"), eq("POST"), eq(expectedPayload), anyString(), anyString())).thenReturn(new JSONObject("{}"));

    JSONObject response = RequestHandler.getOpenAIResponse("test prompt", 5);

    assertNotNull(response);
}

}
