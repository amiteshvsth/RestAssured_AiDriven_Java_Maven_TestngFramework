package tests.pet;

import base.BaseApiTest;
import dataFactory.PetDF;
import endpoints.PetEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetPetTests extends BaseApiTest {

    @Test(groups = {"smoke", "regression", "pet", "positive"})
    public void verifyThatGetPetShouldReturn200WhenPetExists() {
        long petId = PetDF.getValidId();
        Response response = apiClient.get(PetEndpoints.GET_PET, petId);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotNull(response.jsonPath().get("id"));
        Assert.assertNotNull(response.jsonPath().get("name"));
        Assert.assertNotNull(response.jsonPath().get("status"));
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatGetPetShouldReturn404WhenPetNotFound() {
        long petId = PetDF.getNonexistentId();
        Response response = apiClient.get(PetEndpoints.GET_PET, petId);
        Assert.assertEquals(response.getStatusCode(), 404);
        Assert.assertEquals(response.jsonPath().getInt("code"), 404);
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatGetPetShouldReturnResponseWithinTimeLimit() {
        long petId = PetDF.getValidId();
        Response response = apiClient.get(PetEndpoints.GET_PET, petId);
        Assert.assertTrue(response.getTime() < 3000);
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatGetPetShouldReturnProperContentType() {
        long petId = PetDF.getValidId();
        Response response = apiClient.get(PetEndpoints.GET_PET, petId);
        Assert.assertTrue(response.getContentType().contains("application/json"));
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatGetPetShouldReturnAllRequiredFields() {
        long petId = PetDF.getValidId();
        Response response = apiClient.get(PetEndpoints.GET_PET, petId);
        Assert.assertNotNull(response.jsonPath().get("id"));
        Assert.assertNotNull(response.jsonPath().get("name"));
        Assert.assertNotNull(response.jsonPath().get("photoUrls"));
        Assert.assertNotNull(response.jsonPath().get("tags"));
        Assert.assertNotNull(response.jsonPath().get("status"));
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatGetPetByInvalidIdShouldReturn404() {
        long petId = PetDF.getInvalidId();
        Response response = apiClient.get(PetEndpoints.GET_PET, petId);
        Assert.assertEquals(response.getStatusCode(), 404);
    }

    @Test(groups = {"smoke", "regression", "pet", "positive"})
    public void verifyThatFindPetsByStatusShouldReturn200() {
        String status = PetDF.getAvailableStatus();
        Response response = apiClient.get(PetEndpoints.FIND_PETS_BY_STATUS + "?status=" + status);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertFalse(response.jsonPath().getList("").isEmpty());
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatFindPetsByStatusShouldReturnEmptyArrayForInvalidStatus() {
        String status = PetDF.getInvalidStatus();
        Response response = apiClient.get(PetEndpoints.FIND_PETS_BY_STATUS + "?status=" + status);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatFindPetsByTagsShouldReturn200() {
        String tag = PetDF.getValidTag();
        Response response = apiClient.get(PetEndpoints.FIND_PETS_BY_TAGS + "?tags=" + tag);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}