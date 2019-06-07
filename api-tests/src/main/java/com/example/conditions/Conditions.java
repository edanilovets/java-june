package com.example.conditions;

import io.restassured.http.ContentType;
import lombok.experimental.UtilityClass;
import org.hamcrest.Matcher;

@UtilityClass
public class Conditions {

    public static StatusCodeCondition statusCode(int code) {
        return new StatusCodeCondition(code);
    }

    public static BodyFieldCondition bodyField(String path, Matcher matcher) {
        return new BodyFieldCondition(path, matcher);
    }

    public static BodyFieldCondition bodyField(Matcher matcher) {
        return new BodyFieldCondition(null, matcher);
    }

    public static ContentTypeCondition contentType(ContentType contentType) {
        return new ContentTypeCondition(contentType);
    }
}
