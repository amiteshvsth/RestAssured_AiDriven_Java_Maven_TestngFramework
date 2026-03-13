package tests.user;

import base.baseApiTest;
import dataFactory.user.UserDF;
import dto.request.user.CreateUserRequest;
import endpoints.userEndpoints;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import validators.responseValidator;

public class CreateUserTests extends baseApiTest {

    @Test(groups = {"smoke", "regression", "user", "positive"})
    public void verifyThatCreateUserShouldReturn200WhenValidPayload() {
        CreateUserRequest request = UserDF.createValidCreateUserRequest();
        Response response = apiClient.post(userEndpoints.CREATE_USER, request);
        responseValidator.validateStatusCode(response, 200);
        responseValidator.validateJsonFieldEquals(response, "code", 200);
        responseValidator.validateFieldExists(response, "message");
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatCreateUserShouldReturn200WhenMinimalDataProvided() {
        CreateUserRequest request = UserDF.createCreateUserRequestWithMinimalData();
        Response response = apiClient.post(userEndpoints.CREATE_USER, request);
        responseValidator.validateStatusCode(response, 200);
        responseValidator.validateJsonFieldEquals(response, "code", 200);
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatCreateUserShouldReturnResponseWithinTimeLimit() {
        CreateUserRequest request = UserDF.createValidCreateUserRequest();
        Response response = apiClient.post(userEndpoints.CREATE_USER, request);
        responseValidator.validateResponseTime(response, 3000);
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatCreateUserShouldReturnProperContentType() {
        CreateUserRequest request = UserDF.createValidCreateUserRequest();
        Response response = apiClient.post(userEndpoints.CREATE_USER, request);
        responseValidator.validateContentType(response, "application/json");
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatCreateUserWithInvalidEmailShouldReturn200() {
        CreateUserRequest request = UserDF.createCreateUserRequestWithInvalidEmail();
        Response response = apiClient.post(userEndpoints.CREATE_USER, request);
        responseValidator.validateStatusCode(response, 200);
    }

    @Test(groups = {"regression", "user", "edge"})
    public void verifyThatCreateUserWithEmptyPayloadShouldReturn200() {
        CreateUserRequest request = UserDF.createCreateUserRequestWithEmptyPayload();
        Response response = apiClient.post(userEndpoints.CREATE_USER, request);
        responseValidator.validateStatusCode(response, 200);
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatCreateUsersWithArrayShouldReturn200() {
        Response response = apiClient.post(userEndpoints.CREATE_USER_WITH_ARRAY, 
                UserDF.createValidCreateUsersWithArrayRequest());
        responseValidator.validateStatusCode(response, 200);
        responseValidator.validateJsonFieldEquals(response, "code", 200);
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatCreateUsersWithListShouldReturn200() {
        Response response = apiClient.post(userEndpoints.CREATE_USER_WITH_LIST, 
                UserDF.createValidCreateUsersWithListRequest());
        responseValidator.validateStatusCode(response, 200);
        responseValidator.validateJsonFieldEquals(response, "code", 200);
    }
}
