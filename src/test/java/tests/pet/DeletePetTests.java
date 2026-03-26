package tests.pet;

import base.BaseApiTest;
import dataFactory.PetDF;
import endpoints.PetEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeletePetTests extends BaseApiTest {

    @Test(groups = {"smoke", "regression", "pet", "positive"})
    public void verifyThatDeletePetShouldReturn200WhenPetExists() {
        long petId = PetDF.getValidId();
        Response response = apiClient.delete(PetEndpoints.DELETE_PET, petId);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("code"), 200);
        Assert.assertNotNull(response.jsonPath().get("message"));
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatDeletePetShouldReturn404WhenPetNotFound() {
        long petId = PetDF.getNonexistentId();
        Response response = apiClient.delete(PetEndpoints.DELETE_PET, petId);
        Assert.assertEquals(response.getStatusCode(), 404);
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatDeletePetShouldReturnResponseWithinTimeLimit() {
        long petId = PetDF.getValidId();
        Response response = apiClient.delete(PetEndpoints.DELETE_PET, petId);
        Assert.assertTrue(response.getTime() < 3000);
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatDeletePetShouldReturnProperContentType() {
        long petId = PetDF.getValidId();
        Response response = apiClient.delete(PetEndpoints.DELETE_PET, petId);
        Assert.assertTrue(response.getContentType().contains("application/json"));
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatDeletePetWithInvalidIdShouldReturn404() {
        long petId = PetDF.getInvalidId();
        Response response = apiClient.delete(PetEndpoints.DELETE_PET, petId);
        Assert.assertEquals(response.getStatusCode(), 404);
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatDeletePetShouldReturnSuccessMessage() {
        long petId = PetDF.getValidId();
        Response response = apiClient.delete(PetEndpoints.DELETE_PET, petId);
        Assert.assertNotNull(response.jsonPath().get("message"));
    }
}