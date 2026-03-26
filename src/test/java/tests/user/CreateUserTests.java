package tests.user;

import base.BaseApiTest;
import dataFactory.UserDF;
import dto.user.CreateUserRequest;
import endpoints.UserEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateUserTests extends BaseApiTest {

    @Test(groups = {"smoke", "regression", "user", "positive"})
    public void verifyThatCreateUserShouldReturn200WhenValidPayload() {
        CreateUserRequest request = UserDF.getData();
        Response response = apiClient.post(UserEndpoints.CREATE_USER, request);
        int statusCode = response.getStatusCode();
        int responseCode = response.jsonPath().getInt("code");
        Object responseMessage = response.jsonPath().get("message");
        
        Assert.assertEquals(statusCode, 200);
        
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(responseCode, 200);
        softAssert.assertNotNull(responseMessage);
        softAssert.assertAll();
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
        int responseCode = response.jsonPath().getInt("code");
        
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(responseCode, 200);
        softAssert.assertAll();
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatCreateUserShouldReturnResponseWithinTimeLimit() {
        CreateUserRequest request = UserDF.getData();
        Response response = apiClient.post(UserEndpoints.CREATE_USER, request);
        long responseTime = response.getTime();
        
        Assert.assertTrue(responseTime < 3000);
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatCreateUserShouldReturnProperContentType() {
        CreateUserRequest request = UserDF.getData();
        Response response = apiClient.post(UserEndpoints.CREATE_USER, request);
        String contentType = response.getContentType();
        
        Assert.assertTrue(contentType.contains("application/json"));
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatCreateUserWithInvalidEmailShouldReturn200() {
        CreateUserRequest request = UserDF.getData();
        request.setEmail("invalid-email");
        Response response = apiClient.post(UserEndpoints.CREATE_USER, request);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }

    @Test(groups = {"regression", "user", "edge"})
    public void verifyThatCreateUserWithEmptyPayloadShouldReturn200() {
        CreateUserRequest request = new CreateUserRequest();
        Response response = apiClient.post(UserEndpoints.CREATE_USER, request);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatCreateUsersWithArrayShouldReturn200() {
        Response response = apiClient.post(UserEndpoints.CREATE_USER_WITH_ARRAY,
                UserDF.getArrayData());
        int statusCode = response.getStatusCode();
        int responseCode = response.jsonPath().getInt("code");
        
        Assert.assertEquals(statusCode, 200);
        
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(responseCode, 200);
        softAssert.assertAll();
    }

    @Test(groups = {"regression", "user", "positive"})
    public void verifyThatCreateUsersWithListShouldReturn200() {
        Response response = apiClient.post(UserEndpoints.CREATE_USER_WITH_LIST,
                UserDF.getListData());
        int statusCode = response.getStatusCode();
        int responseCode = response.jsonPath().getInt("code");
        
        Assert.assertEquals(statusCode, 200);
        
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(responseCode, 200);
        softAssert.assertAll();
    }
}