package tests.user;

import base.BaseApiTest;
import dataFactory.UserDF;
import endpoints.UserEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GetUserTests extends BaseApiTest {

    @Test(groups = {"smoke", "regression", "user", "positive"})
    public void verifyThatGetUserShouldReturn200WhenUserExists() {
        String username = UserDF.getValidUsername();
        Response response = apiClient.getWithPathParam(UserEndpoints.GET_USER, "username", username);
        int statusCode = response.getStatusCode();
        String responseUsername = response.jsonPath().getString("username");
        Object responseId = response.jsonPath().get("id");
        Object responseEmail = response.jsonPath().get("email");
        
        Assert.assertEquals(statusCode, 200);
        
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(responseUsername, username);
        softAssert.assertNotNull(responseId);
        softAssert.assertNotNull(responseEmail);
        softAssert.assertAll();
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatGetUserShouldReturn404WhenUserNotFound() {
        String username = UserDF.getNonexistentUsername();
        Response response = apiClient.getWithPathParam(UserEndpoints.GET_USER, "username", username);
        int statusCode = response.getStatusCode();
        int responseCode = response.jsonPath().getInt("code");
        
        Assert.assertEquals(statusCode, 404);
        
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(responseCode, 404);
        softAssert.assertAll();
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatGetUserShouldReturnResponseWithinTimeLimit() {
        String username = UserDF.getValidUsername();
        Response response = apiClient.getWithPathParam(UserEndpoints.GET_USER, "username", username);
        long responseTime = response.getTime();
        
        Assert.assertTrue(responseTime < 3000);
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatGetUserShouldReturnProperContentType() {
        String username = UserDF.getValidUsername();
        Response response = apiClient.getWithPathParam(UserEndpoints.GET_USER, "username", username);
        String contentType = response.getContentType();
        
        Assert.assertTrue(contentType.contains("application/json"));
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatGetUserShouldReturnAllRequiredFields() {
        String username = UserDF.getValidUsername();
        Response response = apiClient.getWithPathParam(UserEndpoints.GET_USER, "username", username);
        Object responseId = response.jsonPath().get("id");
        Object responseUsername = response.jsonPath().get("username");
        Object responseFirstName = response.jsonPath().get("firstName");
        Object responseLastName = response.jsonPath().get("lastName");
        Object responseEmail = response.jsonPath().get("email");
        Object responsePassword = response.jsonPath().get("password");
        Object responsePhone = response.jsonPath().get("phone");
        Object responseUserStatus = response.jsonPath().get("userStatus");
        
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(responseId);
        softAssert.assertNotNull(responseUsername);
        softAssert.assertNotNull(responseFirstName);
        softAssert.assertNotNull(responseLastName);
        softAssert.assertNotNull(responseEmail);
        softAssert.assertNotNull(responsePassword);
        softAssert.assertNotNull(responsePhone);
        softAssert.assertNotNull(responseUserStatus);
        softAssert.assertAll();
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatGetUserByInvalidUsernameShouldReturn404() {
        String username = UserDF.getInvalidUsername();
        Response response = apiClient.getWithPathParam(UserEndpoints.GET_USER, "username", username);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 404);
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatGetUserShouldReturnCorrectEmailFormat() {
        String username = UserDF.getValidUsername();
        Response response = apiClient.getWithPathParam(UserEndpoints.GET_USER, "username", username);
        String responseEmail = response.jsonPath().getString("email");
        
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(responseEmail);
        softAssert.assertAll();
    }
}