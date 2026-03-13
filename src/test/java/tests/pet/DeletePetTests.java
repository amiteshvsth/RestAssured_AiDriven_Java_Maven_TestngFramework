package tests.pet;

import base.baseApiTest;
import dataFactory.pet.PetDF;
import endpoints.petEndpoints;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import validators.responseValidator;

public class DeletePetTests extends baseApiTest {

    @Test(groups = {"smoke", "regression", "pet", "positive"})
    public void verifyThatDeletePetShouldReturn200WhenPetExists() {
        long petId = PetDF.getValidPetId();
        Response response = apiClient.delete(petEndpoints.DELETE_PET, petId);
        responseValidator.validateStatusCode(response, 200);
        responseValidator.validateJsonFieldEquals(response, "code", 200);
        responseValidator.validateFieldExists(response, "message");
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatDeletePetShouldReturn404WhenPetNotFound() {
        long petId = PetDF.getNonexistentPetId();
        Response response = apiClient.delete(petEndpoints.DELETE_PET, petId);
        responseValidator.validateStatusCode(response, 404);
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatDeletePetShouldReturnResponseWithinTimeLimit() {
        long petId = PetDF.getValidPetId();
        Response response = apiClient.delete(petEndpoints.DELETE_PET, petId);
        responseValidator.validateResponseTime(response, 3000);
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatDeletePetShouldReturnProperContentType() {
        long petId = PetDF.getValidPetId();
        Response response = apiClient.delete(petEndpoints.DELETE_PET, petId);
        responseValidator.validateContentType(response, "application/json");
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatDeletePetWithInvalidIdShouldReturn404() {
        long petId = PetDF.getInvalidPetId();
        Response response = apiClient.delete(petEndpoints.DELETE_PET, petId);
        responseValidator.validateStatusCode(response, 404);
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatDeletePetShouldReturnSuccessMessage() {
        long petId = PetDF.getValidPetId();
        Response response = apiClient.delete(petEndpoints.DELETE_PET, petId);
        responseValidator.validateFieldExists(response, "message");
    }
}
