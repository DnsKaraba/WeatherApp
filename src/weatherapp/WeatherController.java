package weatherapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import org.json.JSONObject;
import org.json.JSONException; // Import JSONException for catching JSON parsing exceptions

public class WeatherController {

    @FXML
    private TextField locationField;

    @FXML
    private Label temperatureLabel;

    @FXML
    private Label humidityLabel;

    @FXML
    private Label windSpeedLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private VBox root;

    private WeatherService weatherService;

    public WeatherController() {
        weatherService = new WeatherService();
    }

    @FXML
    private void initialize() {
        // Default values or initial setup
        temperatureLabel.setText("Temperature: N/A");
        humidityLabel.setText("Humidity: N/A");
        windSpeedLabel.setText("Wind Speed: N/A");
        descriptionLabel.setText("Description: N/A");
        root.setStyle("-fx-background-color: lightgray;");
    }

    @FXML
    private void handleGetWeather() {
        String location = locationField.getText();
        try {
            String weatherData = weatherService.getWeatherData(location);
            updateWeatherDisplay(weatherData);
        } catch (Exception e) {
            e.printStackTrace();
            updateLabelsWithError();
        }
    }

    private void updateWeatherDisplay(String weatherData) {
        JSONObject jsonObject = new JSONObject(weatherData);

        try {
            double temperature = jsonObject.getDouble("temperature");
            int humidity = jsonObject.getInt("humidity");
            double windSpeed = jsonObject.getDouble("wind_speed");
            String description = jsonObject.getString("description");

            // Update labels with parsed data
            temperatureLabel.setText("Temperature: " + temperature + "°C");
            humidityLabel.setText("Humidity: " + humidity + "%");
            windSpeedLabel.setText("Wind Speed: " + windSpeed + " km/h");
            descriptionLabel.setText("Description: " + description);
        } catch (JSONException e) {
            e.printStackTrace();
            updateLabelsWithError(); // Fallback to error message if JSON parsing fails
        }
    }

    private void updateLabelsWithError() {
        temperatureLabel.setText("Error fetching weather data.");
        humidityLabel.setText("");
        windSpeedLabel.setText("");
        descriptionLabel.setText("");
    }
}
