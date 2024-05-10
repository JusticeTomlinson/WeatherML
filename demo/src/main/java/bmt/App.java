package bmt;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        ForecastFactory forecastFactory = new ForecastFactory("United?States", "Washington", "Seattle"); 
        forecastFactory.creation();
    }
}