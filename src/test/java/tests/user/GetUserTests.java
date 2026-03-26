package tests.user;

import base.BaseApiTest;
import dataFactory.UserDF;
import dto.user.GetUserResponse;
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
        GetUserResponse responseDto = response.as(GetUserResponse.class);
        
        Assert.assertEquals(statusCode, 200);
        
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(responseDto.getUsername(), username);
        softAssert.assertNotNull(responseDto.getId());
        softAssert.assertNotNull(responseDto.getEmail());
        softAssert.assertAll();
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatGetUserShouldReturn404WhenUserNotFound() {
        String username = UserDF.getNonexistentUsername();
        Response response = apiClient.getWithPathParam(UserEndpoints.GET_USER, "username", username);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 404);
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatGetUserByInvalidUsernameShouldReturn400() {
        String username = UserDF.getInvalidUsername();
        Response response = apiClient.getWithPathParam(UserEndpoints.GET_USER, "username", username);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 400);
    }
}