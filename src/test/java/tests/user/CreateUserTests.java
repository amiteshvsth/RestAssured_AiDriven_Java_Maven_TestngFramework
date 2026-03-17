package tests.user;

import base.BaseApiTest;
import dataFactory.UserDF;
import dto.user.CreateUserRequest;
import endpoints.UserEndpoints;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import validators.ResponseValidator;

public class CreateUserTests extends BaseApiTest {

    @Test(groups = {"smoke", "regression", "user", "positive"})
    public void verifyThatCreateUserShouldReturn200WhenValidPayload() {
        CreateUserRequest request = UserDF.createValidCreateUserRequest();
        Response response = apiClient.post(UserEndpoints.CREATE_USER, request);
        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateJsonFieldEquals(response, "code", 200);
        ResponseValidator.validateFieldExists(response, "message");
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatCreateUserShouldReturn200WhenMinimalDataProvided() {
        CreateUserRequest request = UserDF.createCreateUserRequestWithMinimalData();
        Response response = apiClient.post(UserEndpoints.CREATE_USER, request);
        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateJsonFieldEquals(response, "code", 200);
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatCreateUserShouldReturnResponseWithinTimeLimit() {
        CreateUserRequest request = UserDF.createValidCreateUserRequest();
        Response response = apiClient.post(UserEndpoints.CREATE_USER, request);
        ResponseValidator.validateResponseTime(response, 3000);
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatCreateUserShouldReturnProperContentType() {
        CreateUserRequest request = UserDF.createValidCreateUserRequest();
        Response response = apiClient.post(UserEndpoints.CREATE_USER, request);
        ResponseValidator.validateContentType(response, "application/json");
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatCreateUserWithInvalidEmailShouldReturn200() {
        CreateUserRequest request = UserDF.createCreateUserRequestWithInvalidEmail();
        Response response = apiClient.post(UserEndpoints.CREATE_USER, request);
        ResponseValidator.validateStatusCode(response, 200);
    }

    @Test(groups = {"regression", "user", "edge"})
    public void verifyThatCreateUserWithEmptyPayloadShouldReturn200() {
        CreateUserRequest request = UserDF.createCreateUserRequestWithEmptyPayload();
        Response response = apiClient.post(UserEndpoints.CREATE_USER, request);
        ResponseValidator.validateStatusCode(response, 200);
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatCreateUsersWithArrayShouldReturn200() {
        Response response = apiClient.post(UserEndpoints.CREATE_USER_WITH_ARRAY, 
                UserDF.createValidCreateUsersWithArrayRequest());
        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateJsonFieldEquals(response, "code", 200);
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatCreateUsersWithListShouldReturn200() {
        Response response = apiClient.post(UserEndpoints.CREATE_USER_WITH_LIST, 
                UserDF.createValidCreateUsersWithListRequest());
        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateJsonFieldEquals(response, "code", 200);
    }
}
