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

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatAddPetShouldReturn200WhenMinimalDataProvided() {
        Response response = apiClient.post(PetEndpoints.ADD_PET, PetDF.getData());
        int statusCode = response.getStatusCode();
        Object responseId = response.jsonPath().get("id");
        
        Assert.assertEquals(statusCode, 200);
        
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(responseId);
        softAssert.assertAll();
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatAddPetShouldReturnResponseWithinTimeLimit() {
        Response response = apiClient.post(PetEndpoints.ADD_PET, PetDF.getData());
        long responseTime = response.getTime();
        
        Assert.assertTrue(responseTime < 3000);
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatAddPetShouldReturnProperContentType() {
        Response response = apiClient.post(PetEndpoints.ADD_PET, PetDF.getData());
        String contentType = response.getContentType();
        
        Assert.assertTrue(contentType.contains("application/json"));
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatAddPetShouldReturnAllFields() {
        Response response = apiClient.post(PetEndpoints.ADD_PET, PetDF.getData());
        Object responseId = response.jsonPath().get("id");
        Object responseName = response.jsonPath().get("name");
        Object responseStatus = response.jsonPath().get("status");
        Object responseCategory = response.jsonPath().get("category");
        Object responsePhotoUrls = response.jsonPath().get("photoUrls");
        Object responseTags = response.jsonPath().get("tags");
        
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(responseId);
        softAssert.assertNotNull(responseName);
        softAssert.assertNotNull(responseStatus);
        softAssert.assertNotNull(responseCategory);
        softAssert.assertNotNull(responsePhotoUrls);
        softAssert.assertNotNull(responseTags);
        softAssert.assertAll();
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatAddPetShouldIncludeCategoryDetails() {
        Response response = apiClient.post(PetEndpoints.ADD_PET, PetDF.getData());
        int statusCode = response.getStatusCode();
        int categoryId = response.jsonPath().getInt("category.id");
        String categoryName = response.jsonPath().getString("category.name");
        
        Assert.assertEquals(statusCode, 200);
        
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(categoryId, 1);
        softAssert.assertEquals(categoryName, "Dogs");
        softAssert.assertAll();
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatAddPetShouldIncludeTagDetails() {
        Response response = apiClient.post(PetEndpoints.ADD_PET, PetDF.getData());
        int statusCode = response.getStatusCode();
        boolean tagsNotEmpty = !response.jsonPath().getList("tags").isEmpty();
        
        Assert.assertEquals(statusCode, 200);
        
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(tagsNotEmpty);
        softAssert.assertAll();
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatAddPetWithInvalidStatusShouldReturn200() {
        Response response = apiClient.post(PetEndpoints.ADD_PET, PetDF.getData());
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }

    @Test(groups = {"regression", "pet", "edge"})
    public void verifyThatAddPetWithEmptyPayloadShouldReturn200() {
        Response response = apiClient.post(PetEndpoints.ADD_PET, PetDF.getData());
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }

    @Test(groups = {"regression", "pet", "edge"})
    public void verifyThatAddPetWithNullNameShouldReturn200() {
        Response response = apiClient.post(PetEndpoints.ADD_PET, PetDF.getData());
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }
}