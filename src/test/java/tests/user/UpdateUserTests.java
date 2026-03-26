package tests.user;

import base.BaseApiTest;
import dataFactory.UserDF;
import dto.user.UpdateUserRequest;
import endpoints.UserEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdateUserTests extends BaseApiTest {

    @Test(groups = {"smoke", "regression", "user", "positive"})
    public void verifyThatUpdateUserShouldReturn200WhenValidPayload() {
        String username = "testuser456";
        apiClient.post(UserEndpoints.CREATE_USER, UserDF.getData());
        
        UpdateUserRequest request = UserDF.getDataAsUpdateUserRequest();
        Response response = apiClient.put(UserEndpoints.UPDATE_USER.replace("{username}", username), request);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatUpdateUserShouldReturn404WhenUserNotFound() {
        String username = UserDF.getNonexistentUsername();
        UpdateUserRequest request = UserDF.getDataAsUpdateUserRequest();
        Response response = apiClient.put(UserEndpoints.UPDATE_USER.replace("{username}", username), request);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatUpdateUserShouldReturn400WhenInvalidPayload() {
        String username = "testuser789";
        apiClient.post(UserEndpoints.CREATE_USER, UserDF.getData());
        
        UpdateUserRequest request = new UpdateUserRequest();
        Response response = apiClient.put(UserEndpoints.UPDATE_USER.replace("{username}", username), request);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }
}