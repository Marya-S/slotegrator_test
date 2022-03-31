package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class PlayerPage extends BasicPage {
    @FindBy(id = "payment-system-transaction-grid")
    private WebElement tablePlayer;

    @FindBy(xpath = "//a[text()='Username']")
    private WebElement usernameHeader;

    @FindBy(xpath = "//tbody//td[2]/a")
    private List<WebElement> userNameList;

    public PlayerPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public Boolean tableIsLoad(){
        return tablePlayer.isDisplayed();
    }

    public void pushUsernameHeader(){
        usernameHeader.click();
    }

    public List<String> userNameList(){
        List<String> userList = new ArrayList<>();
        for (WebElement it: userNameList){
            userList.add(it.getText());
        }
        return userList;
    }
}
