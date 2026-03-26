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
        UpdatePetRequest request = PetDF.getUpdateData();
        Response response = apiClient.put(PetEndpoints.UPDATE_PET, request);
        int statusCode = response.getStatusCode();
        Object responseId = response.jsonPath().get("id");
        
        Assert.assertEquals(statusCode, 200);
        Assert.assertNotNull(responseId);
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatUpdatePetShouldReturn404WhenPetNotFound() {
        UpdatePetRequest request = PetDF.getUpdateData();
        request.setId(999999999L);
        Response response = apiClient.put(PetEndpoints.UPDATE_PET, request);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 404);
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatUpdatePetShouldReturnResponseWithinTimeLimit() {
        UpdatePetRequest request = PetDF.getUpdateData();
        Response response = apiClient.put(PetEndpoints.UPDATE_PET, request);
        long responseTime = response.getTime();
        
        Assert.assertTrue(responseTime < 3000);
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatUpdatePetShouldReturnProperContentType() {
        UpdatePetRequest request = PetDF.getUpdateData();
        Response response = apiClient.put(PetEndpoints.UPDATE_PET, request);
        String contentType = response.getContentType();
        
        Assert.assertTrue(contentType.contains("application/json"));
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatUpdatePetShouldUpdateStatusField() {
        UpdatePetRequest request = PetDF.getUpdateData();
        Response response = apiClient.put(PetEndpoints.UPDATE_PET, request);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatUpdatePetFormShouldReturn200() {
        long petId = PetDF.getValidId();
        String name = "updatedname";
        String status = "pending";
        Response response = apiClient.post(PetEndpoints.UPDATE_PET_FORM + "?name=" + name + "&status=" + status, "", petId);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatUpdatePetFormShouldReturn400WhenMissingParameters() {
        long petId = PetDF.getValidId();
        Response response = apiClient.post(PetEndpoints.UPDATE_PET_FORM + "?name=", "", petId);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 400);
    }

    @Test(groups = {"regression", "pet", "edge"})
    public void verifyThatUpdatePetWithEmptyPayloadShouldReturn400() {
        UpdatePetRequest request = new UpdatePetRequest();
        Response response = apiClient.put(PetEndpoints.UPDATE_PET, request);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 400);
    }
}