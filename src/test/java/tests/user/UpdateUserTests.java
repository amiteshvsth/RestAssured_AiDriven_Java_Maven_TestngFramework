package tests.user;

import base.BaseApiTest;
import dataFactory.user.UserDF;
import dto.user.UpdateUserRequest;
import endpoints.UserEndpoints;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import validators.ResponseValidator;

public class UpdateUserTests extends BaseApiTest {

    @Test(groups = {"smoke", "regression", "user", "positive"})
    public void verifyThatUpdateUserShouldReturn200WhenValidPayload() {
        String username = UserDF.getValidUsername();
        UpdateUserRequest request = UserDF.createValidUpdateUserRequest();
        Response response = apiClient.putWithPathParam(UserEndpoints.UPDATE_USER, request, "username", username);
        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateJsonFieldEquals(response, "code", 200);
        ResponseValidator.validateFieldExists(response, "message");
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatUpdateUserShouldReturn200ForNonexistentUser() {
        String username = UserDF.getNonexistentUserForUpdate();
        UpdateUserRequest request = UserDF.createValidUpdateUserRequest();
        Response response = apiClient.putWithPathParam(UserEndpoints.UPDATE_USER, request, "username", username);
        ResponseValidator.validateStatusCode(response, 200);
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatUpdateUserShouldReturnResponseWithinTimeLimit() {
        String username = UserDF.getValidUsername();
        UpdateUserRequest request = UserDF.createValidUpdateUserRequest();
        Response response = apiClient.putWithPathParam(UserEndpoints.UPDATE_USER, request, "username", username);
        ResponseValidator.validateResponseTime(response, 3000);
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatUpdateUserShouldReturnProperContentType() {
        String username = UserDF.getValidUsername();
        UpdateUserRequest request = UserDF.createValidUpdateUserRequest();
        Response response = apiClient.putWithPathParam(UserEndpoints.UPDATE_USER, request, "username", username);
        ResponseValidator.validateContentType(response, "application/json");
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatUpdateUserWithInvalidEmailShouldReturn200() {
        String username = UserDF.getValidUsername();
        UpdateUserRequest request = UserDF.createUpdateUserRequestWithInvalidEmail();
        Response response = apiClient.putWithPathParam(UserEndpoints.UPDATE_USER, request, "username", username);
        ResponseValidator.validateStatusCode(response, 200);
    }

    @Test(groups = {"regression", "user", "edge"})
    public void verifyThatUpdateUserWithEmptyPayloadShouldReturn200() {
        String username = UserDF.getValidUsername();
        UpdateUserRequest request = UserDF.createUpdateUserRequestWithEmptyPayload();
        Response response = apiClient.putWithPathParam(UserEndpoints.UPDATE_USER, request, "username", username);
        ResponseValidator.validateStatusCode(response, 200);
    }
}
