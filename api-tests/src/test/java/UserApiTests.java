import com.example.assertions.AssertableResponse;
import com.example.model.UserPayload;
import com.example.responses.UserListResponse;
import com.example.services.UserApiServices;
import io.restassured.RestAssured;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.example.conditions.Conditions.bodyField;
import static com.example.conditions.Conditions.statusCode;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

class UserApiTests {

    private final UserApiServices userApiServices = new UserApiServices();

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "http://localhost:80/";
    }

    @Test
    @DisplayName("Can Register User WIth Valid Credentials")
    void testCanRegisterUserWithValidCredentials() {

        UserPayload userPayload = new UserPayload()
            .setUsername(RandomStringUtils.randomAlphanumeric(6))
            .setEmail("user@gmail.com")
            .setPassword("12345");


        String id = userApiServices.registerUser(userPayload)
                .shouldHave(statusCode(200))
                .shouldHave(bodyField("id", not(blankString())))
                .shouldHave(bodyField(containsString("id")))
                .getValue("id");

        UserListResponse users = userApiServices.getAllCustomers().asPojoFromString(UserListResponse.class);

        assertThat(users.getEmbedded().getCustomer().size()).isGreaterThan(15);

    }

    @Test
    @DisplayName("Can Not Register User With Invalid Credentials")
    void testCanNotRegisterUserWithInvalidCredentials() {
        UserPayload userPayload = new UserPayload()
                .setUsername(null);

        userApiServices.registerUser(userPayload)
                .shouldHave(statusCode(500))
                .shouldHave(bodyField("id", not(blankString())));
    }
}
