package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class ApiHelpers {

    public RequestSpecification requestSpecificationWithJSONHeader() {
        return new RequestSpecBuilder().setBaseUri("https://petstore.swagger.io/v2").addHeader("Content-Type", "application/json").build();
    }

    public RequestSpecification requestSpecificationBaseURI() {
        return new RequestSpecBuilder().setBaseUri("https://petstore.swagger.io/v2").build();
    }

    public RequestSpecification requestSpecificationWithMultiPart() {
        return new RequestSpecBuilder().setBaseUri("https://petstore.swagger.io/v2").
                addHeader("Content-Type", "multipart/form-data").build();
    }

    public RequestSpecification requestSpecificationWithContentTypeEncoded() {
        return new RequestSpecBuilder().setBaseUri("https://petstore.swagger.io/v2").
                addHeader("Content-Type", "application/x-www-form-urlencoded").build();
    }

    public RequestSpecification requestSpecificationWithApiKey() {

        return new RequestSpecBuilder().setBaseUri("https://petstore.swagger.io/v2").
                addHeader("Content-Type", "application/json").addHeader("api_key", "special_key").build();
    }

}
