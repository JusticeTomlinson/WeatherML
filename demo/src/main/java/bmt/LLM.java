package bmt;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import io.github.cdimascio.dotenv.Dotenv;

public class LLM {

    private static final Dotenv dotenv = Dotenv.load();
    public static final String FUN_API_KEY = dotenv.get("FUN_API_KEY");    
    private static final String ENDPOINT = "https://api.openai.com/v1/completions";
    private static final String model = "gpt-3.5-turbo-instruct";
    
    public static String chatGPT(String text) throws Exception {
        String url = "https://api.openai.com/v1/completions";
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + FUN_API_KEY);

        JSONObject data = new JSONObject();
        data.put("model", model);
        data.put("prompt", text);
        data.put("max_tokens", 4000);
        data.put("temperature", 1.0);

        con.setDoOutput(true);
        con.getOutputStream().write(data.toString().getBytes());

        String output = new BufferedReader(new InputStreamReader(con.getInputStream())).lines()
                .reduce((a, b) -> a + b).get();

        String result = new JSONObject(output).getJSONArray("choices").getJSONObject(0).getString("text");
        System.out.println(result);
        return result;
    }

    public static String promptBuilder(String base, String info) {
        String prompt = base + info;
        return prompt;

    }

    public static void main(String[] args) throws Exception {
        String prompt = promptBuilder(model, model);
        chatGPT("Hello, how are you?");
    }

}