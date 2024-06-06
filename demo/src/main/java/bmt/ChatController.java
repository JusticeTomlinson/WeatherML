package bmt;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@RestController
@RequestMapping("/bmt")
public class ChatController {

    @GetMapping("/getActivities")
    public String getActivities() throws IOException, InterruptedException, Exception {
        ForecastFactory forecastFactory = new ForecastFactory("United States", "Washington", "Seattle");
        forecastFactory.creation();
        Forecast forecast = forecastFactory.forecast;

        String base = "I need you to recommend 3 specific fun activities to do given the following weather ";
        String prompt = base + forecast.getWeatherInfoString();

        String response = LLM.chatGPT(prompt);
        return response;
    }
}
