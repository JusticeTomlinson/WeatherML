package bmt;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;

public class RequestHandler {
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final String OPENAI_API_KEY = "YOUR_OPENAI_API_KEY";
    private static final String OPENAI_ENDPOINT = "https://api.openai.com/v1/completions";


    public static JSONObject sendRequest(String URIBuilder, String method, JSONObject payload, String... headers) throws IOException, InterruptedException {
    
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder().uri(URI.create(URIBuilder));

        // add headers optionally 

        if (headers.length > 0) {
            for (int i = 0; i > headers.length; i +=2) {
                requestBuilder.header(headers[i], headers[i + 1]);
            }
        }

        // Set the request method and payload
        if ("POST".equalsIgnoreCase(method) && payload != null) {
            requestBuilder.POST(HttpRequest.BodyPublishers.ofString(payload.toString()));
        } else {
            requestBuilder.GET();
        }

        HttpRequest request = requestBuilder.build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();

        if (responseBody.charAt(0) == '[') {
            JSONArray jsonArray = new JSONArray(responseBody);
            responseBody = jsonArray.get(0).toString();
        }
        
        JSONObject jsonObject = new JSONObject(responseBody);
        
        return jsonObject;
    }

    public static String getForecastURL(Float longiFloat, Float latitudeFloat) throws IOException, InterruptedException  {

            float latitude = Math.round(latitudeFloat * 10000) / 10000f;
            float longitude = Math.round(longiFloat * 10000) / 10000f;

            // extract the forecast endpoint
            String apiUrl = String.format("https://api.weather.gov/points/%s,%s", latitude, longitude); 
            System.out.println(apiUrl);
            // JSONObject jsonObject = getJObject(apiUrl);
            JSONObject jsonObject = sendRequest(apiUrl, "GET", null, "application/json");
            JSONObject properties = jsonObject.getJSONObject("properties");
            String forecastURL = properties.getString("forecast");
            return forecastURL;
    }

    public static JSONObject getForecastSeries(String forecastURL) throws IOException, InterruptedException  {
            // extract the forecast information
            // JSONObject forecastJSON = getJObject(forecastURL);
            JSONObject forecastJSON = sendRequest(forecastURL, "GET", null, "application/json");
            JSONObject properties = forecastJSON.getJSONObject("properties");

            return properties;
        }

        public static JSONObject getOpenAIResponse(String prompt, int maxTokens) throws IOException, InterruptedException {


        JSONObject payload = new JSONObject()
            .put("model", "text-davinci-003")
            .put("prompt", prompt)
            .put("max_tokens", maxTokens);

        return sendRequest(
            OPENAI_ENDPOINT,
            "POST",
            payload,
            "Content-Type", "application/json",
            "Authorization", "Bearer " + OPENAI_API_KEY
        );
}
}