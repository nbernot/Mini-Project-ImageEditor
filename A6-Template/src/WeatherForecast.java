import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.google.gson.*;
class WeatherForecast{
    // This is the main function hehe
    public static void main(String[] args) {
        double latitude = 40.7128;
        double longitude = -74.00;
        String hourly = "temperature_2m";
        String unit = "fahrenheit";
        String timezone = "EST";
        String baseURL = "https://api.open-meteo.com/v1/forecast?";
        String extra = String.format("latitude=%f&longitude=%f&hourly=temperature_2m&temperature_unit=%s&timezone=%s",
                latitude, longitude, hourly, timezone);
        String fullURL = baseURL + extra;
        try{
            URL url = new URL(fullURL);
            HttpURLConnection connect = (HttpURLConnection) url.openConnection();
            connect.setRequestMethod("GET");

            int responseCode = connect.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                BufferedReader reader = new BufferedReader(new InputStreamReader(connect.getInputStream()));
                StringBuilder properResponse = new StringBuilder();
                String newline;
                while ((newline = reader.readLine()) != null){
                    properResponse.append(newline);
                }
                reader.close();

                JsonElement jsonFE = JsonParser.parseString(properResponse.toString());
                JsonObject jsonFO = jsonFE.getAsJsonObject();
                JsonArray timeHolder = jsonFO.get("times").getAsJsonArray();
                JsonArray tempHolder = jsonFO.get("temperatures").getAsJsonArray();

                System.out.println("Bloomington 7 Day Forecast in Fahrenheit: ");
                for (int i = 0; i< timeHolder.size();i++){
                    String date = timeHolder.get(i).getAsJsonObject().get("date").getAsString();
                    System.out.println("Forecast for " + date +":");
                    JsonArray temperatures = tempHolder.get(i).getAsJsonArray();
                    for (int j =0; j<temperatures.size();j++){
                        JsonObject tempOb = temperatures.get(j).getAsJsonObject();
                        String time = tempOb.get("time").getAsString();
                        double tempF = tempOb.get("tempF").getAsDouble();
                        System.out.println(time + ": " + tempF + "°F");
                    }
                }

            }
            else{
                throw new IOException("HTTP request failed: " + responseCode);
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Invalid URl");
        }
    }
}



