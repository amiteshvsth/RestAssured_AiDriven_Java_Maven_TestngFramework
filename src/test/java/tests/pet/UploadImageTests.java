package tests.pet;

import base.baseApiTest;
import dataFactory.pet.PetDF;
import endpoints.petEndpoints;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import validators.responseValidator;

public class UploadImageTests extends baseApiTest {

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatUploadImageShouldReturn200WhenValidPetId() {
        long petId = PetDF.getValidPetId();
        String metadata = "test metadata";
        Response response = apiClient.post(petEndpoints.UPLOAD_IMAGE, metadata, petId);
        responseValidator.validateStatusCode(response, 200);
        responseValidator.validateFieldExists(response, "code");
        responseValidator.validateFieldExists(response, "message");
        responseValidator.validateFieldExists(response, "type");
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatUploadImageShouldReturn404WhenPetNotFound() {
        long petId = PetDF.getNonexistentPetId();
        String metadata = "test metadata";
        Response response = apiClient.post(petEndpoints.UPLOAD_IMAGE, metadata, petId);
        responseValidator.validateStatusCode(response, 404);
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatUploadImageShouldReturnResponseWithinTimeLimit() {
        long petId = PetDF.getValidPetId();
        String metadata = "test metadata";
        Response response = apiClient.post(petEndpoints.UPLOAD_IMAGE, metadata, petId);
        responseValidator.validateResponseTime(response, 5000);
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatUploadImageShouldReturnProperContentType() {
        long petId = PetDF.getValidPetId();
        String metadata = "test metadata";
        Response response = apiClient.post(petEndpoints.UPLOAD_IMAGE, metadata, petId);
        responseValidator.validateContentType(response, "application/json");
    }

    @Test(groups = {"regression", "pet", "edge"})
    public void verifyThatUploadImageWithEmptyMetadataShouldReturn200() {
        long petId = PetDF.getValidPetId();
        Response response = apiClient.post(petEndpoints.UPLOAD_IMAGE, "", petId);
        responseValidator.validateStatusCode(response, 200);
    }
}
