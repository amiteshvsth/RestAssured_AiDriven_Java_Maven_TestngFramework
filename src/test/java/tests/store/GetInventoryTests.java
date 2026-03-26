package tests.store;

import base.BaseApiTest;
import endpoints.StoreEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Map;

public class GetInventoryTests extends BaseApiTest {

    @Test(groups = {"smoke", "regression", "store", "positive"})
    public void verifyThatGetInventoryShouldReturn200() {
        Response response = apiClient.get(StoreEndpoints.GET_INVENTORY);
        int statusCode = response.getStatusCode();
        Map<String, Integer> inventory = response.jsonPath().getMap("");
        
        Assert.assertEquals(statusCode, 200);
        
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(inventory.get("available"));
        softAssert.assertNotNull(inventory.get("pending"));
        softAssert.assertNotNull(inventory.get("sold"));
        softAssert.assertAll();
    }
}