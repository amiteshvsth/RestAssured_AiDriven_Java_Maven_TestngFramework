package tests.pet;

import base.BaseApiTest;
import dataFactory.PetDF;
import endpoints.PetEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UploadImageTests extends BaseApiTest {

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatUploadImageShouldReturn200WhenValidPetId() {
        long petId = PetDF.getValidId();
        String metadata = "test metadata";
        Response response = apiClient.post(PetEndpoints.UPLOAD_IMAGE, metadata, petId);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotNull(response.jsonPath().get("code"));
        Assert.assertNotNull(response.jsonPath().get("message"));
        Assert.assertNotNull(response.jsonPath().get("type"));
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatUploadImageShouldReturn404WhenPetNotFound() {
        long petId = PetDF.getNonexistentId();
        String metadata = "test metadata";
        Response response = apiClient.post(PetEndpoints.UPLOAD_IMAGE, metadata, petId);
        Assert.assertEquals(response.getStatusCode(), 404);
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatUploadImageShouldReturnResponseWithinTimeLimit() {
        long petId = PetDF.getValidId();
        String metadata = "test metadata";
        Response response = apiClient.post(PetEndpoints.UPLOAD_IMAGE, metadata, petId);
        Assert.assertTrue(response.getTime() < 5000);
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatUploadImageShouldReturnProperContentType() {
        long petId = PetDF.getValidId();
        String metadata = "test metadata";
        Response response = apiClient.post(PetEndpoints.UPLOAD_IMAGE, metadata, petId);
        Assert.assertTrue(response.getContentType().contains("application/json"));
    }

    @Test(groups = {"regression", "pet", "edge"})
    public void verifyThatUploadImageWithEmptyMetadataShouldReturn200() {
        long petId = PetDF.getValidId();
        Response response = apiClient.post(PetEndpoints.UPLOAD_IMAGE, "", petId);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}