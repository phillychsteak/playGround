package API;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.it.Ma;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public class DeserializationIntro {

    @Test
    public void deserialization1() throws URISyntaxException, IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("petstore.swagger.io");
        uriBuilder.setPath("v2/pet/8989");

        HttpGet httpGet = new HttpGet(uriBuilder.build());

        httpGet.setHeader("Application", "application/json");

        HttpResponse response = httpClient.execute(httpGet);

        if (response.getStatusLine().getStatusCode() != 200) {
            Assert.fail();
        }


        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> desiralizeRespone = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {
                });

        System.out.println("Your id response is: " + desiralizeRespone.get("id"));
        System.out.println("Your name response is: " + desiralizeRespone.get("name"));
        System.out.println("Your category response is: " + desiralizeRespone.get("category"));

        Map<String, Object> category = (Map<String, Object>) desiralizeRespone.get("category");
        System.out.println(category.get("name"));
        System.out.println(category.get("id"));


    }

    @Test
    public void deserialization2() throws URISyntaxException, IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("reqres.in");
        uriBuilder.setPath("api/users/2");

        HttpGet httpGet = new HttpGet(uriBuilder.build());

        httpGet.setHeader("Application", "application/json");

        HttpResponse response = httpClient.execute(httpGet);

        if (response.getStatusLine().getStatusCode() != 200) {
            Assert.fail();
        }

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> desiralizeRespone = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {
                });

        Map<String, Object> category = (Map<String, Object>) desiralizeRespone.get("ad");
        System.out.println(category.get("company"));


    }

    @Test
    public void chuckNorris() throws URISyntaxException, IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("api.chucknorris.io/");
        uriBuilder.setPath("jokes/random");

        HttpGet httpGet = new HttpGet(uriBuilder.build());

        httpGet.setHeader("Accept", "application/json");

        HttpResponse response = httpClient.execute(httpGet);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> desiralizeRespone = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {
                });

        System.out.println(desiralizeRespone.get("value"));


    }

    @Test
    public void tronaldTrump() throws URISyntaxException, IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("www.tronalddump.io/");
        uriBuilder.setPath("random/quote");

        HttpGet httpGet = new HttpGet(uriBuilder.build());

        httpGet.setHeader("Accept", "application/json");

        HttpResponse response = httpClient.execute(httpGet);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> deserializeResponse = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {
                });

        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
        System.out.println(deserializeResponse.get("value"));

        Map<String, Object> links = (Map<String, Object>) deserializeResponse.get("_links");
        Map<String, String> self = (Map<String, String>) links.get("self");

        Object href = self.get("href");
        System.out.println(href);


    }

    @Test
    public void got() throws URISyntaxException, IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("got.show");
        uriBuilder.setPath("api/map/characters");

        HttpGet httpGet = new HttpGet(uriBuilder.build());

        httpGet.setHeader("Accept", "application/json");

        HttpResponse response = httpClient.execute(httpGet);

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> deserializeResponse = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {
                });


    }

    @Test
    public void testAPI() throws URISyntaxException, IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("reqres.in");
        uriBuilder.setPath("api/users?page");

        HttpGet httpGet = new HttpGet(uriBuilder.build());

        httpGet.setHeader("Accept", "application/json");

        HttpResponse response = httpClient.execute(httpGet);

        Assert.assertEquals(200, response.getStatusLine().getStatusCode());

        System.out.println(response.getStatusLine());


    }

    @Test
    public void catFacts() throws URISyntaxException, IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("cat-fact.herokuapp.com");
        uriBuilder.setPath("/facts");

        HttpGet httpGet = new HttpGet(uriBuilder.build());

        httpGet.setHeader("Accept", "application/json");

        HttpResponse response = httpClient.execute(httpGet);

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, List> parseResponse = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, List>>() {
                });

        parseResponse.get("all");

        System.out.println(parseResponse.get("all").size());


    }

    @Test
    public void nonCatFacts() throws URISyntaxException, IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("cat-fact.herokuapp.com");
        uriBuilder.setPath("/facts");

        HttpGet httpGet = new HttpGet(uriBuilder.build());

        httpGet.setHeader("Accept", "application/json");

        HttpResponse response = httpClient.execute(httpGet);

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, List<Map<String, Object>>> parseResponse = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, List<Map<String, Object>>>>() {
                });


        List<Map<String, Object>> mapList = parseResponse.get("all");

        for (Map<String, Object> text : mapList) {
            System.out.println(text.get("all"));
            Map<String, Object> info = (Map<String, Object>) text.get("user");
            Map<String, String> names = (Map<String, String>) info.get("name");

            System.out.println(names.get("first"));
            System.out.println(names.get("last"));


        }

    }

    @Test
    public void reqres() throws URISyntaxException, IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("reqres.in");
        uriBuilder.setPath("api/users?page");

        HttpGet httpGet = new HttpGet(uriBuilder.build());

        httpGet.setHeader("Accept", "application/json");

        HttpResponse response = httpClient.execute(httpGet);

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> userInfo = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {
                });

        System.out.println(userInfo.get("page"));
        System.out.println(userInfo.get("per_page"));
        System.out.println(userInfo.get("total"));
        System.out.println(userInfo.get("total_pages"));


        List<Map<String, Object>> users = (List<Map<String, Object>>) userInfo.get("data");
        // System.out.println(users);


        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i).get("first_name"));
        }

    }

    @Test
    public void petTest() throws URISyntaxException, IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("petstore.swagger.io");
        uriBuilder.setPath("/v2/pet");

        HttpGet httpGet = new HttpGet(uriBuilder.build());

        httpGet.setHeader("Accept", "application/json");

        HttpResponse response = httpClient.execute(httpGet);

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> userInfo = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {
                });



    }
}
