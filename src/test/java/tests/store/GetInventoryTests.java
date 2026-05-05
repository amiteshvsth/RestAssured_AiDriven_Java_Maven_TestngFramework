package tests.store;

import base.BaseApiTest;
import utils.ApiEndPoints;
import utils.ApiHelpers;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetInventoryTests extends BaseApiTest {
    private ApiHelpers helpers = new ApiHelpers();

    @Test(groups = {"smoke", "regression", "store", "positive"})
    public void verifyThatGetInventoryShouldReturn200() {
        Response response = given().spec(helpers.requestSpecificationBaseURI()).when().
                get(ApiEndPoints.GET_INVENTORY);
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