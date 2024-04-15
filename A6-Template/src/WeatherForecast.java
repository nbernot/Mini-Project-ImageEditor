import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.stream.Collectors;

import com.google.gson.*;

class WeatherForecast {

    /**
     * Creates a URL connection, grabbing data about the weather in a certain
     * region and time frame
     * @param args main arguments
     */
    public static void main(String[] args) {
        double latitude = 39.168804;
        double longitude = -86.536659;
        String hourly = "temperature_2m";
        String unit = "fahrenheit";
        String timezone = "EST";
        String baseURL = "https://api.open-meteo.com/v1/forecast?";
        String extra = String.format("latitude=%f&longitude=%f&hourly=%s&temperature_unit=%s&timezone=%s",
                latitude, longitude, hourly, unit, timezone);
        String fullURL = baseURL + extra;

        // Tries a connection and results in error if not, catching it
        try{
            URL url = new URL(fullURL);
            HttpURLConnection connect = (HttpURLConnection) url.openConnection();
            connect.setRequestMethod("GET");

            int responseCode = connect.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connect.getInputStream()));
                String properResponse;
                properResponse = reader.lines().collect(Collectors.joining());
                reader.close();

                JsonElement jsonFE = JsonParser.parseString(properResponse);
                JsonObject jsonFO = jsonFE.getAsJsonObject();
                JsonArray timeHolder = jsonFO.get("hourly").getAsJsonObject().get("time").getAsJsonArray();
                JsonArray tempHolder = jsonFO.get("hourly").getAsJsonObject().get("temperature").getAsJsonArray();

                System.out.println(timeHolder);

                System.out.println("Bloomington 7 Day Forecast in Fahrenheit: ");
                for (int i = 0; i < timeHolder.size(); i++){
                    String date = timeHolder.get(i).getAsJsonObject().get("date").getAsString();
                    System.out.println("Forecast for " + date +":");
                    JsonArray temperatures = tempHolder.get(i).getAsJsonArray();
                    for (int j = 0; j < temperatures.size(); j++){
                        JsonObject tempOb = temperatures.get(j).getAsJsonObject();
                        String time = tempOb.get("time").getAsString();
                        double tempF = tempOb.get("tempF").getAsDouble();
                        System.out.println(time + ": " + tempF + "°F");
                    }
                }
            }
            else throw new IOException("HTTP request failed: " + responseCode);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Invalid URl");
        }
    }
}



