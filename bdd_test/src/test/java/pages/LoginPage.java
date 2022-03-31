package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasicPage {

    @FindBy(id = "UserLogin_username")
    private WebElement inputLogin;
    @FindBy(id = "UserLogin_password")
    private WebElement inputPassword;
    @FindBy(xpath = "//input[@type = 'submit']")
    private WebElement submit;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void putLoginAndPassword(String login, String password){
        inputLogin.sendKeys(login);
        inputPassword.sendKeys(password);
    }

    public void pushSignIn(){
        submit.click();
    }
}
