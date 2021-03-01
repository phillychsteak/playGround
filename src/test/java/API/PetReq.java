package API;

import API.Pojo.PetPojo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class PetReq {
    @Test
    public void getPet() throws URISyntaxException, IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("petstore.swagger.io");
        uriBuilder.setPath("/v2/pet/2020");

        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader("Content-Type", "application/json");
        httpGet.setHeader("Accept", "application/json");

        HttpResponse response = httpClient.execute(httpGet);
        ObjectMapper objectMapper =new ObjectMapper();

        PetPojo desirializeObject = objectMapper.readValue(response.getEntity().getContent(),
                PetPojo.class);

        System.out.println(desirializeObject.getId());
        System.out.println(desirializeObject.getName());

    }
    @Test
    public void reqres() throws IOException, URISyntaxException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("petstore.swagger.io");
        uriBuilder.setPath("/v2/pet/2020");

        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader("Content-Type", "application/json");
        httpGet.setHeader("Accept", "application/json");

        HttpResponse response = httpClient.execute(httpGet);
        ObjectMapper objectMapper =new ObjectMapper();


    }

}
