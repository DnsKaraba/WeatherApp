package weatherapp;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

public class WeatherService {
    private static final String USERNAME = "none_karaba_denis";
    private static final String PASSWORD = "SiV1p3D60q";
    private static final String BASE_API_URL = "https://api.meteomatics.com/2024-08-15T21:55:00.000+03:00--2024-08-16T21:55:00.000+03:00:PT5M/t_2m:C/t_2m:C,precip_1h:mm,wind_speed_10m:ms/1.4419683,38.4313975/json";

    public String getWeatherData(String location) throws IOException, InterruptedException {
        String auth = USERNAME + ":" + PASSWORD;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        String apiUrl = BASE_API_URL + location;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Authorization", "Basic " + encodedAuth)
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}
