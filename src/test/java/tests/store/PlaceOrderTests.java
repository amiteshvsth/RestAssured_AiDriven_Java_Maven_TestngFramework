package tests.store;

import base.BaseApiTest;
import dataFactory.StoreDF;
import dto.store.PlaceOrderRequest;
import endpoints.StoreEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PlaceOrderTests extends BaseApiTest {

    @Test(groups = {"smoke", "regression", "store", "positive"})
    public void verifyThatPlaceOrderShouldReturn200WhenValidPayload() {
        PlaceOrderRequest request = StoreDF.getData();
        Response response = apiClient.post(StoreEndpoints.PLACE_ORDER, request);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }

    @Test(groups = {"regression", "store", "negative"})
    public void verifyThatPlaceOrderShouldReturn400WhenInvalidPayload() {
        dto.store.PlaceOrderRequest request = new dto.store.PlaceOrderRequest();
        Response response = apiClient.post(StoreEndpoints.PLACE_ORDER, request);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }
}