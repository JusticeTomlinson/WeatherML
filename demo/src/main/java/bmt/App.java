package bmt;

import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public static void main(String[] args) throws IOException, InterruptedException, Exception {
        SpringApplication.run(App.class, args);
        ForecastFactory forecastFactory = new ForecastFactory("United?States", "Washington", "Seattle"); 
        forecastFactory.creation(); 
        Forecast forecast = forecastFactory.forecast;
        // forecast.displayWeather();

        String base = "I need you to recommend 3 specific fun activities to do given the following weather ";
        String prompt = base + forecast.getWeatherInfoString();
        System.out.println(prompt);
     
        LLM.chatGPT(prompt);
    }
}