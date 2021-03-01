package API.Serialization;

import Utils.PayloadUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SerializationIntro {
    @Test
    public void serialization() throws IOException {
        Pet pet = new Pet("Reks", "ready", 3);
        pet.setId(2020);
        pet.setPhotoUrl("http://s3.petpics.amazon.com");

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.writeValue(new File("target/pet.json"),pet);
    }
    @Test
    public void createPet() throws URISyntaxException, IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setHost("petstore.swagger.io").setPath("/v2/pet");

        HttpPost httpPost = new HttpPost(uriBuilder.build());
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Accept", "application/json");

        String petPayload = PayloadUtils.generateStringFromResource("target/pet.json");

        HttpEntity entity = new StringEntity(petPayload);

        httpPost.setEntity(entity);

        HttpResponse response = httpClient.execute(httpPost);

        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
    }
}
