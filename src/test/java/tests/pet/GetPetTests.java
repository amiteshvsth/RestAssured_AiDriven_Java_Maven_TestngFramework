package tests.pet;

import base.BaseApiTest;
import dataFactory.PetDF;
import dto.pet.AddPetRequest;
import dto.pet.GetPetResponse;
import endpoints.PetEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetPetTests extends BaseApiTest {

    @Test(groups = {"smoke", "regression", "pet", "positive"})
    public void verifyThatGetPetShouldReturn200WhenPetExists() {
        AddPetRequest petRequest = PetDF.getData();
        apiClient.post(PetEndpoints.ADD_PET, petRequest);
        
        long petId = petRequest.getId();
        Response response = apiClient.get(PetEndpoints.GET_PET, petId);
        int statusCode = response.getStatusCode();
        GetPetResponse responseDto = response.as(GetPetResponse.class);
        
        Assert.assertEquals(statusCode, 200);
        Assert.assertNotNull(responseDto);
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatGetPetShouldReturn404WhenPetNotFound() {
        long petId = PetDF.getNonexistentId();
        Response response = apiClient.get(PetEndpoints.GET_PET, petId);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 404);
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatGetPetShouldReturn400WhenInvalidId() {
        long petId = PetDF.getInvalidId();
        Response response = apiClient.get(PetEndpoints.GET_PET, petId);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 404);
    }

    @Test(groups = {"smoke", "regression", "pet", "positive"})
    public void verifyThatFindPetsByStatusShouldReturn200() {
        String status = PetDF.getAvailableStatus();
        Response response = apiClient.get(PetEndpoints.FIND_PETS_BY_STATUS + "?status=" + status);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatFindPetsByStatusShouldReturn400ForInvalidStatus() {
        String status = PetDF.getInvalidStatus();
        Response response = apiClient.get(PetEndpoints.FIND_PETS_BY_STATUS + "?status=" + status);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }
}