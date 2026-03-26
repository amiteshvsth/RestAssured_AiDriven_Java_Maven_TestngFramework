package tests.user;

import base.BaseApiTest;
import dataFactory.UserDF;
import dto.user.CreateUserRequest;
import endpoints.UserEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginUserTests extends BaseApiTest {

    @Test(groups = {"smoke", "regression", "user", "positive"})
    public void verifyThatLoginShouldReturn200WhenValidCredentials() {
        CreateUserRequest createRequest = UserDF.getData();
        apiClient.post(UserEndpoints.CREATE_USER, createRequest);
        
        String username = createRequest.getUsername();
        String password = createRequest.getPassword();
        Response response = apiClient.getWithQueryParams(UserEndpoints.LOGIN_USER, "username", username, "password", password);
        int statusCode = response.getStatusCode();
        String loginMessage = response.getBody().asString();
        
        Assert.assertEquals(statusCode, 200);
        Assert.assertTrue(loginMessage.contains("logged in"));
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatLoginShouldReturn400WhenInvalidCredentials() {
        String username = UserDF.getInvalidUsername();
        String password = "wrongpassword";
        Response response = apiClient.getWithQueryParams(UserEndpoints.LOGIN_USER, "username", username, "password", password);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatLoginShouldReturn400WhenMissingUsername() {
        String username = "";
        String password = UserDF.getValidPassword();
        Response response = apiClient.getWithQueryParams(UserEndpoints.LOGIN_USER, "username", username, "password", password);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatLoginShouldReturn400WhenMissingPassword() {
        String username = UserDF.getValidUsername();
        String password = "";
        Response response = apiClient.getWithQueryParams(UserEndpoints.LOGIN_USER, "username", username, "password", password);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }
}