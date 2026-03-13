package tests.user;

import base.baseApiTest;
import dataFactory.user.UserDF;
import endpoints.userEndpoints;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import validators.responseValidator;

import static org.hamcrest.Matchers.*;

public class GetUserTests extends baseApiTest {

    @Test(groups = {"smoke", "regression", "user", "positive"})
    public void verifyThatGetUserShouldReturn200WhenUserExists() {
        String username = UserDF.getValidUsername();
        Response response = apiClient.getWithPathParam(userEndpoints.GET_USER, "username", username);
        responseValidator.validateStatusCode(response, 200);
        responseValidator.validateJsonFieldEquals(response, "username", username);
        responseValidator.validateFieldExists(response, "id");
        responseValidator.validateFieldExists(response, "email");
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatGetUserShouldReturn404WhenUserNotFound() {
        String username = UserDF.getNonexistentUsername();
        Response response = apiClient.getWithPathParam(userEndpoints.GET_USER, "username", username);
        responseValidator.validateStatusCode(response, 404);
        responseValidator.validateJsonFieldEquals(response, "code", 404);
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatGetUserShouldReturnResponseWithinTimeLimit() {
        String username = UserDF.getValidUsername();
        Response response = apiClient.getWithPathParam(userEndpoints.GET_USER, "username", username);
        responseValidator.validateResponseTime(response, 3000);
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatGetUserShouldReturnProperContentType() {
        String username = UserDF.getValidUsername();
        Response response = apiClient.getWithPathParam(userEndpoints.GET_USER, "username", username);
        responseValidator.validateContentType(response, "application/json");
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatGetUserShouldReturnAllRequiredFields() {
        String username = UserDF.getValidUsername();
        Response response = apiClient.getWithPathParam(userEndpoints.GET_USER, "username", username);
        responseValidator.validateFieldExists(response, "id");
        responseValidator.validateFieldExists(response, "username");
        responseValidator.validateFieldExists(response, "firstName");
        responseValidator.validateFieldExists(response, "lastName");
        responseValidator.validateFieldExists(response, "email");
        responseValidator.validateFieldExists(response, "password");
        responseValidator.validateFieldExists(response, "phone");
        responseValidator.validateFieldExists(response, "userStatus");
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatGetUserByInvalidUsernameShouldReturn404() {
        String username = UserDF.getInvalidUsername();
        Response response = apiClient.getWithPathParam(userEndpoints.GET_USER, "username", username);
        responseValidator.validateStatusCode(response, 404);
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatGetUserShouldReturnCorrectEmailFormat() {
        String username = UserDF.getValidUsername();
        Response response = apiClient.getWithPathParam(userEndpoints.GET_USER, "username", username);
        responseValidator.validateJsonField(response, "email", containsString("@"));
    }
}
