package tests.user;

import base.BaseApiTest;
import dataFactory.UserDF;
import dto.user.CreateUserRequest;
import dto.user.CreateUserResponse;
import dto.user.CreateUsersWithArrayRequest;
import dto.user.CreateUsersWithListRequest;
import utils.ApiEndPoints;
import utils.ApiHelpers;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class CreateUserTests extends BaseApiTest {
    private ApiHelpers helpers = new ApiHelpers();

    @Test(groups = {"smoke", "regression", "user", "positive"})
    public void verifyThatCreateUserShouldReturn200WhenValidPayload() {
        CreateUserRequest request = UserDF.getData();
        Response response = given().spec(helpers.requestSpecificationWithJSONHeader()).body(request).when().
                post(ApiEndPoints.CREATE_USER);
        
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
        CreateUsersWithArrayRequest request = new CreateUsersWithArrayRequest();
        List<CreateUserRequest> users = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            users.add(UserDF.getData());
        }
        request.setUsers(users);
        
        Response response = given().spec(helpers.requestSpecificationWithJSONHeader()).body(request).when().
                post(ApiEndPoints.CREATE_USER_WITH_ARRAY);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }

    @Test(groups = {"smoke", "regression", "user", "positive"})
    public void verifyThatCreateUsersWithListShouldReturn200() {
        CreateUsersWithListRequest request = new CreateUsersWithListRequest();
        List<CreateUserRequest> users = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            users.add(UserDF.getData());
        }
        request.setUsers(users);
        
        Response response = given().spec(helpers.requestSpecificationWithJSONHeader()).body(request).when().
                post(ApiEndPoints.CREATE_USER_WITH_LIST);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }
}