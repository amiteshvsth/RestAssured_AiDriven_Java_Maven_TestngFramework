package tests.store;

import base.BaseApiTest;
import dataFactory.StoreDF;
import endpoints.StoreEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GetOrderTests extends BaseApiTest {

    @Test(groups = {"smoke", "regression", "store", "positive"})
    public void verifyThatGetOrderShouldReturn200WhenOrderExists() {
        long orderId = StoreDF.getValidId();
        Response response = apiClient.getWithPathParam(StoreEndpoints.GET_ORDER, "orderId", orderId);
        int statusCode = response.getStatusCode();
        Long responseId = response.jsonPath().getLong("id");
        Object responsePetId = response.jsonPath().get("petId");
        Object responseQuantity = response.jsonPath().get("quantity");
        Object responseStatus = response.jsonPath().get("status");
        
        Assert.assertEquals(statusCode, 200);
        
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(responseId, Long.valueOf(orderId));
        softAssert.assertNotNull(responsePetId);
        softAssert.assertNotNull(responseQuantity);
        softAssert.assertNotNull(responseStatus);
        softAssert.assertAll();
    }

    @Test(groups = {"regression", "store", "negative"})
    public void verifyThatGetOrderShouldReturn404WhenOrderNotFound() {
        long orderId = StoreDF.getNonexistentId();
        Response response = apiClient.getWithPathParam(StoreEndpoints.GET_ORDER, "orderId", orderId);
        int statusCode = response.getStatusCode();
        int responseCode = response.jsonPath().getInt("code");
        
        Assert.assertEquals(statusCode, 404);
        
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(responseCode, 404);
        softAssert.assertAll();
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatGetOrderShouldReturnResponseWithinTimeLimit() {
        long orderId = StoreDF.getValidId();
        Response response = apiClient.getWithPathParam(StoreEndpoints.GET_ORDER, "orderId", orderId);
        long responseTime = response.getTime();
        
        Assert.assertTrue(responseTime < 3000);
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatGetOrderShouldReturnProperContentType() {
        long orderId = StoreDF.getValidId();
        Response response = apiClient.getWithPathParam(StoreEndpoints.GET_ORDER, "orderId", orderId);
        String contentType = response.getContentType();
        
        Assert.assertTrue(contentType.contains("application/json"));
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatGetOrderShouldReturnAllRequiredFields() {
        long orderId = StoreDF.getValidId();
        Response response = apiClient.getWithPathParam(StoreEndpoints.GET_ORDER, "orderId", orderId);
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

    @Test(groups = {"regression", "store", "negative"})
    public void verifyThatGetOrderWithInvalidIdShouldReturn404() {
        long orderId = StoreDF.getInvalidId();
        Response response = apiClient.getWithPathParam(StoreEndpoints.GET_ORDER, "orderId", orderId);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 404);
    }
}