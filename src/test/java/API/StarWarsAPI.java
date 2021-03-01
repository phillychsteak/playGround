package API;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StarWarsAPI {
    @Test
    public void starWars() throws URISyntaxException, IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("swapi.dev/");
        uriBuilder.setPath("api/planets");

        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader("Accept", "application/json");
        HttpResponse response = httpClient.execute(httpGet);

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> parseResponse = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {
                });
        List<Map<String, Object>> results = (List<Map<String, Object>>) parseResponse.get("results");

        Map<String, String> planetPopulation = new HashMap<>();

        for (int i = 0; i < results.size(); i++) {
            String planet = results.get(i).get("population").toString();
            String populaton = results.get(i).get("population").toString();
            planetPopulation.put(planet, populaton);

        }
        System.out.println(planetPopulation);

    }
}