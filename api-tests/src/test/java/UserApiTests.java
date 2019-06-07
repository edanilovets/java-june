import com.example.ProjectConfig;
import com.example.model.UserPayload;
import com.example.responses.UserListResponse;
import com.example.services.UserApiServices;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.example.conditions.Conditions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

class UserApiTests extends BaseTest{

    private final UserApiServices userApiServices = new UserApiServices();

    @BeforeAll
    static void setUp() {

        //Java Owner library
        RestAssured.baseURI = ConfigFactory.create(ProjectConfig.class).apiPath();
    }

    @Test
    @DisplayName("Can Register User With Valid Credentials")
    void testCanRegisterUserWithValidCredentials() {

        UserPayload userPayload = new UserPayload()
            .setUsername(faker.name().firstName() + " " + faker.name().lastName())
            .setEmail(faker.internet().emailAddress())
            .setPassword(faker.internet().password());

        String id = userApiServices.registerUser(userPayload)
                .shouldHave(statusCode(200))
                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyField(containsString("id")))
                .shouldHave(bodyField("id", not(blankString())))
                .getValue("id");

        assertThat(id.length()).isEqualTo(24);

    }

    @Test
    @DisplayName("Can Not Register User With Invalid Credentials")
    void testCanNotRegisterUserWithInvalidCredentials() {
        UserPayload userPayload = new UserPayload()
                .setUsername(null);

        userApiServices.registerUser(userPayload)
                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(statusCode(500));
    }

    @Test
    @DisplayName("Can Return all customers")
    void testCanReturnAllCustomers() {
        UserListResponse users =userApiServices.getAllCustomers()
                .asPojoFromString(UserListResponse.class);

        assertThat(users.getEmbedded().getCustomer().size()).isGreaterThan(15);
        assertThat(users.getEmbedded().getCustomer().get(0).getFirstName()).isEqualTo("Eve");
    }
}
