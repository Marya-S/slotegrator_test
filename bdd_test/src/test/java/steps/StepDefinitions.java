package steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.MainPage;
import pages.PlayerPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StepDefinitions {
    WebDriver driver;
    LoginPage loginPage;
    MainPage mainPage;
    PlayerPage playerPage;

    @Дано("Перейти на страницу авторизации {string}")
    public void openLoginPage(String url) {
        driver = new ChromeDriver();
        driver.get(url);
    }

    @И("Ввести логин {string}  и пароль {string}")
    public void putLogin(String login, String password) {
        loginPage = new LoginPage(driver);
        loginPage.putLoginAndPassword(login, password);
    }

    @Когда("^Нажать авторизоваться")
    public void pushLogin() {
        loginPage = new LoginPage(driver);
        loginPage.pushSignIn();
    }

    @Тогда("Пользователю открылась главная страница")
    public void checkMainPageIsOpen() {
        assertTrue(
                driver.getCurrentUrl().endsWith("dashboard/index"));
    }

    @И("^Пользователь раскрывает меню Users")
    public void openUserMenu() {
        mainPage = new MainPage(driver);
        mainPage.pushMenuUser();
    }

    @Когда("^Перейти в раздел Players")
    public void pushPlayersMenu() {
        mainPage = new MainPage(driver);
        mainPage.pushPlayersMenu();
    }

    @Тогда("Таблица с игроками загрузилась")
    public void checkTableIsLoad() {
        playerPage = new PlayerPage(driver);
        assertTrue(
                playerPage.tableIsLoad()
        );
    }

    @Когда ("Выбрать столбец {string} и нажать на заголовок")
    public void pushHeaderOfTable(){
        playerPage = new PlayerPage(driver);
        playerPage.pushUsernameHeader();

    }

    @Тогда ("Таблица с игроками отсортирована по столбцу {string}")
    public void checkTableIsSorted(){
        playerPage = new PlayerPage(driver);
        List<String> userList = playerPage.userNameList();
        List<String> tmp = new ArrayList<>(userList);
        Collections.sort(tmp);
        assertEquals(tmp, userList);
    }


}
