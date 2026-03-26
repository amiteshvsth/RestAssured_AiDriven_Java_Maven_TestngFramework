package tests.user;

import base.BaseApiTest;
import dataFactory.UserDF;
import endpoints.UserEndpoints;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import validators.ResponseValidator;

import static org.hamcrest.Matchers.containsString;

public class GetUserTests extends BaseApiTest {

    @Test(groups = {"smoke", "regression", "user", "positive"})
    public void verifyThatGetUserShouldReturn200WhenUserExists() {
        String username = UserDF.getValidUsername();
        Response response = apiClient.getWithPathParam(UserEndpoints.GET_USER, "username", username);
        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateJsonFieldEquals(response, "username", username);
        ResponseValidator.validateFieldExists(response, "id");
        ResponseValidator.validateFieldExists(response, "email");
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatGetUserShouldReturn404WhenUserNotFound() {
        String username = UserDF.getNonexistentUsername();
        Response response = apiClient.getWithPathParam(UserEndpoints.GET_USER, "username", username);
        ResponseValidator.validateStatusCode(response, 404);
        ResponseValidator.validateJsonFieldEquals(response, "code", 404);
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatGetUserShouldReturnResponseWithinTimeLimit() {
        String username = UserDF.getValidUsername();
        Response response = apiClient.getWithPathParam(UserEndpoints.GET_USER, "username", username);
        ResponseValidator.validateResponseTime(response, 3000);
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatGetUserShouldReturnProperContentType() {
        String username = UserDF.getValidUsername();
        Response response = apiClient.getWithPathParam(UserEndpoints.GET_USER, "username", username);
        ResponseValidator.validateContentType(response, "application/json");
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatGetUserShouldReturnAllRequiredFields() {
        String username = UserDF.getValidUsername();
        Response response = apiClient.getWithPathParam(UserEndpoints.GET_USER, "username", username);
        ResponseValidator.validateFieldExists(response, "id");
        ResponseValidator.validateFieldExists(response, "username");
        ResponseValidator.validateFieldExists(response, "firstName");
        ResponseValidator.validateFieldExists(response, "lastName");
        ResponseValidator.validateFieldExists(response, "email");
        ResponseValidator.validateFieldExists(response, "password");
        ResponseValidator.validateFieldExists(response, "phone");
        ResponseValidator.validateFieldExists(response, "userStatus");
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatGetUserByInvalidUsernameShouldReturn404() {
        String username = UserDF.getInvalidUsername();
        Response response = apiClient.getWithPathParam(UserEndpoints.GET_USER, "username", username);
        ResponseValidator.validateStatusCode(response, 404);
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatGetUserShouldReturnCorrectEmailFormat() {
        String username = UserDF.getValidUsername();
        Response response = apiClient.getWithPathParam(UserEndpoints.GET_USER, "username", username);
        ResponseValidator.validateJsonField(response, "email", containsString("@"));
    }
}
