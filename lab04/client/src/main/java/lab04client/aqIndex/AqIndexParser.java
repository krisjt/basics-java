package lab04client.aqIndex;

import com.google.gson.Gson;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class AqIndexParser {
    public static Values getRestData(int id){
        String url = "https://api.gios.gov.pl/pjp-api/rest/aqindex/getIndex/"+id;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).timeout(Duration.of(10, ChronoUnit.SECONDS)).build();

        return  client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(AqIndexParser::aqIndexParsing)
                .join();
    }

    private static Values aqIndexParsing(String responseBody){
        Gson gson = new Gson();
        return gson.fromJson(responseBody, Values.class);
    }
}
