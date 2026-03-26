package tests.pet;

import base.BaseApiTest;
import dataFactory.PetDF;
import endpoints.PetEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DeletePetTests extends BaseApiTest {

    @Test(groups = {"smoke", "regression", "pet", "positive"})
    public void verifyThatDeletePetShouldReturn200WhenPetExists() {
        long petId = PetDF.getValidId();
        Response response = apiClient.delete(PetEndpoints.DELETE_PET, petId);
        int statusCode = response.getStatusCode();
        int responseCode = response.jsonPath().getInt("code");
        Object responseMessage = response.jsonPath().get("message");
        
        Assert.assertEquals(statusCode, 200);
        
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(responseCode, 200);
        softAssert.assertNotNull(responseMessage);
        softAssert.assertAll();
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatDeletePetShouldReturn404WhenPetNotFound() {
        long petId = PetDF.getNonexistentId();
        Response response = apiClient.delete(PetEndpoints.DELETE_PET, petId);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 404);
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatDeletePetShouldReturn400WhenInvalidId() {
        long petId = PetDF.getInvalidId();
        Response response = apiClient.delete(PetEndpoints.DELETE_PET, petId);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 400);
    }
}