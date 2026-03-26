package tests.pet;

import base.BaseApiTest;
import dataFactory.PetDF;
import dto.pet.AddPetRequest;
import endpoints.PetEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeletePetTests extends BaseApiTest {

    @Test(groups = {"smoke", "regression", "pet", "positive"})
    public void verifyThatDeletePetShouldReturn200WhenPetExists() {
        AddPetRequest petRequest = PetDF.getData();
        apiClient.post(PetEndpoints.ADD_PET, petRequest);
        
        long petId = petRequest.getId();
        Response response = apiClient.delete(PetEndpoints.DELETE_PET, petId);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
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
        
        Assert.assertEquals(statusCode, 404);
    }
}