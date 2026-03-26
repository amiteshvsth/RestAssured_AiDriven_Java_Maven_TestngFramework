package tests.user;

import base.BaseApiTest;
import dataFactory.UserDF;
import endpoints.UserEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteUserTests extends BaseApiTest {

    @Test(groups = {"smoke", "regression", "user", "positive"})
    public void verifyThatDeleteUserShouldReturn200WhenUserExists() {
        String username = UserDF.getValidUsername();
        Response response = apiClient.deleteWithPathParam(UserEndpoints.DELETE_USER, "username", username);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("code"), 200);
        Assert.assertNotNull(response.jsonPath().get("message"));
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatDeleteUserShouldReturn404WhenUserNotFound() {
        String username = UserDF.getNonexistentUsername();
        Response response = apiClient.deleteWithPathParam(UserEndpoints.DELETE_USER, "username", username);
        Assert.assertEquals(response.getStatusCode(), 404);
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatDeleteUserShouldReturnResponseWithinTimeLimit() {
        String username = UserDF.getValidUsername();
        Response response = apiClient.deleteWithPathParam(UserEndpoints.DELETE_USER, "username", username);
        Assert.assertTrue(response.getTime() < 3000);
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatDeleteUserShouldReturnProperContentType() {
        String username = UserDF.getValidUsername();
        Response response = apiClient.deleteWithPathParam(UserEndpoints.DELETE_USER, "username", username);
        Assert.assertTrue(response.getContentType().contains("application/json"));
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatDeleteUserWithInvalidUsernameShouldReturn404() {
        String username = UserDF.getInvalidUsername();
        Response response = apiClient.deleteWithPathParam(UserEndpoints.DELETE_USER, "username", username);
        Assert.assertEquals(response.getStatusCode(), 404);
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatDeleteUserShouldReturnSuccessMessage() {
        String username = UserDF.getValidUsername();
        Response response = apiClient.deleteWithPathParam(UserEndpoints.DELETE_USER, "username", username);
        Assert.assertEquals(response.jsonPath().getString("message"), username);
    }
}