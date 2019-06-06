import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@Disabled
class RestAssuredTrainingTests {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "http://localhost:80";

    }
    @Test
    void Example1JSON() throws IOException {
        String id = "57a98d98e4b00679b4a830af";
        RestAssured
                .given().contentType(ContentType.JSON).log().all()
                .get("/customers/57a98d98e4b00679b4a830af")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.TEXT);
        String s = RestAssured
                .given().contentType(ContentType.JSON).log().all()
                .get("/customers/57a98d98e4b00679b4a830af" ).asString();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode customerJSON = mapper.readTree(s);

        System.out.println(customerJSON.toString());

    }
}
