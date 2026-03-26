package tests.user;

import base.BaseApiTest;
import dataFactory.UserDF;
import dto.user.UpdateUserRequest;
import endpoints.UserEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class UpdateUserTests extends BaseApiTest {

    @Test(groups = {"smoke", "regression", "user", "positive"})
    public void verifyThatUpdateUserShouldReturn200WhenValidPayload() {
        String username = UserDF.getValidUsername();
        UpdateUserRequest request = UserDF.getUpdateData();
        Response response = apiClient.putWithPathParam(UserEndpoints.UPDATE_USER, request, "username", username);
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
    public void verifyThatUpdateUserShouldReturn404WhenUserNotFound() {
        String username = UserDF.getNonexistentUsername();
        UpdateUserRequest request = UserDF.getUpdateData();
        Response response = apiClient.putWithPathParam(UserEndpoints.UPDATE_USER, request, "username", username);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 404);
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatUpdateUserShouldReturn400WhenInvalidPayload() {
        String username = UserDF.getValidUsername();
        UpdateUserRequest request = new UpdateUserRequest();
        Response response = apiClient.putWithPathParam(UserEndpoints.UPDATE_USER, request, "username", username);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 400);
    }
}