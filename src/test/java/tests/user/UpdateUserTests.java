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
        String username = UserDF.getValidUsername();
        UpdateUserRequest request = UserDF.getUpdateData();
        Response response = apiClient.putWithPathParam(UserEndpoints.UPDATE_USER, request, "username", username);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("code"), 200);
        Assert.assertNotNull(response.jsonPath().get("message"));
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatUpdateUserShouldReturn200ForNonexistentUser() {
        String username = UserDF.getNonexistentUsername();
        UpdateUserRequest request = UserDF.getUpdateData();
        Response response = apiClient.putWithPathParam(UserEndpoints.UPDATE_USER, request, "username", username);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatUpdateUserShouldReturnResponseWithinTimeLimit() {
        String username = UserDF.getValidUsername();
        UpdateUserRequest request = UserDF.getUpdateData();
        Response response = apiClient.putWithPathParam(UserEndpoints.UPDATE_USER, request, "username", username);
        Assert.assertTrue(response.getTime() < 3000);
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatUpdateUserShouldReturnProperContentType() {
        String username = UserDF.getValidUsername();
        UpdateUserRequest request = UserDF.getUpdateData();
        Response response = apiClient.putWithPathParam(UserEndpoints.UPDATE_USER, request, "username", username);
        Assert.assertTrue(response.getContentType().contains("application/json"));
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatUpdateUserWithInvalidEmailShouldReturn200() {
        String username = UserDF.getValidUsername();
        UpdateUserRequest request = UserDF.getUpdateData();
        request.setEmail("invalid-email-format");
        Response response = apiClient.putWithPathParam(UserEndpoints.UPDATE_USER, request, "username", username);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(groups = {"regression", "user", "edge"})
    public void verifyThatUpdateUserWithEmptyPayloadShouldReturn200() {
        String username = UserDF.getValidUsername();
        UpdateUserRequest request = new UpdateUserRequest();
        Response response = apiClient.putWithPathParam(UserEndpoints.UPDATE_USER, request, "username", username);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}