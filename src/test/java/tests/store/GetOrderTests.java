package tests.store;

import base.BaseApiTest;
import dataFactory.StoreDF;
import endpoints.StoreEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetOrderTests extends BaseApiTest {

    @Test(groups = {"smoke", "regression", "store", "positive"})
    public void verifyThatGetOrderShouldReturn200WhenOrderExists() {
        long orderId = StoreDF.getValidId();
        Response response = apiClient.getWithPathParam(StoreEndpoints.GET_ORDER, "orderId", orderId);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getLong("id"), Long.valueOf(orderId));
        Assert.assertNotNull(response.jsonPath().get("petId"));
        Assert.assertNotNull(response.jsonPath().get("quantity"));
        Assert.assertNotNull(response.jsonPath().get("status"));
    }

    @Test(groups = {"regression", "store", "negative"})
    public void verifyThatGetOrderShouldReturn404WhenOrderNotFound() {
        long orderId = StoreDF.getNonexistentId();
        Response response = apiClient.getWithPathParam(StoreEndpoints.GET_ORDER, "orderId", orderId);
        Assert.assertEquals(response.getStatusCode(), 404);
        Assert.assertEquals(response.jsonPath().getInt("code"), 404);
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatGetOrderShouldReturnResponseWithinTimeLimit() {
        long orderId = StoreDF.getValidId();
        Response response = apiClient.getWithPathParam(StoreEndpoints.GET_ORDER, "orderId", orderId);
        Assert.assertTrue(response.getTime() < 3000);
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatGetOrderShouldReturnProperContentType() {
        long orderId = StoreDF.getValidId();
        Response response = apiClient.getWithPathParam(StoreEndpoints.GET_ORDER, "orderId", orderId);
        Assert.assertTrue(response.getContentType().contains("application/json"));
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatGetOrderShouldReturnAllRequiredFields() {
        long orderId = StoreDF.getValidId();
        Response response = apiClient.getWithPathParam(StoreEndpoints.GET_ORDER, "orderId", orderId);
        Assert.assertNotNull(response.jsonPath().get("id"));
        Assert.assertNotNull(response.jsonPath().get("petId"));
        Assert.assertNotNull(response.jsonPath().get("quantity"));
        Assert.assertNotNull(response.jsonPath().get("status"));
        Assert.assertNotNull(response.jsonPath().get("complete"));
    }

    @Test(groups = {"regression", "store", "negative"})
    public void verifyThatGetOrderWithInvalidIdShouldReturn404() {
        long orderId = StoreDF.getInvalidId();
        Response response = apiClient.getWithPathParam(StoreEndpoints.GET_ORDER, "orderId", orderId);
        Assert.assertEquals(response.getStatusCode(), 404);
    }
}