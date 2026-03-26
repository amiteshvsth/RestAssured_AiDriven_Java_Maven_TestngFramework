package tests.user;

import base.BaseApiTest;
import dataFactory.UserDF;
import endpoints.UserEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginUserTests extends BaseApiTest {

    @Test(groups = {"smoke", "regression", "user", "positive"})
    public void verifyThatLoginShouldReturn200WhenValidCredentials() {
        String username = UserDF.getValidUsername();
        String password = UserDF.getValidPassword();
        Response response = apiClient.getWithQueryParams(UserEndpoints.LOGIN_USER, "username", username, "password", password);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatLoginShouldReturn200WhenInvalidCredentials() {
        String username = UserDF.getInvalidUsername();
        String password = "wrongpassword";
        Response response = apiClient.getWithQueryParams(UserEndpoints.LOGIN_USER, "username", username, "password", password);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatLoginShouldReturn200WhenUserNotFound() {
        String username = UserDF.getNonexistentUsername();
        String password = "password";
        Response response = apiClient.getWithQueryParams(UserEndpoints.LOGIN_USER, "username", username, "password", password);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatLoginShouldReturnResponseWithinTimeLimit() {
        String username = UserDF.getValidUsername();
        String password = UserDF.getValidPassword();
        Response response = apiClient.getWithQueryParams(UserEndpoints.LOGIN_USER, "username", username, "password", password);
        Assert.assertTrue(response.getTime() < 3000);
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatLoginShouldReturnProperContentType() {
        String username = UserDF.getValidUsername();
        String password = UserDF.getValidPassword();
        Response response = apiClient.getWithQueryParams(UserEndpoints.LOGIN_USER, "username", username, "password", password);
        Assert.assertTrue(response.getContentType().contains("application/json"));
    }

    @Test(groups = {"regression", "user", "edge"})
    public void verifyThatLoginShouldReturn200WithEmptyPassword() {
        String username = UserDF.getValidUsername();
        String password = "";
        Response response = apiClient.getWithQueryParams(UserEndpoints.LOGIN_USER, "username", username, "password", password);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(groups = {"regression", "user", "edge"})
    public void verifyThatLoginShouldReturn200WithEmptyUsername() {
        String username = "";
        String password = UserDF.getValidPassword();
        Response response = apiClient.getWithQueryParams(UserEndpoints.LOGIN_USER, "username", username, "password", password);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}