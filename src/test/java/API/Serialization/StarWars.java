package API.Serialization;

import API.Pojo.SWPojo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public class StarWars {

    @Test
    public void getStaships() throws URISyntaxException, IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("swapi.dev/").setHost("swapi.dev/").setPath("api/starships");


        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader("Accept", "application/json");
        HttpResponse response = httpClient.execute(httpGet);

        ObjectMapper objectMapper = new ObjectMapper();

//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
//        SWPojo swPojo = objectMapper.readValue(response.getEntity().getContent(), SWPojo.class);

        SWPojo swPojo = objectMapper.readValue(response.getEntity().getContent(), SWPojo.class);
        System.out.println(swPojo.getResults().get(0).getName());


    }

}


