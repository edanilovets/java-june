import com.example.model.UserPayload;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.blankString;

class UserApiTests {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "http://127.0.0.1/";
    }

    @Test
    void testCanRegisterUser() {

        UserPayload userPayload = new UserPayload();
        userPayload.setUsername(RandomStringUtils.randomAlphanumeric(6));
        userPayload.setEmail("user@gmail.com");
        userPayload.setPassword("12345");


        RestAssured
                .given().contentType(ContentType.JSON).log().all()
                .body(userPayload)
                .when()
                .post("register")
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .body("id", not(blankString()));
    }

}
