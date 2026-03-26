package tests.store;

import base.BaseApiTest;
import endpoints.StoreEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetInventoryTests extends BaseApiTest {

    @Test(groups = {"smoke", "regression", "store", "positive"})
    public void verifyThatGetInventoryShouldReturn200() {
        Response response = apiClient.get(StoreEndpoints.GET_INVENTORY);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatGetInventoryShouldReturnInventoryData() {
        Response response = apiClient.get(StoreEndpoints.GET_INVENTORY);
        Assert.assertNotNull(response.jsonPath().get("available"));
        Assert.assertNotNull(response.jsonPath().get("pending"));
        Assert.assertNotNull(response.jsonPath().get("sold"));
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatGetInventoryShouldReturnResponseWithinTimeLimit() {
        Response response = apiClient.get(StoreEndpoints.GET_INVENTORY);
        Assert.assertTrue(response.getTime() < 3000);
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatGetInventoryShouldReturnProperContentType() {
        Response response = apiClient.get(StoreEndpoints.GET_INVENTORY);
        Assert.assertTrue(response.getContentType().contains("application/json"));
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatGetInventoryShouldReturnIntegerValues() {
        Response response = apiClient.get(StoreEndpoints.GET_INVENTORY);
        Assert.assertTrue(response.jsonPath().getInt("available") >= 0);
        Assert.assertTrue(response.jsonPath().getInt("pending") >= 0);
        Assert.assertTrue(response.jsonPath().getInt("sold") >= 0);
    }

    @Test(groups = {"regression", "store", "edge"})
    public void verifyThatGetInventoryShouldReturnNonNegativeValues() {
        Response response = apiClient.get(StoreEndpoints.GET_INVENTORY);
        Assert.assertTrue(response.jsonPath().getInt("available") >= 0);
        Assert.assertTrue(response.jsonPath().getInt("pending") >= 0);
        Assert.assertTrue(response.jsonPath().getInt("sold") >= 0);
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatGetInventoryReturnsCompleteObject() {
        Response response = apiClient.get(StoreEndpoints.GET_INVENTORY);
        Assert.assertTrue(response.jsonPath().getInt("available") >= 0);
    }
}