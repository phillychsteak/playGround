package Pages.Slack;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SlackPage {
    public SlackPage(WebDriver driver){ PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//div[@class='c-nav--signed-out']//*[.='Sign in']")
    public WebElement signIn;

    @FindBy(id = "domain")
    public WebElement textField;

    @FindBy(id = "submit_team_domain")
    public WebElement continueButton;

    @FindBy(id = "email")
    public WebElement emailField;

    @FindBy(id = "password")
    public WebElement passwordField;

    @FindBy(id = "signin_btn")
    public WebElement signInButton;

//    @FindBy(xpath = "//div[@aria-level='2']//span[@dir='auto']")
//    public List<WebElement>listOfUsers;
    @FindBy(xpath = "//span[contains(text(),'oopeace')]")
    public WebElement oopeaceChannel;

    @FindBy(id = "undefined")
    public WebElement messageBox;



}
