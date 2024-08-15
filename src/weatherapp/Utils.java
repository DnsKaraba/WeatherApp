package weatherapp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class Utils {

    // Method to convert a timestamp to a formatted date string
    public static String formatTimestamp(String timestamp) {

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        LocalDateTime dateTime = LocalDateTime.parse(timestamp, inputFormatter);
        
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(outputFormatter);
    }

    // Method to calculate the difference between two timestamps
    public static long calculateDuration(String startTimestamp, String endTimestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        ZonedDateTime startDateTime = ZonedDateTime.parse(startTimestamp, formatter);
        ZonedDateTime endDateTime = ZonedDateTime.parse(endTimestamp, formatter);

        return ChronoUnit.MINUTES.between(startDateTime, endDateTime);
    }

    // Method to get the current time in a specific format
    public static String getCurrentTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }

    // Method to get current date in ISO format
    public static String getCurrentDateISO() {
        return LocalDateTime.now().toLocalDate().toString();
    }

    // Method to convert temperature from Celsius to Fahrenheit
    public static double celsiusToFahrenheit(double celsius) {
        return (celsius * 9/5) + 32;
    }

    // Method to convert wind speed from m/s to km/h
    public static double metersPerSecondToKilometersPerHour(double mps) {
        return mps * 3.6;
    }

    // Method to convert precipitation from mm to inches
    public static double millimetersToInches(double mm) {
        return mm / 25.4;
    }
}
