package services;

import dto.GetToken;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;


public class AuthorizationApi {
    private static final String BASE_URI = "http://test-api.d6.dev.devcaz.com/";
    private static final String PATH = " /v2/oauth2/token";
    private RequestSpecification spec;

    public AuthorizationApi() {
        spec = given()
                .baseUri(BASE_URI)
                .basePath(PATH)
                .contentType(ContentType.JSON);
    }

    public Response getToken(GetToken token) {
        return
                given(spec)
                        .body(token)
                        .auth().preemptive().basic("front_2d6b0a8391742f5d789d7d915755e09e","")
                        .when()
                        .log().all()
                        .post();
    }

}
