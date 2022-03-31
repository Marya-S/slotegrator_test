package User;

import dto.GetToken;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;


public class GetGuestTokenTest extends UserBaseTest {
    GetToken guestToken;
    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void getGuestTokenUser(){
        guestToken = GetToken.builder()
                .grant_type("client_credentials")
                .scope("guest:default")
                .build();
       authorizationApi.getToken(guestToken)
                .then()
                .statusCode(200)
                .body("access_token", not(emptyOrNullString()));
    }
}
