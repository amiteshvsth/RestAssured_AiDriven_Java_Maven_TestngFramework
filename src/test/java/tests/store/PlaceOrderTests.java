package tests.store;

import base.BaseApiTest;
import dataFactory.StoreDF;
import dto.store.PlaceOrderRequest;
import dto.store.PlaceOrderResponse;
import endpoints.StoreEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PlaceOrderTests extends BaseApiTest {

    @Test(groups = {"smoke", "regression", "store", "positive"})
    public void verifyThatPlaceOrderShouldReturn200WhenValidPayload() {
        PlaceOrderRequest request = StoreDF.getData();
        Response response = apiClient.post(StoreEndpoints.PLACE_ORDER, request);
        int statusCode = response.getStatusCode();
        PlaceOrderResponse responseDto = response.as(PlaceOrderResponse.class);
        
        Assert.assertEquals(statusCode, 200);
        
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(responseDto.getId());
        softAssert.assertNotNull(responseDto.getStatus());
        softAssert.assertAll();
    }

    @Test(groups = {"regression", "store", "negative"})
    public void verifyThatPlaceOrderShouldReturn400WhenInvalidPayload() {
        PlaceOrderRequest request = new PlaceOrderRequest();
        Response response = apiClient.post(StoreEndpoints.PLACE_ORDER, request);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 400);
    }
}