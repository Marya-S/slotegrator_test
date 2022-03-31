package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BasicPage{
    @FindBy(xpath = "//a[@data-target=\"#s-menu-users\"]")
    private WebElement menuUserButton;

    @FindBy(xpath = "//a[text()='Players']")
    private WebElement playersMenu;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void pushMenuUser(){
        menuUserButton.click();
    }

    public void pushPlayersMenu(){
        playersMenu.click();
    }
}
