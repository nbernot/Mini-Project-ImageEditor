import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
class WeatherForecast{
    // This is the main function hehe
    public static void main(String[] args) {
        String baseURL = "https://api/open-meteo.com/v1/forecast";
        String extra = "location=Bloomington";
        String fullURL = baseURL + extra;
        try{
            URL url = new URL(fullURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        }


    }


    private static void printWeatherData(String ){


        System.out.println("Bloomington 7-Day Forecast Forecast in Fahrenheit:");
        for (int i = 0; i < ; i++) {

        }
    }
}



