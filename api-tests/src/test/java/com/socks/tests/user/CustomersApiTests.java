package com.socks.tests.user;

import com.example.responses.UserListResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomersApiTests extends BaseTest{
    @Test
    @DisplayName("Can Return all customers")
    void testCanReturnAllCustomers() {
        UserListResponse users = userApiServices.getAllCustomers()
                .asPojoFromString(UserListResponse.class);

        assertThat(users.getEmbedded().getCustomers().size()).isGreaterThan(15);
        assertThat(users.getEmbedded().getCustomers().get(0).getFirstName()).isEqualTo("Eve");
    }

    @Test
    @Disabled
    void testReturnAllCustomersContainsAllRequiredFields() {
        String customersResponse = userApiServices.getAllCustomers()
                .toString();

        // code here...

    }
}
