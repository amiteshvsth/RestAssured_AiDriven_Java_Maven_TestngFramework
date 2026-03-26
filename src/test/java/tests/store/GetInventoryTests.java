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
        Object available = response.jsonPath().get("available");
        Object pending = response.jsonPath().get("pending");
        Object sold = response.jsonPath().get("sold");
        
        Assert.assertEquals(statusCode, 200);
        
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(available);
        softAssert.assertNotNull(pending);
        softAssert.assertNotNull(sold);
        softAssert.assertAll();
    }
}