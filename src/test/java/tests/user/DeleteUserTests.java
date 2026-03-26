package tests.user;

import base.BaseApiTest;
import dataFactory.UserDF;
import dto.user.CreateUserRequest;
import endpoints.UserEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteUserTests extends BaseApiTest {

    @Test(groups = {"smoke", "regression", "user", "positive"})
    public void verifyThatDeleteUserShouldReturn200WhenUserExists() {
        CreateUserRequest createRequest = UserDF.getData();
        apiClient.post(UserEndpoints.CREATE_USER, createRequest);
        
        String username = createRequest.getUsername();
        Response response = apiClient.deleteWithPathParam(UserEndpoints.DELETE_USER, "username", username);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatDeleteUserShouldReturn404WhenUserNotFound() {
        String username = UserDF.getNonexistentUsername();
        Response response = apiClient.deleteWithPathParam(UserEndpoints.DELETE_USER, "username", username);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 404);
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatDeleteUserShouldReturn400WhenInvalidUsername() {
        String username = UserDF.getInvalidUsername();
        Response response = apiClient.deleteWithPathParam(UserEndpoints.DELETE_USER, "username", username);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 404);
    }
}