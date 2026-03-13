package tests.user;

import base.baseApiTest;
import dataFactory.user.UserDF;
import endpoints.userEndpoints;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import validators.responseValidator;

public class DeleteUserTests extends baseApiTest {

    @Test(groups = {"smoke", "regression", "user", "positive"})
    public void verifyThatDeleteUserShouldReturn200WhenUserExists() {
        String username = UserDF.getValidUsername();
        Response response = apiClient.deleteWithPathParam(userEndpoints.DELETE_USER, "username", username);
        responseValidator.validateStatusCode(response, 200);
        responseValidator.validateJsonFieldEquals(response, "code", 200);
        responseValidator.validateFieldExists(response, "message");
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatDeleteUserShouldReturn404WhenUserNotFound() {
        String username = UserDF.getNonexistentUsername();
        Response response = apiClient.deleteWithPathParam(userEndpoints.DELETE_USER, "username", username);
        responseValidator.validateStatusCode(response, 404);
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatDeleteUserShouldReturnResponseWithinTimeLimit() {
        String username = UserDF.getValidUsername();
        Response response = apiClient.deleteWithPathParam(userEndpoints.DELETE_USER, "username", username);
        responseValidator.validateResponseTime(response, 3000);
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatDeleteUserShouldReturnProperContentType() {
        String username = UserDF.getValidUsername();
        Response response = apiClient.deleteWithPathParam(userEndpoints.DELETE_USER, "username", username);
        responseValidator.validateContentType(response, "application/json");
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatDeleteUserWithInvalidUsernameShouldReturn404() {
        String username = UserDF.getInvalidUsername();
        Response response = apiClient.deleteWithPathParam(userEndpoints.DELETE_USER, "username", username);
        responseValidator.validateStatusCode(response, 404);
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatDeleteUserShouldReturnSuccessMessage() {
        String username = UserDF.getValidUsername();
        Response response = apiClient.deleteWithPathParam(userEndpoints.DELETE_USER, "username", username);
        responseValidator.validateJsonFieldEquals(response, "message", username);
    }
}
