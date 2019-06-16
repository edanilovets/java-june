package com.socks.tests.user;

import com.example.ProjectConfig;
import com.example.services.UserApiServices;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;

import java.util.Locale;


public class BaseTest {

    public final UserApiServices userApiServices = new UserApiServices();
    public final Faker faker = new Faker(new Locale("en"));

    @BeforeAll
    static void setUp() {

        //Java Owner library
        RestAssured.baseURI = ConfigFactory.create(ProjectConfig.class).apiPath();
    }
}
