package builders;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class RequestBuilder {
    private final RequestSpecification requestSpec;
    private static final AllureRestAssured allureFilter = new AllureRestAssured();

    public RequestBuilder() {
        requestSpec = given()
                .contentType("application/json")
                .accept("application/json")
                .filter(allureFilter);
    }

    public RequestBuilder withHeader(String key, String value) {
        requestSpec.header(key, value);
        return this;
    }

    public RequestBuilder withHeaders(Map<String, String> headers) {
        if (headers != null) {
            requestSpec.headers(headers);
        }
        return this;
    }

    public RequestBuilder withQueryParam(String key, Object value) {
        requestSpec.queryParam(key, value);
        return this;
    }

    public RequestBuilder withQueryParams(Map<String, Object> queryParams) {
        if (queryParams != null) {
            requestSpec.queryParams(queryParams);
        }
        return this;
    }

    public RequestBuilder withBody(Object body) {
        requestSpec.body(body);
        return this;
    }

    public RequestBuilder withPathParam(String key, Object value) {
        requestSpec.pathParam(key, value);
        return this;
    }

    public RequestSpecification build() {
        return requestSpec;
    }
}