package tests.user;

import base.BaseApiTest;
import dataFactory.UserDF;
import endpoints.UserEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginUserTests extends BaseApiTest {

    @Test(groups = {"smoke", "regression", "user", "positive"})
    public void verifyThatLoginShouldReturn200WhenValidCredentials() {
        String username = UserDF.getValidUsername();
        String password = UserDF.getValidPassword();
        Response response = apiClient.getWithQueryParams(UserEndpoints.LOGIN_USER, "username", username, "password", password);
        int statusCode = response.getStatusCode();
        String loginMessage = response.getBody().asString();
        
        Assert.assertEquals(statusCode, 200);
        
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(loginMessage.contains("logged in"));
        softAssert.assertAll();
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatLoginShouldReturn400WhenInvalidCredentials() {
        String username = UserDF.getInvalidUsername();
        String password = "wrongpassword";
        Response response = apiClient.getWithQueryParams(UserEndpoints.LOGIN_USER, "username", username, "password", password);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 400);
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatLoginShouldReturn400WhenMissingUsername() {
        String username = "";
        String password = UserDF.getValidPassword();
        Response response = apiClient.getWithQueryParams(UserEndpoints.LOGIN_USER, "username", username, "password", password);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 400);
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatLoginShouldReturn400WhenMissingPassword() {
        String username = UserDF.getValidUsername();
        String password = "";
        Response response = apiClient.getWithQueryParams(UserEndpoints.LOGIN_USER, "username", username, "password", password);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 400);
    }
}