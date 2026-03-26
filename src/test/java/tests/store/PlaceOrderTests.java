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
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotNull(response.jsonPath().get("id"));
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatPlaceOrderShouldReturn200WhenMinimalDataProvided() {
        PlaceOrderRequest request = StoreDF.getData();
        Response response = apiClient.post(StoreEndpoints.PLACE_ORDER, request);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotNull(response.jsonPath().get("id"));
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatPlaceOrderShouldReturnResponseWithinTimeLimit() {
        PlaceOrderRequest request = StoreDF.getData();
        Response response = apiClient.post(StoreEndpoints.PLACE_ORDER, request);
        Assert.assertTrue(response.getTime() < 3000);
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatPlaceOrderShouldReturnProperContentType() {
        PlaceOrderRequest request = StoreDF.getData();
        Response response = apiClient.post(StoreEndpoints.PLACE_ORDER, request);
        Assert.assertTrue(response.getContentType().contains("application/json"));
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatPlaceOrderShouldReturnAllRequiredFields() {
        PlaceOrderRequest request = StoreDF.getData();
        Response response = apiClient.post(StoreEndpoints.PLACE_ORDER, request);
        Assert.assertNotNull(response.jsonPath().get("id"));
        Assert.assertNotNull(response.jsonPath().get("petId"));
        Assert.assertNotNull(response.jsonPath().get("quantity"));
        Assert.assertNotNull(response.jsonPath().get("status"));
        Assert.assertNotNull(response.jsonPath().get("complete"));
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatPlaceOrderWithCompleteStatusShouldReturn200() {
        PlaceOrderRequest request = StoreDF.getData();
        Response response = apiClient.post(StoreEndpoints.PLACE_ORDER, request);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatPlaceOrderWithDeliveredStatusShouldReturn200() {
        PlaceOrderRequest request = StoreDF.getData();
        Response response = apiClient.post(StoreEndpoints.PLACE_ORDER, request);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(groups = {"regression", "store", "negative"})
    public void verifyThatPlaceOrderWithInvalidQuantityShouldReturn200() {
        PlaceOrderRequest request = StoreDF.getData();
        Response response = apiClient.post(StoreEndpoints.PLACE_ORDER, request);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(groups = {"regression", "store", "edge"})
    public void verifyThatPlaceOrderWithEmptyPayloadShouldReturn200() {
        PlaceOrderRequest request = new PlaceOrderRequest();
        Response response = apiClient.post(StoreEndpoints.PLACE_ORDER, request);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(groups = {"regression", "store", "edge"})
    public void verifyThatPlaceOrderWithNegativeQuantityShouldReturn200() {
        PlaceOrderRequest request = StoreDF.getData();
        Response response = apiClient.post(StoreEndpoints.PLACE_ORDER, request);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}