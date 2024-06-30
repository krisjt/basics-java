
package lab04client.measurementStation;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;


public class MeasurementStationsParser {
    public static MeasurementStation[] getMeasurementStationsList(){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.gios.gov.pl/pjp-api/rest/station/findAll")).timeout(Duration.of(10, ChronoUnit.SECONDS)).build();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(MeasurementStationsParser::measurementStationsListParsing)
                .join();
    }
    private static MeasurementStation[] measurementStationsListParsing(String responseBody){
        Gson gson = new Gson();

        return gson.fromJson(responseBody, MeasurementStation[].class);
    }

    public static MeasurementSensor[] getMeasurementStation(int id){
        String url = "https://api.gios.gov.pl/pjp-api/rest/station/sensors/"+id;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).timeout(Duration.of(10, ChronoUnit.SECONDS)).build();
        return  client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(MeasurementStationsParser::measurementStationParsing)
                .join();
    }

    private static MeasurementSensor[] measurementStationParsing(String responseBody){
        Gson gson = new Gson();
        return gson.fromJson(responseBody, MeasurementSensor[].class);
    }
}