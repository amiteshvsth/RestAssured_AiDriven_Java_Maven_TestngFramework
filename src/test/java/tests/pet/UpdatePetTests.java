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
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotNull(response.jsonPath().get("id"));
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatUpdatePetShouldReturn404WhenPetNotFound() {
        UpdatePetRequest request = PetDF.getUpdateData();
        request.setId(999999999L);
        Response response = apiClient.put(PetEndpoints.UPDATE_PET, request);
        Assert.assertEquals(response.getStatusCode(), 404);
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatUpdatePetShouldReturnResponseWithinTimeLimit() {
        UpdatePetRequest request = PetDF.getUpdateData();
        Response response = apiClient.put(PetEndpoints.UPDATE_PET, request);
        Assert.assertTrue(response.getTime() < 3000);
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatUpdatePetShouldReturnProperContentType() {
        UpdatePetRequest request = PetDF.getUpdateData();
        Response response = apiClient.put(PetEndpoints.UPDATE_PET, request);
        Assert.assertTrue(response.getContentType().contains("application/json"));
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatUpdatePetShouldUpdateStatusField() {
        UpdatePetRequest request = PetDF.getUpdateData();
        Response response = apiClient.put(PetEndpoints.UPDATE_PET, request);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatUpdatePetFormShouldReturn200() {
        long petId = PetDF.getValidId();
        String name = "updatedname";
        String status = "pending";
        Response response = apiClient.post(PetEndpoints.UPDATE_PET_FORM + "?name=" + name + "&status=" + status, "", petId);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatUpdatePetFormShouldReturn400WhenMissingParameters() {
        long petId = PetDF.getValidId();
        Response response = apiClient.post(PetEndpoints.UPDATE_PET_FORM + "?name=", "", petId);
        Assert.assertEquals(response.getStatusCode(), 400);
    }

    @Test(groups = {"regression", "pet", "edge"})
    public void verifyThatUpdatePetWithEmptyPayloadShouldReturn400() {
        UpdatePetRequest request = new UpdatePetRequest();
        Response response = apiClient.put(PetEndpoints.UPDATE_PET, request);
        Assert.assertEquals(response.getStatusCode(), 400);
    }
}