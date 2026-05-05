package tests.store;

import base.BaseApiTest;
import dataFactory.StoreDF;
import dto.store.GetOrderResponse;
import utils.ApiEndPoints;
import utils.ApiHelpers;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetOrderTests extends BaseApiTest {
    private ApiHelpers helpers = new ApiHelpers();

    @Test(groups = {"smoke", "regression", "store", "positive"})
    public void verifyThatGetOrderShouldReturn200WhenOrderExists() {
        Response createResponse = given().spec(helpers.requestSpecificationWithJSONHeader()).body(StoreDF.getData()).when().
                post(ApiEndPoints.PLACE_ORDER);
        long orderId = createResponse.as(dto.store.PlaceOrderResponse.class).getId();
        
        Response response = given().spec(helpers.requestSpecificationBaseURI()).pathParam("orderId", orderId).when().
                get(ApiEndPoints.GET_ORDER);
        int statusCode = response.getStatusCode();
        GetOrderResponse responseDto = response.as(GetOrderResponse.class);
        
        Assert.assertEquals(statusCode, 200);
        Assert.assertNotNull(responseDto);
    }

    @Test(groups = {"regression", "store", "negative"})
    public void verifyThatGetOrderShouldReturn404WhenOrderNotFound() {
        long orderId = 999999999L;
        Response response = given().spec(helpers.requestSpecificationBaseURI()).pathParam("orderId", orderId).when().
                get(ApiEndPoints.GET_ORDER);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 404);
    }

    @Test(groups = {"regression", "store", "negative"})
    public void verifyThatGetOrderShouldReturn400WhenInvalidId() {
        long orderId = -1L;
        Response response = given().spec(helpers.requestSpecificationBaseURI()).pathParam("orderId", orderId).when().
                get(ApiEndPoints.GET_ORDER);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 404);
    }
}