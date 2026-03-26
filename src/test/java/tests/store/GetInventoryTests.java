package tests.store;

import base.BaseApiTest;
import endpoints.StoreEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GetInventoryTests extends BaseApiTest {

    @Test(groups = {"smoke", "regression", "store", "positive"})
    public void verifyThatGetInventoryShouldReturn200() {
        Response response = apiClient.get(StoreEndpoints.GET_INVENTORY);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatGetInventoryShouldReturnInventoryData() {
        Response response = apiClient.get(StoreEndpoints.GET_INVENTORY);
        Object available = response.jsonPath().get("available");
        Object pending = response.jsonPath().get("pending");
        Object sold = response.jsonPath().get("sold");
        
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(available);
        softAssert.assertNotNull(pending);
        softAssert.assertNotNull(sold);
        softAssert.assertAll();
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatGetInventoryShouldReturnResponseWithinTimeLimit() {
        Response response = apiClient.get(StoreEndpoints.GET_INVENTORY);
        long responseTime = response.getTime();
        
        Assert.assertTrue(responseTime < 3000);
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatGetInventoryShouldReturnProperContentType() {
        Response response = apiClient.get(StoreEndpoints.GET_INVENTORY);
        String contentType = response.getContentType();
        
        Assert.assertTrue(contentType.contains("application/json"));
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatGetInventoryShouldReturnIntegerValues() {
        Response response = apiClient.get(StoreEndpoints.GET_INVENTORY);
        int available = response.jsonPath().getInt("available");
        int pending = response.jsonPath().getInt("pending");
        int sold = response.jsonPath().getInt("sold");
        
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(available >= 0);
        softAssert.assertTrue(pending >= 0);
        softAssert.assertTrue(sold >= 0);
        softAssert.assertAll();
    }

    @Test(groups = {"regression", "store", "edge"})
    public void verifyThatGetInventoryShouldReturnNonNegativeValues() {
        Response response = apiClient.get(StoreEndpoints.GET_INVENTORY);
        int available = response.jsonPath().getInt("available");
        int pending = response.jsonPath().getInt("pending");
        int sold = response.jsonPath().getInt("sold");
        
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(available >= 0);
        softAssert.assertTrue(pending >= 0);
        softAssert.assertTrue(sold >= 0);
        softAssert.assertAll();
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatGetInventoryReturnsCompleteObject() {
        Response response = apiClient.get(StoreEndpoints.GET_INVENTORY);
        int available = response.jsonPath().getInt("available");
        
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(available >= 0);
        softAssert.assertAll();
    }
}