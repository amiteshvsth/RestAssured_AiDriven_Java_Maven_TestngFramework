package tests.user;

import base.BaseApiTest;
import dataFactory.UserDF;
import dto.user.CreateUserRequest;
import endpoints.UserEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateUserTests extends BaseApiTest {

    @Test(groups = {"smoke", "regression", "user", "positive"})
    public void verifyThatCreateUserShouldReturn200WhenValidPayload() {
        CreateUserRequest request = UserDF.getData();
        Response response = apiClient.post(UserEndpoints.CREATE_USER, request);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("code"), 200);
        Assert.assertNotNull(response.jsonPath().get("message"));
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatCreateUserShouldReturn200WhenMinimalDataProvided() {
        CreateUserRequest request = UserDF.getData();
        request.setId(null);
        request.setFirstName(null);
        request.setLastName(null);
        request.setPhone(null);
        request.setUserStatus(null);
        Response response = apiClient.post(UserEndpoints.CREATE_USER, request);
        Assert.assertEquals(response.jsonPath().getInt("code"), 200);
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatCreateUserShouldReturnResponseWithinTimeLimit() {
        CreateUserRequest request = UserDF.getData();
        Response response = apiClient.post(UserEndpoints.CREATE_USER, request);
        Assert.assertTrue(response.getTime() < 3000);
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatCreateUserShouldReturnProperContentType() {
        CreateUserRequest request = UserDF.getData();
        Response response = apiClient.post(UserEndpoints.CREATE_USER, request);
        Assert.assertTrue(response.getContentType().contains("application/json"));
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatCreateUserWithInvalidEmailShouldReturn200() {
        CreateUserRequest request = UserDF.getData();
        request.setEmail("invalid-email");
        Response response = apiClient.post(UserEndpoints.CREATE_USER, request);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(groups = {"regression", "user", "edge"})
    public void verifyThatCreateUserWithEmptyPayloadShouldReturn200() {
        CreateUserRequest request = new CreateUserRequest();
        Response response = apiClient.post(UserEndpoints.CREATE_USER, request);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatCreateUsersWithArrayShouldReturn200() {
        Response response = apiClient.post(UserEndpoints.CREATE_USER_WITH_ARRAY,
                UserDF.getArrayData());
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("code"), 200);
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatCreateUsersWithListShouldReturn200() {
        Response response = apiClient.post(UserEndpoints.CREATE_USER_WITH_LIST,
                UserDF.getListData());
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("code"), 200);
    }
}
