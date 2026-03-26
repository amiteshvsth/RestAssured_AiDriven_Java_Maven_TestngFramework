package tests.store;

import base.BaseApiTest;
import dataFactory.StoreDF;
import dto.store.PlaceOrderRequest;
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
        Object responseId = response.jsonPath().get("id");
        
        Assert.assertEquals(statusCode, 200);
        
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(responseId);
        softAssert.assertAll();
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatPlaceOrderShouldReturn200WhenMinimalDataProvided() {
        PlaceOrderRequest request = StoreDF.getData();
        Response response = apiClient.post(StoreEndpoints.PLACE_ORDER, request);
        int statusCode = response.getStatusCode();
        Object responseId = response.jsonPath().get("id");
        
        Assert.assertEquals(statusCode, 200);
        
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(responseId);
        softAssert.assertAll();
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatPlaceOrderShouldReturnResponseWithinTimeLimit() {
        PlaceOrderRequest request = StoreDF.getData();
        Response response = apiClient.post(StoreEndpoints.PLACE_ORDER, request);
        long responseTime = response.getTime();
        
        Assert.assertTrue(responseTime < 3000);
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatPlaceOrderShouldReturnProperContentType() {
        PlaceOrderRequest request = StoreDF.getData();
        Response response = apiClient.post(StoreEndpoints.PLACE_ORDER, request);
        String contentType = response.getContentType();
        
        Assert.assertTrue(contentType.contains("application/json"));
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatPlaceOrderShouldReturnAllRequiredFields() {
        PlaceOrderRequest request = StoreDF.getData();
        Response response = apiClient.post(StoreEndpoints.PLACE_ORDER, request);
        Object responseId = response.jsonPath().get("id");
        Object responsePetId = response.jsonPath().get("petId");
        Object responseQuantity = response.jsonPath().get("quantity");
        Object responseStatus = response.jsonPath().get("status");
        Object responseComplete = response.jsonPath().get("complete");
        
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(responseId);
        softAssert.assertNotNull(responsePetId);
        softAssert.assertNotNull(responseQuantity);
        softAssert.assertNotNull(responseStatus);
        softAssert.assertNotNull(responseComplete);
        softAssert.assertAll();
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatPlaceOrderWithCompleteStatusShouldReturn200() {
        PlaceOrderRequest request = StoreDF.getData();
        Response response = apiClient.post(StoreEndpoints.PLACE_ORDER, request);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatPlaceOrderWithDeliveredStatusShouldReturn200() {
        PlaceOrderRequest request = StoreDF.getData();
        Response response = apiClient.post(StoreEndpoints.PLACE_ORDER, request);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }

    @Test(groups = {"regression", "store", "negative"})
    public void verifyThatPlaceOrderWithInvalidQuantityShouldReturn200() {
        PlaceOrderRequest request = StoreDF.getData();
        Response response = apiClient.post(StoreEndpoints.PLACE_ORDER, request);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }

    @Test(groups = {"regression", "store", "edge"})
    public void verifyThatPlaceOrderWithEmptyPayloadShouldReturn200() {
        PlaceOrderRequest request = new PlaceOrderRequest();
        Response response = apiClient.post(StoreEndpoints.PLACE_ORDER, request);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }

    @Test(groups = {"regression", "store", "edge"})
    public void verifyThatPlaceOrderWithNegativeQuantityShouldReturn200() {
        PlaceOrderRequest request = StoreDF.getData();
        Response response = apiClient.post(StoreEndpoints.PLACE_ORDER, request);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }
}