package lab04client.data;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class DataParser {
        public static Data getRestData(int id){
                String url = "https://api.gios.gov.pl/pjp-api/rest/data/getData/"+id;
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).timeout(Duration.of(10, ChronoUnit.SECONDS)).build();
                return  client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                        .thenApply(HttpResponse::body)
                        .thenApply(DataParser::restDataParsing)
                        .join();
        }

        private static Data restDataParsing(String responseBody){
                Gson gson = new Gson();
                return gson.fromJson(responseBody, Data.class);
        }
}
