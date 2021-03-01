package StepDefinitions.amazon;

import Pages.Amazon.AmazonLinksPage;
import Utils.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class AmazonLinks {

    WebDriver driver = Driver.getDriver();
    AmazonLinksPage amazonLinksPage= new AmazonLinksPage(driver);

    @When("user goes to amazon.com")
    public void user_goes_to_amazon_com() {
    driver.navigate().to("https://www.amazon.com/");

    }

    @Then("print out  links")
    public void print_out_working_links() {

//        int count =0;
//        List<WebElement>links = amazonLinksPage.allLinks;
//        for(WebElement link : links){
//
//            System.out.println(link.getAttribute("href"));
//            count++;
//        }

    }
    @Then("print out only working links")
    public void print_out_only_working_links() throws URISyntaxException, IOException {

        List<WebElement>links = amazonLinksPage.allLinks;

        List<String>working=new ArrayList<>();
        List<String>broken = new ArrayList<>();
        for (WebElement link : links){

            if(link.getAttribute("href").startsWith("http") && !(link.getAttribute("href").equals(null))) {

                HttpClient httpClient = HttpClientBuilder.create().build();
                URIBuilder uriBuilder = new URIBuilder(link.getAttribute("href"));
                HttpGet httpGet = new HttpGet(uriBuilder.build());
                httpGet.setHeader("Accept", "application/json");
                HttpResponse response = httpClient.execute(httpGet);


                if (response.getStatusLine().getStatusCode() == 200) {
                    working.add(link.getAttribute("href"));

                } else {
                    broken.add(link.getAttribute("href"));
                    System.out.println(link.getAttribute("href"));

                }
            }else {
                broken.add(link.getAttribute("href"));
                System.out.println(link.getAttribute("href"));
            }

        }
        System.out.println(broken.size());
        System.out.println(working.size());

    }
}
