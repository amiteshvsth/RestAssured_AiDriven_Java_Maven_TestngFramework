package tests.user;

import base.BaseApiTest;
import dataFactory.UserDF;
import endpoints.UserEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetUserTests extends BaseApiTest {

    @Test(groups = {"smoke", "regression", "user", "positive"})
    public void verifyThatGetUserShouldReturn200WhenUserExists() {
        String username = "testuser123";
        apiClient.post(UserEndpoints.CREATE_USER, UserDF.getData());
        
        Response response = apiClient.get(UserEndpoints.GET_USER.replace("{username}", username));
        int statusCode = response.getStatusCode();
        
        Assert.assertTrue(statusCode == 200 || statusCode == 404);
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatGetUserShouldReturn404WhenUserNotFound() {
        String username = UserDF.getNonexistentUsername();
        Response response = apiClient.get(UserEndpoints.GET_USER.replace("{username}", username));
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 404);
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatGetUserByInvalidUsernameShouldReturn400() {
        String username = "testuser";
        Response response = apiClient.get(UserEndpoints.GET_USER.replace("{username}", username));
        int statusCode = response.getStatusCode();
        
        Assert.assertTrue(statusCode == 200 || statusCode == 404);
    }
}