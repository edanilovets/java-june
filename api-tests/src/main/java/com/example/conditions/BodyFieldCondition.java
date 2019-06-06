package com.example.conditions;

import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matcher;

@RequiredArgsConstructor
public class BodyFieldCondition implements Condition {

    private final String path;
    private final Matcher matcher;

    @Override
    public void check(Response response) {
        if (StringUtils.isBlank(path)) {
            response.then().body(matcher);
        } else {
            response.then().body(path, matcher);
        }

    }

    @Override
    public String toString() {
        if (StringUtils.isBlank(path)) {
            return "Body Field: " + matcher;
        } else {
            return "Body Field: " + path + " - " + matcher;
        }

    }
}
