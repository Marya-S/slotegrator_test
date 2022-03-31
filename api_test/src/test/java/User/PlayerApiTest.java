package User;

import dto.GetToken;
import dto.RegisterPlayer;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class PlayerApiTest extends UserBaseTest {
    GetToken getBearerToken;
    RegisterPlayer registerPlayer;
    private String login;
    private String password;
    private int id;

    private void loginAndPasswordGenerate(){
        Random rndIndexGen = new Random(System.currentTimeMillis());
        login = "janedoe" + rndIndexGen;
        password = "amFuZWRvZTEyMw==";
    }

    @Test
    public void registerPlayerTest(){
        loginAndPasswordGenerate();
        getBearerToken = GetToken.builder()
                .scope("password")
                .username("test91")
                .password("oiwSMUmQo9uuuyc9KITwH")
                .build();
        String token=  authorizationApi.getToken(getBearerToken)
                .then()
                .statusCode(200)
                .body("access_token", not(emptyOrNullString()))
                .extract().path("access_token");
        registerPlayer = RegisterPlayer.builder()
                .username(login)
                .password_change(password)
                .password_repeat(password)
                .email("janedoe@example.com")
                .name("Jane")
                .surname("Doe")
                .build();
        id = playerApi.registerPlayer(registerPlayer, token)
                .then()
                .statusCode(201)
                .body(matchesJsonSchemaInClasspath("playerResponse.json"))
                .extract().path("id");
    }

    @Test
    public void authorizeAndCheckCredential(){
        getBearerToken = GetToken.builder()
                .scope("password")
                .username(login)
                .password(password)
                .build();
        String token=  authorizationApi.getToken(getBearerToken)
                .then()
                .statusCode(200)
                .body("access_token", not(emptyOrNullString()))
                .extract().path("access_token");
        playerApi.getUser(id,token)
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("idPlayerResponse.json"));

    }

    @Test
    public void authorizeAndCheckWrongCredential(){
        getBearerToken = GetToken.builder()
                .scope("password")
                .username(login)
                .password(password)
                .build();
        String token=  authorizationApi.getToken(getBearerToken)
                .then()
                .statusCode(200)
                .body("access_token", not(emptyOrNullString()))
                .extract().path("access_token");
        id++;
        playerApi.getUser(id,token)
                .then()
                .statusCode(404);
    }
}
