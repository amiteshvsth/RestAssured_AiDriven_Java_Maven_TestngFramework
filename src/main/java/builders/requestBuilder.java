package builders;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class requestBuilder {
    private final RequestSpecification requestSpec;
    private static final AllureRestAssured allureFilter = new AllureRestAssured();

    public requestBuilder() {
        requestSpec = given()
                .contentType("application/json")
                .accept("application/json")
                .filter(allureFilter);
    }

    public requestBuilder withHeader(String key, String value) {
        requestSpec.header(key, value);
        return this;
    }

    public requestBuilder withHeaders(Map<String, String> headers) {
        if (headers != null) {
            requestSpec.headers(headers);
        }
        return this;
    }

    public requestBuilder withQueryParam(String key, Object value) {
        requestSpec.queryParam(key, value);
        return this;
    }

    public requestBuilder withQueryParams(Map<String, Object> queryParams) {
        if (queryParams != null) {
            requestSpec.queryParams(queryParams);
        }
        return this;
    }

    public requestBuilder withBody(Object body) {
        requestSpec.body(body);
        return this;
    }

    public requestBuilder withPathParam(String key, Object value) {
        requestSpec.pathParam(key, value);
        return this;
    }

    public RequestSpecification build() {
        return requestSpec;
    }
}