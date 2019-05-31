import com.example.model.Customer;
import com.example.model.UserPayload;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.Matchers.blankString;
import static org.hamcrest.Matchers.not;

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

    @Test
    @Ignore
    void testCanReturnAllCustomersAsJSON() {
        RestAssured
                .given().contentType(ContentType.JSON).log().all()
                .when()
                .get("/customers")
                .then().log().all()
                .contentType(ContentType.JSON);
    }

    @Test
    void testCanReturnAllCustomers() {

        // Arrange
        //  1) If there are customers - drop all customers
        //  2) Create new customers in db
        // Act
        //  1) Get all customers to list
        // Assert
        //  1) Assert each customer by fields in list
        //  2) Track test execution time

        String allCustomers = RestAssured
                .given().contentType(ContentType.JSON).log().all()
                .get("/customers").asString();
        // And get all books with price < 10 from the response. "from" is statically imported from the JsonPath class
        List<Customer> customers = from(allCustomers).getList("_embedded.customer", Customer.class);
        System.out.println("All: " + customers.size());
        for(Customer customer: customers){
            System.out.println("----------");
            System.out.println(customer.getFirstName());
            System.out.println(customer.getLastName());
            System.out.println(customer.getUsername());
            System.out.println(customer.getId());
            System.out.println(customer.getLinks());

        }
    }
}
