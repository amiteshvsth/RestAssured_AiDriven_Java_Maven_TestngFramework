package tests.user;

import base.BaseApiTest;
import dataFactory.UserDF;
import endpoints.UserEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DeleteUserTests extends BaseApiTest {

    @Test(groups = {"smoke", "regression", "user", "positive"})
    public void verifyThatDeleteUserShouldReturn200WhenUserExists() {
        String username = UserDF.getValidUsername();
        Response response = apiClient.deleteWithPathParam(UserEndpoints.DELETE_USER, "username", username);
        int statusCode = response.getStatusCode();
        int responseCode = response.jsonPath().getInt("code");
        Object responseMessage = response.jsonPath().get("message");
        
        Assert.assertEquals(statusCode, 200);
        
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(responseCode, 200);
        softAssert.assertNotNull(responseMessage);
        softAssert.assertAll();
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatDeleteUserShouldReturn404WhenUserNotFound() {
        String username = UserDF.getNonexistentUsername();
        Response response = apiClient.deleteWithPathParam(UserEndpoints.DELETE_USER, "username", username);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 404);
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatDeleteUserShouldReturnResponseWithinTimeLimit() {
        String username = UserDF.getValidUsername();
        Response response = apiClient.deleteWithPathParam(UserEndpoints.DELETE_USER, "username", username);
        long responseTime = response.getTime();
        
        Assert.assertTrue(responseTime < 3000);
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatDeleteUserShouldReturnProperContentType() {
        String username = UserDF.getValidUsername();
        Response response = apiClient.deleteWithPathParam(UserEndpoints.DELETE_USER, "username", username);
        String contentType = response.getContentType();
        
        Assert.assertTrue(contentType.contains("application/json"));
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatDeleteUserWithInvalidUsernameShouldReturn404() {
        String username = UserDF.getInvalidUsername();
        Response response = apiClient.deleteWithPathParam(UserEndpoints.DELETE_USER, "username", username);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 404);
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatDeleteUserShouldReturnSuccessMessage() {
        String username = UserDF.getValidUsername();
        Response response = apiClient.deleteWithPathParam(UserEndpoints.DELETE_USER, "username", username);
        int statusCode = response.getStatusCode();
        String responseMessage = response.jsonPath().getString("message");
        
        Assert.assertEquals(statusCode, 200);
        
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(responseMessage, username);
        softAssert.assertAll();
    }
}