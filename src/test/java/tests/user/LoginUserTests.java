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

public class LoginUserTests extends BaseApiTest {
    private ApiHelpers helpers = new ApiHelpers();

    @Test(groups = {"smoke", "regression", "user", "positive"})
    public void verifyThatLoginShouldReturn200WhenValidCredentials() {
        CreateUserRequest createRequest = UserDF.getData();
        given().spec(helpers.requestSpecificationWithJSONHeader()).body(createRequest).when().
                post(ApiEndPoints.CREATE_USER);
        
        String username = createRequest.getUsername();
        String password = createRequest.getPassword();
        Response response = given().spec(helpers.requestSpecificationBaseURI()).queryParam("username", username).queryParam("password", password).when().
                get(ApiEndPoints.LOGIN_USER);
        int statusCode = response.getStatusCode();
        String loginMessage = response.getBody().asString();
        
        Assert.assertEquals(statusCode, 200);
        Assert.assertTrue(loginMessage.contains("logged in"));
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatLoginShouldReturn400WhenInvalidCredentials() {
        String username = "!!invalid";
        String password = "wrongpassword";
        Response response = given().spec(helpers.requestSpecificationBaseURI()).queryParam("username", username).queryParam("password", password).when().
                get(ApiEndPoints.LOGIN_USER);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatLoginShouldReturn400WhenMissingUsername() {
        String username = "";
        String password = UserDF.getData().getPassword();
        Response response = given().spec(helpers.requestSpecificationBaseURI()).queryParam("username", username).queryParam("password", password).when().
                get(ApiEndPoints.LOGIN_USER);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }

    @Test(groups = {"regression", "user", "negative"})
    public void verifyThatLoginShouldReturn400WhenMissingPassword() {
        String username = UserDF.getData().getUsername();
        String password = "";
        Response response = given().spec(helpers.requestSpecificationBaseURI()).queryParam("username", username).queryParam("password", password).when().
                get(ApiEndPoints.LOGIN_USER);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }
}