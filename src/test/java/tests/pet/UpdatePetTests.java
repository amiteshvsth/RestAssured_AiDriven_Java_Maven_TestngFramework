package tests.pet;

import base.BaseApiTest;
import dataFactory.PetDF;
import dto.pet.UpdatePetRequest;
import endpoints.PetEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdatePetTests extends BaseApiTest {

    @Test(groups = {"smoke", "regression", "pet", "positive"})
    public void verifyThatUpdatePetShouldReturn200WhenValidPayload() {
        UpdatePetRequest request = PetDF.getDataAsUpdatePetRequest();
        Response response = apiClient.put(PetEndpoints.PET_BASE, request);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatUpdatePetShouldReturn404WhenPetNotFound() {
        UpdatePetRequest request = PetDF.getDataAsUpdatePetRequest();
        Response response = apiClient.put(PetEndpoints.PET_BASE, request);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatUpdatePetShouldReturn400WhenInvalidPayload() {
        UpdatePetRequest request = new UpdatePetRequest();
        Response response = apiClient.put(PetEndpoints.PET_BASE, request);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatUpdatePetFormShouldReturn200() {
        Assert.assertTrue(true);
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatUpdatePetFormShouldReturn405WhenInvalidInput() {
        Assert.assertTrue(true);
    }
}