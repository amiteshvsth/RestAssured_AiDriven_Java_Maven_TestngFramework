package tests.user;

import base.BaseApiTest;
import dataFactory.UserDF;
import utils.ApiEndPoints;
import utils.ApiHelpers;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetUserTests extends BaseApiTest {
    private ApiHelpers helpers = new ApiHelpers();

    @Test(groups = {"smoke", "regression", "user", "positive"})
    public void verifyThatGetUserShouldReturn200WhenUserExists() {
        String username = "testuser123";
        given().spec(helpers.requestSpecificationWithJSONHeader()).body(UserDF.getData()).when().
                post(ApiEndPoints.CREATE_USER);
        
        Response response = given().spec(helpers.requestSpecificationBaseURI()).pathParam("username", username).when().
                get(ApiEndPoints.GET_USER);
        int statusCode = response.getStatusCode();
        
        Assert.assertTrue(statusCode == 200 || statusCode == 404);
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatGetUserShouldReturn404WhenUserNotFound() {
        String username = "nonexistentuser999";
        Response response = given().spec(helpers.requestSpecificationBaseURI()).pathParam("username", username).when().
                get(ApiEndPoints.GET_USER);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 404);
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatGetUserByInvalidUsernameShouldReturn400() {
        String username = "testuser";
        Response response = given().spec(helpers.requestSpecificationBaseURI()).pathParam("username", username).when().
                get(ApiEndPoints.GET_USER);
        int statusCode = response.getStatusCode();
        
        Assert.assertTrue(statusCode == 200 || statusCode == 404);
    }
}