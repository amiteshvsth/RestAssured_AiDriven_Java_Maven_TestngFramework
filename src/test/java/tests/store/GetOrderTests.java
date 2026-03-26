package tests.store;

import base.BaseApiTest;
import dataFactory.StoreDF;
import dto.store.GetOrderResponse;
import endpoints.StoreEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetOrderTests extends BaseApiTest {

    @Test(groups = {"smoke", "regression", "store", "positive"})
    public void verifyThatGetOrderShouldReturn200WhenOrderExists() {
        Response createResponse = apiClient.post(StoreEndpoints.PLACE_ORDER, StoreDF.getData());
        long orderId = createResponse.as(dto.store.PlaceOrderResponse.class).getId();
        
        Response response = apiClient.getWithPathParam(StoreEndpoints.GET_ORDER, "orderId", orderId);
        int statusCode = response.getStatusCode();
        GetOrderResponse responseDto = response.as(GetOrderResponse.class);
        
        Assert.assertEquals(statusCode, 200);
        Assert.assertNotNull(responseDto);
    }

    @Test(groups = {"regression", "store", "negative"})
    public void verifyThatGetOrderShouldReturn404WhenOrderNotFound() {
        long orderId = StoreDF.getNonexistentId();
        Response response = apiClient.getWithPathParam(StoreEndpoints.GET_ORDER, "orderId", orderId);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 404);
    }

    @Test(groups = {"regression", "store", "negative"})
    public void verifyThatGetOrderShouldReturn400WhenInvalidId() {
        long orderId = StoreDF.getInvalidId();
        Response response = apiClient.getWithPathParam(StoreEndpoints.GET_ORDER, "orderId", orderId);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 404);
    }
}