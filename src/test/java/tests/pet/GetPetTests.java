package tests.pet;

import base.BaseApiTest;
import dataFactory.PetDF;
import endpoints.PetEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GetPetTests extends BaseApiTest {

    @Test(groups = {"smoke", "regression", "pet", "positive"})
    public void verifyThatGetPetShouldReturn200WhenPetExists() {
        long petId = PetDF.getValidId();
        Response response = apiClient.get(PetEndpoints.GET_PET, petId);
        int statusCode = response.getStatusCode();
        Object responseId = response.jsonPath().get("id");
        Object responseName = response.jsonPath().get("name");
        Object responseStatus = response.jsonPath().get("status");
        
        Assert.assertEquals(statusCode, 200);
        
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(responseId);
        softAssert.assertNotNull(responseName);
        softAssert.assertNotNull(responseStatus);
        softAssert.assertAll();
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatGetPetShouldReturn404WhenPetNotFound() {
        long petId = PetDF.getNonexistentId();
        Response response = apiClient.get(PetEndpoints.GET_PET, petId);
        int statusCode = response.getStatusCode();
        int responseCode = response.jsonPath().getInt("code");
        
        Assert.assertEquals(statusCode, 404);
        
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(responseCode, 404);
        softAssert.assertAll();
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatGetPetShouldReturnResponseWithinTimeLimit() {
        long petId = PetDF.getValidId();
        Response response = apiClient.get(PetEndpoints.GET_PET, petId);
        long responseTime = response.getTime();
        
        Assert.assertTrue(responseTime < 3000);
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatGetPetShouldReturnProperContentType() {
        long petId = PetDF.getValidId();
        Response response = apiClient.get(PetEndpoints.GET_PET, petId);
        String contentType = response.getContentType();
        
        Assert.assertTrue(contentType.contains("application/json"));
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatGetPetShouldReturnAllRequiredFields() {
        long petId = PetDF.getValidId();
        Response response = apiClient.get(PetEndpoints.GET_PET, petId);
        Object responseId = response.jsonPath().get("id");
        Object responseName = response.jsonPath().get("name");
        Object responsePhotoUrls = response.jsonPath().get("photoUrls");
        Object responseTags = response.jsonPath().get("tags");
        Object responseStatus = response.jsonPath().get("status");
        
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(responseId);
        softAssert.assertNotNull(responseName);
        softAssert.assertNotNull(responsePhotoUrls);
        softAssert.assertNotNull(responseTags);
        softAssert.assertNotNull(responseStatus);
        softAssert.assertAll();
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatGetPetByInvalidIdShouldReturn404() {
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
        boolean petsListNotEmpty = !response.jsonPath().getList("").isEmpty();
        
        Assert.assertEquals(statusCode, 200);
        
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(petsListNotEmpty);
        softAssert.assertAll();
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatFindPetsByStatusShouldReturnEmptyArrayForInvalidStatus() {
        String status = PetDF.getInvalidStatus();
        Response response = apiClient.get(PetEndpoints.FIND_PETS_BY_STATUS + "?status=" + status);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatFindPetsByTagsShouldReturn200() {
        String tag = PetDF.getValidTag();
        Response response = apiClient.get(PetEndpoints.FIND_PETS_BY_TAGS + "?tags=" + tag);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }
}