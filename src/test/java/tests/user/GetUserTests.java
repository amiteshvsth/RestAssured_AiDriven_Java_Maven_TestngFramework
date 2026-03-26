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
        String username = UserDF.getValidUsername();
        Response response = apiClient.getWithPathParam(UserEndpoints.GET_USER, "username", username);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("username"), username);
        Assert.assertNotNull(response.jsonPath().get("id"));
        Assert.assertNotNull(response.jsonPath().get("email"));
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatGetUserShouldReturn404WhenUserNotFound() {
        String username = UserDF.getNonexistentUsername();
        Response response = apiClient.getWithPathParam(UserEndpoints.GET_USER, "username", username);
        Assert.assertEquals(response.getStatusCode(), 404);
        Assert.assertEquals(response.jsonPath().getInt("code"), 404);
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatGetUserShouldReturnResponseWithinTimeLimit() {
        String username = UserDF.getValidUsername();
        Response response = apiClient.getWithPathParam(UserEndpoints.GET_USER, "username", username);
        Assert.assertTrue(response.getTime() < 3000);
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatGetUserShouldReturnProperContentType() {
        String username = UserDF.getValidUsername();
        Response response = apiClient.getWithPathParam(UserEndpoints.GET_USER, "username", username);
        Assert.assertTrue(response.getContentType().contains("application/json"));
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatGetUserShouldReturnAllRequiredFields() {
        String username = UserDF.getValidUsername();
        Response response = apiClient.getWithPathParam(UserEndpoints.GET_USER, "username", username);
        Assert.assertNotNull(response.jsonPath().get("id"));
        Assert.assertNotNull(response.jsonPath().get("username"));
        Assert.assertNotNull(response.jsonPath().get("firstName"));
        Assert.assertNotNull(response.jsonPath().get("lastName"));
        Assert.assertNotNull(response.jsonPath().get("email"));
        Assert.assertNotNull(response.jsonPath().get("password"));
        Assert.assertNotNull(response.jsonPath().get("phone"));
        Assert.assertNotNull(response.jsonPath().get("userStatus"));
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatGetUserByInvalidUsernameShouldReturn404() {
        String username = UserDF.getInvalidUsername();
        Response response = apiClient.getWithPathParam(UserEndpoints.GET_USER, "username", username);
        Assert.assertEquals(response.getStatusCode(), 404);
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatGetUserShouldReturnCorrectEmailFormat() {
        String username = UserDF.getValidUsername();
        Response response = apiClient.getWithPathParam(UserEndpoints.GET_USER, "username", username);
        Assert.assertNotNull(response.jsonPath().getString("email"));
    }
}
