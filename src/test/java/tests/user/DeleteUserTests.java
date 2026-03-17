package tests.user;

import base.BaseApiTest;
import dataFactory.UserDF;
import endpoints.UserEndpoints;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import validators.ResponseValidator;

public class DeleteUserTests extends BaseApiTest {

    @Test(groups = {"smoke", "regression", "user", "positive"})
    public void verifyThatDeleteUserShouldReturn200WhenUserExists() {
        String username = UserDF.getValidUsername();
        Response response = apiClient.deleteWithPathParam(UserEndpoints.DELETE_USER, "username", username);
        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateJsonFieldEquals(response, "code", 200);
        ResponseValidator.validateFieldExists(response, "message");
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatDeleteUserShouldReturn404WhenUserNotFound() {
        String username = UserDF.getNonexistentUsername();
        Response response = apiClient.deleteWithPathParam(UserEndpoints.DELETE_USER, "username", username);
        ResponseValidator.validateStatusCode(response, 404);
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatDeleteUserShouldReturnResponseWithinTimeLimit() {
        String username = UserDF.getValidUsername();
        Response response = apiClient.deleteWithPathParam(UserEndpoints.DELETE_USER, "username", username);
        ResponseValidator.validateResponseTime(response, 3000);
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatDeleteUserShouldReturnProperContentType() {
        String username = UserDF.getValidUsername();
        Response response = apiClient.deleteWithPathParam(UserEndpoints.DELETE_USER, "username", username);
        ResponseValidator.validateContentType(response, "application/json");
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatDeleteUserWithInvalidUsernameShouldReturn404() {
        String username = UserDF.getInvalidUsername();
        Response response = apiClient.deleteWithPathParam(UserEndpoints.DELETE_USER, "username", username);
        ResponseValidator.validateStatusCode(response, 404);
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatDeleteUserShouldReturnSuccessMessage() {
        String username = UserDF.getValidUsername();
        Response response = apiClient.deleteWithPathParam(UserEndpoints.DELETE_USER, "username", username);
        ResponseValidator.validateJsonFieldEquals(response, "message", username);
    }
}
