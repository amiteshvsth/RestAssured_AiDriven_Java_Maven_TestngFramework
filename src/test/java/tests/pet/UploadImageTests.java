package tests.pet;

import base.BaseApiTest;
import dataFactory.PetDF;
import endpoints.PetEndpoints;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import validators.ResponseValidator;

public class UploadImageTests extends BaseApiTest {

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatUploadImageShouldReturn200WhenValidPetId() {
        long petId = PetDF.getValidId();
        String metadata = "test metadata";
        Response response = apiClient.post(PetEndpoints.UPLOAD_IMAGE, metadata, petId);
        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateFieldExists(response, "code");
        ResponseValidator.validateFieldExists(response, "message");
        ResponseValidator.validateFieldExists(response, "type");
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatUploadImageShouldReturn404WhenPetNotFound() {
        long petId = PetDF.getNonexistentId();
        String metadata = "test metadata";
        Response response = apiClient.post(PetEndpoints.UPLOAD_IMAGE, metadata, petId);
        ResponseValidator.validateStatusCode(response, 404);
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatUploadImageShouldReturnResponseWithinTimeLimit() {
        long petId = PetDF.getValidId();
        String metadata = "test metadata";
        Response response = apiClient.post(PetEndpoints.UPLOAD_IMAGE, metadata, petId);
        ResponseValidator.validateResponseTime(response, 5000);
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatUploadImageShouldReturnProperContentType() {
        long petId = PetDF.getValidId();
        String metadata = "test metadata";
        Response response = apiClient.post(PetEndpoints.UPLOAD_IMAGE, metadata, petId);
        ResponseValidator.validateContentType(response, "application/json");
    }

    @Test(groups = {"regression", "pet", "edge"})
    public void verifyThatUploadImageWithEmptyMetadataShouldReturn200() {
        long petId = PetDF.getValidId();
        Response response = apiClient.post(PetEndpoints.UPLOAD_IMAGE, "", petId);
        ResponseValidator.validateStatusCode(response, 200);
    }
}
