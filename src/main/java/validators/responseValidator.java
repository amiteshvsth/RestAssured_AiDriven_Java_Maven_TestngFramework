package validators;

import io.restassured.response.Response;
import org.hamcrest.Matcher;

import static org.hamcrest.Matchers.*;

public class ResponseValidator {
    public static void validateStatusCode(Response response, int expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);
    }

    public static void validateResponseTime(Response response, long maxMilliseconds) {
        response.then().time(lessThan(maxMilliseconds));
    }

    public static void validateJsonField(Response response, String jsonPath, Matcher<?> matcher) {
        response.then().body(jsonPath, matcher);
    }

    public static void validateJsonFieldEquals(Response response, String jsonPath, Object expectedValue) {
        response.then().body(jsonPath, equalTo(expectedValue));
    }

    public static void validateHeader(Response response, String headerName, String expectedValue) {
        response.then().header(headerName, expectedValue);
    }

    public static void validateContentType(Response response, String expectedContentType) {
        response.then().contentType(containsString(expectedContentType));
    }

    public static void validateFieldExists(Response response, String jsonPath) {
        response.then().body(jsonPath, notNullValue());
    }

    public static void validateArraySize(Response response, String jsonPath, int expectedSize) {
        response.then().body(jsonPath + ".size()", equalTo(expectedSize));
    }

    public static void validateFieldContains(Response response, String jsonPath, String expectedSubstring) {
        response.then().body(jsonPath, containsString(expectedSubstring));
    }

    public static void validateBooleanField(Response response, String jsonPath, boolean expectedValue) {
        response.then().body(jsonPath, equalTo(expectedValue));
    }

    public static void validateFieldNotNull(Response response, String jsonPath) {
        response.then().body(jsonPath, notNullValue());
    }

    public static void validateFieldEquals(Response response, String jsonPath, Object expectedValue) {
        response.then().body(jsonPath, equalTo(expectedValue));
    }

    public static void validateArrayNotEmpty(Response response, String jsonPath) {
        response.then().body(jsonPath, not(hasSize(0)));
    }
}
