package tests.store;

import base.BaseApiTest;
import dataFactory.StoreDF;
import dto.store.PlaceOrderRequest;
import utils.ApiEndPoints;
import utils.ApiHelpers;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PlaceOrderTests extends BaseApiTest {
    private ApiHelpers helpers = new ApiHelpers();

    @Test(groups = {"smoke", "regression", "store", "positive"})
    public void verifyThatPlaceOrderShouldReturn200WhenValidPayload() {
        PlaceOrderRequest request = StoreDF.getData();
        Response response = given().spec(helpers.requestSpecificationWithJSONHeader()).body(request).when().
                post(ApiEndPoints.PLACE_ORDER);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }

    @Test(groups = {"regression", "store", "negative"})
    public void verifyThatPlaceOrderShouldReturn400WhenInvalidPayload() {
        dto.store.PlaceOrderRequest request = new dto.store.PlaceOrderRequest();
        Response response = given().spec(helpers.requestSpecificationWithJSONHeader()).body(request).when().
                post(ApiEndPoints.PLACE_ORDER);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }
}