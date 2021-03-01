package Pages.Amazon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AmazonLinksPage {

    public AmazonLinksPage(WebDriver driver){ PageFactory.initElements(driver, this); }

    @FindBy(xpath = "//*[@href]")
    public List<WebElement> allLinks;
}
