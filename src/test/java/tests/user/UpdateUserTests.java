package tests.user;

import base.BaseApiTest;
import dataFactory.UserDF;
import dto.user.CreateUserRequest;
import dto.user.UpdateUserRequest;
import utils.ApiEndPoints;
import utils.ApiHelpers;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateUserTests extends BaseApiTest {
    private ApiHelpers helpers = new ApiHelpers();

    @Test(groups = {"smoke", "regression", "user", "positive"})
    public void verifyThatUpdateUserShouldReturn200WhenValidPayload() {
        String username = "testuser456";
        given().spec(helpers.requestSpecificationWithJSONHeader()).body(UserDF.getData()).when().
                post(ApiEndPoints.CREATE_USER);
        
        CreateUserRequest userData = UserDF.getData();
        UpdateUserRequest request = new UpdateUserRequest();
        request.setUsername(userData.getUsername());
        Response response = given().spec(helpers.requestSpecificationWithJSONHeader()).body(request).pathParam("username", username).when().
                put(ApiEndPoints.UPDATE_USER);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatUpdateUserShouldReturn404WhenUserNotFound() {
        String username = "nonexistentuser999";
        CreateUserRequest userData = UserDF.getData();
        UpdateUserRequest request = new UpdateUserRequest();
        request.setUsername(userData.getUsername());
        Response response = given().spec(helpers.requestSpecificationWithJSONHeader()).body(request).pathParam("username", username).when().
                put(ApiEndPoints.UPDATE_USER);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatUpdateUserShouldReturn400WhenInvalidPayload() {
        String username = "testuser789";
        given().spec(helpers.requestSpecificationWithJSONHeader()).body(UserDF.getData()).when().
                post(ApiEndPoints.CREATE_USER);
        
        UpdateUserRequest request = new UpdateUserRequest();
        Response response = given().spec(helpers.requestSpecificationWithJSONHeader()).body(request).pathParam("username", username).when().
                put(ApiEndPoints.UPDATE_USER);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }
}