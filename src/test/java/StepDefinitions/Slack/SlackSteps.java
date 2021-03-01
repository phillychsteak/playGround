package StepDefinitions.Slack;

import Pages.Slack.SlackPage;
import Utils.ConfigReader;
import Utils.Driver;
import Utils.PayloadUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public class SlackSteps {

    WebDriver driver = Driver.getDriver();
    HttpClient httpClient = HttpClientBuilder.create().build();
    HttpResponse response;
    URIBuilder uriBuilder = new URIBuilder();
    ObjectMapper objectMapper = new ObjectMapper();
    public static String ts;
    String channel="C0164SXRETU";
    String expectedMes = ConfigReader.getProperty("message");



    @Test
    @When("Send message to slack via POST request")
    public void send_message_to_slack_via_POST_request() throws URISyntaxException, IOException {
        driver.get(ConfigReader.getProperty("url"));
        uriBuilder.setScheme("https").setHost("slack.com").setPath("api/chat.postMessage");
        HttpPost post = new HttpPost(uriBuilder.build());
        post.setHeader("Content-Type", "application/json");
        post.setHeader("Authorization", ConfigReader.getProperty("token"));

        HttpEntity entity = new StringEntity(PayloadUtils.slackPayload(channel, expectedMes));
        post.setEntity(entity);

        response=httpClient.execute(post);

        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());

        Map<String, Object>parseResppnse = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {
                });
        ts=(String)parseResppnse.get("ts");




    }

    @Then("Verify the message via GET request")
    public void verify_the_message_via_GET_request() {

    }

















//    WebDriver driver = Driver.getDriver();
//    SlackPage slackPage = new SlackPage(driver);
//
//    @Given("user goes to slack.com page and loggs in {string} and {string}")
//    public void user_goes_to_slack_com_page_and_loggs_in_and(String login, String password) {
//        driver.navigate().to(ConfigReader.getProperty("slack"));
//        slackPage.signIn.click();
//        slackPage.textField.sendKeys("techtorialbatch4");
//        slackPage.continueButton.click();
//        slackPage.emailField.sendKeys(login);
//        slackPage.passwordField.sendKeys(password);
//        slackPage.signInButton.click();
//
//    }
//
//    @When("user sending messages")
//    public void user_sending_messages() {
//
////        List<WebElement> allUsers = slackPage.listOfUsers;
////        for(WebElement user : allUsers){
////
////
////        }
////
//
//        slackPage.oopeaceChannel.click();
//        slackPage.messageBox.sendKeys("Lyubov' vsei moei jizni - Dinaramix(Aka malysh)");
//        slackPage.messageBox.sendKeys(Keys.ENTER);
//
//    }
//
//    @Then("user verifies messages are sent and delivered")
//    public void user_verifies_messages_are_sent_and_delivered() {
//
//    }


}
