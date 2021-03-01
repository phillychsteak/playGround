package API;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.Map;

import static Utils.PayloadUtils.getPayload;

public class POSTRequestIntro {

   // Steps to execute a API call/request:
//        Open/ launch client (open POSTMAN)
//        Specify the method type (GET/POST/PUT/DELETE)
//        Specify the URL/URI( Uniform Resource locator)/(Uniform Resource Identifier) URL == URI
//        Execute (click on send button)
//        Check the response status code
//        Check the response body

    @Test
    public void postRequest() throws URISyntaxException, IOException {

        //Open/ launch client (open POSTMAN)
        HttpClient httpClient = HttpClientBuilder.create().build();

        //
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("reqres.in");
        uriBuilder.setPath("api/users");

        HttpPost httpPost = new HttpPost(uriBuilder.build());
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Accept", "application/json");

        HttpEntity httpEntity = new StringEntity("{\n" +
                "    \"name\": \"Jim\",\n" +
                "    \"job\": \"Carrey\"\n" +
                "}");

        httpPost.setEntity(httpEntity);

        HttpResponse response=httpClient.execute(httpPost);
        Assert.assertEquals(HttpStatus.SC_CREATED, response.getStatusLine().getStatusCode());

    }

    @Test
    public void newPet() throws URISyntaxException, IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("petstore.swagger.io");
        uriBuilder.setPath("v2/pet");

        HttpPost httpPost = new HttpPost(uriBuilder.build());
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Accept", "application/json");

        int id = 2020;
        String name = "Avocado";
        String status = "Take it and go!";

        HttpEntity httpEntity = new StringEntity(getPayload(id, name, status));

        httpPost.setEntity(httpEntity);

        HttpResponse response = httpClient.execute(httpPost);
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());

        System.out.println("Your status code is: "+response.getStatusLine().getStatusCode());

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> dogInfo= objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {
                });

        int actualId= (int)dogInfo.get("id");
        String actualName = dogInfo.get("name").toString();
        String actualStatus = dogInfo.get("status").toString();

        Assert.assertEquals(id, actualId);
        Assert.assertEquals(name,actualName);
        Assert.assertEquals(status, actualStatus);

        uriBuilder.setPath("v2/pet/" + id);
        HttpGet get  =new HttpGet(uriBuilder.build());
        get.setHeader("Accept", "application/json");
        response = httpClient.execute(get);

        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());

        dogInfo= objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {
                });

        Assert.assertEquals(id, dogInfo.get("id"));
        Assert.assertEquals(name,dogInfo.get("name"));
        Assert.assertEquals(status, dogInfo.get("status"));

    }

}
