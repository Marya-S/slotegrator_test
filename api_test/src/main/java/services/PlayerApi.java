package services;

import dto.GetToken;
import dto.RegisterPlayer;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class PlayerApi {
    private static final String BASE_URI = "http://test-api.d6.dev.devcaz.com/";
    private static final String PATH = "/v2/players";
    private RequestSpecification spec;

    public PlayerApi(){
        spec = given()
                .baseUri(BASE_URI)
                .basePath(PATH)
                .contentType(ContentType.JSON);
    }
    public Response registerPlayer(RegisterPlayer player, String token) {
        return
                given(spec)
                        .body(player)
                        .header("Authorization","Bearer"+token)
                        .when()
                        .log().all()
                        .post();
    }

    public Response getUser(int id, String token){
        return
                given(spec)
                        .header("Authorization","Bearer"+token)
                        .pathParam("id", id)
                        .log().all()
                        .when().get("/{id}");
    }
}
