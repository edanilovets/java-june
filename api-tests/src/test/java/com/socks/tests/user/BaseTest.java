package com.socks.tests.user;

import com.example.services.UserApiServices;
import com.github.javafaker.Faker;

import java.util.Locale;


public class BaseTest {
    public final UserApiServices userApiServices = new UserApiServices();
    public final Faker faker = new Faker(new Locale("en"));
}
