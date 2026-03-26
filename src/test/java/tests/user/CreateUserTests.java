package tests.user;

import base.BaseApiTest;
import dataFactory.UserDF;
import dto.user.CreateUserRequest;
import dto.user.CreateUserResponse;
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
        CreateUserResponse responseDto = response.as(CreateUserResponse.class);
        
        Assert.assertEquals(statusCode, 200);
        
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(responseDto.getCode(), 200);
        softAssert.assertNotNull(responseDto.getMessage());
        softAssert.assertAll();
    }

    @Test(groups = {"smoke", "regression", "user", "positive"})
    public void verifyThatCreateUsersWithArrayShouldReturn200() {
        Response response = apiClient.post(UserEndpoints.CREATE_USER_WITH_ARRAY,
                UserDF.getArrayData());
        int statusCode = response.getStatusCode();
        CreateUserResponse responseDto = response.as(CreateUserResponse.class);
        
        Assert.assertEquals(statusCode, 200);
        
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(responseDto.getCode(), 200);
        softAssert.assertAll();
    }

    @Test(groups = {"smoke", "regression", "user", "positive"})
    public void verifyThatCreateUsersWithListShouldReturn200() {
        Response response = apiClient.post(UserEndpoints.CREATE_USER_WITH_LIST,
                UserDF.getListData());
        int statusCode = response.getStatusCode();
        CreateUserResponse responseDto = response.as(CreateUserResponse.class);
        
        Assert.assertEquals(statusCode, 200);
        
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(responseDto.getCode(), 200);
        softAssert.assertAll();
    }
}