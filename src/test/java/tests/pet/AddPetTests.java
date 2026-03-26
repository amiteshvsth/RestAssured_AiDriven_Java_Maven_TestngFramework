package tests.pet;

import base.BaseApiTest;
import dataFactory.PetDF;
import endpoints.PetEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AddPetTests extends BaseApiTest {

    @Test(groups = {"smoke", "regression", "pet", "positive"})
    public void verifyThatAddPetShouldReturn200WhenValidPayload() {
        Response response = apiClient.post(PetEndpoints.ADD_PET, PetDF.getData());
        int statusCode = response.getStatusCode();
        Object responseId = response.jsonPath().get("id");
        Object responseName = response.jsonPath().get("name");
        
        Assert.assertEquals(statusCode, 200);
        
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(responseId);
        softAssert.assertNotNull(responseName);
        softAssert.assertAll();
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatAddPetShouldReturn405WhenInvalidPayload() {
        Response response = apiClient.post(PetEndpoints.ADD_PET, "");
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 405);
    }
}