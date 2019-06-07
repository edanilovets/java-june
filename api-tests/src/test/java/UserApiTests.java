import com.example.ProjectConfig;
import com.example.model.UserPayload;
import com.example.responses.UserListResponse;
import com.example.services.UserApiServices;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.example.conditions.Conditions.bodyField;
import static com.example.conditions.Conditions.statusCode;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

class UserApiTests extends BaseTest{

    private final UserApiServices userApiServices = new UserApiServices();

    @BeforeAll
    static void setUp() {

        //more info http://owner.aeonbits.org/docs
        RestAssured.baseURI = ConfigFactory.create(ProjectConfig.class).apiPath();
    }

    @Test
    @DisplayName("Can Register User WIth Valid Credentials")
    void testCanRegisterUserWithValidCredentials() {

        UserPayload userPayload = new UserPayload()
            .setUsername(faker.name().firstName() + " " + faker.name().lastName())
            .setEmail(faker.internet().emailAddress())
            .setPassword(faker.internet().password());


        String id = userApiServices.registerUser(userPayload)
                .shouldHave(statusCode(200))
                .shouldHave(bodyField("id", not(blankString())))
                .shouldHave(bodyField(containsString("id")))
                .getValue("id");

        UserListResponse users = userApiServices.getAllCustomers().asPojoFromString(UserListResponse.class);

        assertThat(users.getEmbedded().getCustomer().size()).isGreaterThan(15);
        assertThat(users.getEmbedded().getCustomer().get(0).getFirstName()).isEqualTo("Eve");

        //Using Assertj for generation of assertions
        // code...

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
