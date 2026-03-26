package tests.pet;

import base.BaseApiTest;
import dataFactory.PetDF;
import endpoints.PetEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class UploadImageTests extends BaseApiTest {

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatUploadImageShouldReturn200WhenValidPetId() {
        long petId = PetDF.getValidId();
        String metadata = "test metadata";
        Response response = apiClient.post(PetEndpoints.UPLOAD_IMAGE, metadata, petId);
        int statusCode = response.getStatusCode();
        Object responseCode = response.jsonPath().get("code");
        Object responseMessage = response.jsonPath().get("message");
        Object responseType = response.jsonPath().get("type");
        
        Assert.assertEquals(statusCode, 200);
        
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(responseCode);
        softAssert.assertNotNull(responseMessage);
        softAssert.assertNotNull(responseType);
        softAssert.assertAll();
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatUploadImageShouldReturn404WhenPetNotFound() {
        long petId = PetDF.getNonexistentId();
        String metadata = "test metadata";
        Response response = apiClient.post(PetEndpoints.UPLOAD_IMAGE, metadata, petId);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 404);
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatUploadImageShouldReturnResponseWithinTimeLimit() {
        long petId = PetDF.getValidId();
        String metadata = "test metadata";
        Response response = apiClient.post(PetEndpoints.UPLOAD_IMAGE, metadata, petId);
        long responseTime = response.getTime();
        
        Assert.assertTrue(responseTime < 5000);
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatUploadImageShouldReturnProperContentType() {
        long petId = PetDF.getValidId();
        String metadata = "test metadata";
        Response response = apiClient.post(PetEndpoints.UPLOAD_IMAGE, metadata, petId);
        String contentType = response.getContentType();
        
        Assert.assertTrue(contentType.contains("application/json"));
    }

    @Test(groups = {"regression", "pet", "edge"})
    public void verifyThatUploadImageWithEmptyMetadataShouldReturn200() {
        long petId = PetDF.getValidId();
        Response response = apiClient.post(PetEndpoints.UPLOAD_IMAGE, "", petId);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }
}