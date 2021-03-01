package Utils;

import API.Pojo.JiraPojo;
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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PayloadUtils {

public static String getPayload(int id,String name, String status){
    return
    "{\n" +
            "  \"id\": "+id+",\n" +
            "  \"category\": {\n" +
            "    \"id\": 0,\n" +
            "    \"name\": \"string\"\n" +
            "  },\n" +
            "  \"name\": \""+name+"\",\n" +
            "  \"photoUrls\": [\n" +
            "    \"string\"\n" +
            "  ],\n" +
            "  \"tags\": [\n" +
            "    {\n" +
            "      \"id\": 0,\n" +
            "      \"name\": \"string\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"status\": \""+status+"\"\n" +
            "}";
}
public static String generateStringFromResource(String path) throws IOException {

    String petPayload = new String(Files.readAllBytes(Paths.get(path)));
    return petPayload;
}
public static String slackPayload(String channel, String text){
    return
    "{\n" +
            "    \"channel\": \""+channel+"\",\n" +
            "    \"text\": \""+text+"\"\n" +
            "}";

}
    public static String getCookieAuthPayload() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();

        uriBuilder.setScheme("http");
        uriBuilder.setHost("localhost");
        uriBuilder.setPort(8080);
        uriBuilder.setPath("rest/auth/1/session");


        HttpPost httpPost = new HttpPost(uriBuilder.build());
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/json");


        HttpEntity httpEntity = new StringEntity(logIn(ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")));
        httpPost.setEntity(httpEntity);
        HttpResponse response = client.execute(httpPost);

        ObjectMapper objectMapper = new ObjectMapper();

        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());

        JiraPojo jiraPojo = objectMapper.readValue(response.getEntity().getContent(),
                JiraPojo.class);

        String cookieName=jiraPojo.getSession().get("name");
        String cookieValue=jiraPojo.getSession().get("value");


        return String.format("%s=%s", cookieName, cookieValue);
    }

public static String logIn(String username, String password){
    return "{\n" +
            "    \"username\": \""+username+"\",\n" +
            "    \"password\": \""+password+"\"\n" +
            "}";
}
public static String getJiraIssuePayload(String summary, String description, String issueType){

    return "{\n" +
            "    \"fields\": {\n" +
            "        \"project\": {\n" +
            "            \"key\": \"TEC\"\n" +
            "        },\n" +
            "        \"summary\": \""+summary+"\",\n" +
            "        \"description\": \""+description+"\",\n" +
            "        \"issuetype\": {\n" +
            "            \"name\": \""+issueType+"\"\n" +
            "        }\n" +
            "    }\n" +
            "}";
}



}
