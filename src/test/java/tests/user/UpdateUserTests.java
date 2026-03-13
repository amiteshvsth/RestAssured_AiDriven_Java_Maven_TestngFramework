package tests.user;

import base.baseApiTest;
import dataFactory.user.UserDF;
import dto.request.user.UpdateUserRequest;
import endpoints.userEndpoints;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import validators.responseValidator;

public class UpdateUserTests extends baseApiTest {

    @Test(groups = {"smoke", "regression", "user", "positive"})
    public void verifyThatUpdateUserShouldReturn200WhenValidPayload() {
        String username = UserDF.getValidUsername();
        UpdateUserRequest request = UserDF.createValidUpdateUserRequest();
        Response response = apiClient.putWithPathParam(userEndpoints.UPDATE_USER, request, "username", username);
        responseValidator.validateStatusCode(response, 200);
        responseValidator.validateJsonFieldEquals(response, "code", 200);
        responseValidator.validateFieldExists(response, "message");
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatUpdateUserShouldReturn200ForNonexistentUser() {
        String username = UserDF.getNonexistentUserForUpdate();
        UpdateUserRequest request = UserDF.createValidUpdateUserRequest();
        Response response = apiClient.putWithPathParam(userEndpoints.UPDATE_USER, request, "username", username);
        responseValidator.validateStatusCode(response, 200);
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatUpdateUserShouldReturnResponseWithinTimeLimit() {
        String username = UserDF.getValidUsername();
        UpdateUserRequest request = UserDF.createValidUpdateUserRequest();
        Response response = apiClient.putWithPathParam(userEndpoints.UPDATE_USER, request, "username", username);
        responseValidator.validateResponseTime(response, 3000);
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatUpdateUserShouldReturnProperContentType() {
        String username = UserDF.getValidUsername();
        UpdateUserRequest request = UserDF.createValidUpdateUserRequest();
        Response response = apiClient.putWithPathParam(userEndpoints.UPDATE_USER, request, "username", username);
        responseValidator.validateContentType(response, "application/json");
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatUpdateUserWithInvalidEmailShouldReturn200() {
        String username = UserDF.getValidUsername();
        UpdateUserRequest request = UserDF.createUpdateUserRequestWithInvalidEmail();
        Response response = apiClient.putWithPathParam(userEndpoints.UPDATE_USER, request, "username", username);
        responseValidator.validateStatusCode(response, 200);
    }

    @Test(groups = {"regression", "user", "edge"})
    public void verifyThatUpdateUserWithEmptyPayloadShouldReturn200() {
        String username = UserDF.getValidUsername();
        UpdateUserRequest request = UserDF.createUpdateUserRequestWithEmptyPayload();
        Response response = apiClient.putWithPathParam(userEndpoints.UPDATE_USER, request, "username", username);
        responseValidator.validateStatusCode(response, 200);
    }
}
