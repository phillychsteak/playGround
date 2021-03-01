package API.Jira;

import API.Pojo.JiraPojo;
import Utils.ConfigReader;
import com.fasterxml.jackson.core.type.TypeReference;
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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.Map;

import static Utils.PayloadUtils.*;

public class CookieAuth {
    @Test
    public void getCookie() throws URISyntaxException, IOException {


        HttpClient client = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();

        uriBuilder.setScheme("http");
        uriBuilder.setHost("localhost");
        uriBuilder.setPort(8080);
        uriBuilder.setPath("rest/auth/1/session");


        HttpPost httpPost = new HttpPost(uriBuilder.build());
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/json");

        HttpEntity httpEntity = new StringEntity(logIn(ConfigReader.getProperty("username")
        , ConfigReader.getProperty("password")));
        httpPost.setEntity(httpEntity);
        HttpResponse response = client.execute(httpPost);

        ObjectMapper objectMapper = new ObjectMapper();

        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());

        JiraPojo jiraPojo = objectMapper.readValue(response.getEntity().getContent(),
                JiraPojo.class);

        System.out.println(jiraPojo.getSession().get("name"));
        System.out.println(jiraPojo.getSession().get("value"));

    }

    @Test
    public void sendCookie() throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();

        uriBuilder.setScheme("http");
        uriBuilder.setHost("localhost");
        uriBuilder.setPort(8080);
        uriBuilder.setPath("rest/auth/2/issue");


        HttpPost httpPost = new HttpPost(uriBuilder.build());
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Cookie", getCookieAuthPayload());

    //    System.out.println(getCookieAuthPayload());
        HttpEntity httpEntity = new StringEntity(getJiraIssuePayload("Creating a bug from API",
                "Create a bug using back-end API call, and verify in UI",
                "Bug"));
        httpPost.setEntity(httpEntity);
        HttpResponse response = client.execute(httpPost);

    //    System.out.println(getJiraIssuePayload("Hello", "World", "Bug"));
        ObjectMapper objectMapper = new ObjectMapper();

        Assert.assertEquals(HttpStatus.SC_CREATED, response.getStatusLine().getStatusCode());
        Map<String,String>responseParse = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, String>>() {
                });

        for(String key: responseParse.keySet()){
            System.out.println(key+" : "+responseParse.get(key));
        }

    }

}
