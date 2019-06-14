package com.example.conditions;

import io.restassured.http.ContentType;
import lombok.experimental.UtilityClass;
import org.hamcrest.Matcher;

@UtilityClass
public class Conditions {
    /***
     * Need to implement such Rest Assured conditions:
     * statusCode
     * body
     * contentType
     * header
     * headers
     * cookie
     * cookies
     * statusLine
     * time
     *
     * */
    public static StatusCodeCondition statusCode(int code) {
        return new StatusCodeCondition(code);
    }

    public static BodyFieldCondition body(String path, Matcher matcher) {
        return new BodyFieldCondition(path, matcher);
    }

    public static BodyFieldCondition body(Matcher matcher) {
        return new BodyFieldCondition(null, matcher);
    }

    public static ContentTypeCondition contentType(ContentType contentType) {
        return new ContentTypeCondition(contentType);
    }
    public static HeaderTypeCondition header(String headerName, String expectedValue) {
        return new HeaderTypeCondition(headerName, expectedValue);
    }
}
