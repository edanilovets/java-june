import com.example.model.UserPayload;
import com.socks.tests.user.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;

@Disabled
class RestAssuredMyExampleTests extends BaseTest {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "http://localhost:80";

    }

    @Test
    void testRegister() {
        UserPayload userPayload = new UserPayload()
                .setUsername("")
                .setEmail(faker.internet().emailAddress())
                .setPassword(faker.internet().password());

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RestAssured
                .given().contentType(ContentType.JSON).log().all()
                .body(userPayload)
                .when()
                .post("register")
                .then()
                .statusCode(500)
                .statusLine("HTTP/1.1 500 Internal Server Error")
                .contentType(ContentType.TEXT)
                .header("Content-Length", "0")
                .header("Content-Type", "text/plain; charset=utf-8")
                .headers("Content-Length", "0",
                        "Content-Type", "text/plain; charset=utf-8")
                .cookie("_TRAEFIK_BACKEND", "http://front-end:8079")
                .cookie("md.sid", not(emptyString()))
                .time(lessThan(200L))
                .body(is(emptyString()));

    }
}
