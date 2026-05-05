package tests.user;

import base.BaseApiTest;
import dataFactory.UserDF;
import dto.user.CreateUserRequest;
import utils.ApiEndPoints;
import utils.ApiHelpers;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteUserTests extends BaseApiTest {
    private ApiHelpers helpers = new ApiHelpers();

    @Test(groups = {"smoke", "regression", "user", "positive"})
    public void verifyThatDeleteUserShouldReturn200WhenUserExists() {
        CreateUserRequest createRequest = UserDF.getData();
        given().spec(helpers.requestSpecificationWithJSONHeader()).body(createRequest).when().
                post(ApiEndPoints.CREATE_USER);
        
        String username = createRequest.getUsername();
        Response response = given().spec(helpers.requestSpecificationBaseURI()).pathParam("username", username).when().
                delete(ApiEndPoints.DELETE_USER);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatDeleteUserShouldReturn404WhenUserNotFound() {
        String username = "nonexistentuser999";
        Response response = given().spec(helpers.requestSpecificationBaseURI()).pathParam("username", username).when().
                delete(ApiEndPoints.DELETE_USER);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 404);
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatDeleteUserShouldReturn400WhenInvalidUsername() {
        String username = "!!invalid";
        Response response = given().spec(helpers.requestSpecificationBaseURI()).pathParam("username", username).when().
                delete(ApiEndPoints.DELETE_USER);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 404);
    }
}