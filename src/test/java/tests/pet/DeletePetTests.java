package tests.pet;

import base.BaseApiTest;
import dataFactory.pet.PetDF;
import endpoints.PetEndpoints;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import validators.ResponseValidator;

public class DeletePetTests extends BaseApiTest {

    @Test(groups = {"smoke", "regression", "pet", "positive"})
    public void verifyThatDeletePetShouldReturn200WhenPetExists() {
        long petId = PetDF.getValidPetId();
        Response response = apiClient.delete(PetEndpoints.DELETE_PET, petId);
        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateJsonFieldEquals(response, "code", 200);
        ResponseValidator.validateFieldExists(response, "message");
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatDeletePetShouldReturn404WhenPetNotFound() {
        long petId = PetDF.getNonexistentPetId();
        Response response = apiClient.delete(PetEndpoints.DELETE_PET, petId);
        ResponseValidator.validateStatusCode(response, 404);
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatDeletePetShouldReturnResponseWithinTimeLimit() {
        long petId = PetDF.getValidPetId();
        Response response = apiClient.delete(PetEndpoints.DELETE_PET, petId);
        ResponseValidator.validateResponseTime(response, 3000);
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatDeletePetShouldReturnProperContentType() {
        long petId = PetDF.getValidPetId();
        Response response = apiClient.delete(PetEndpoints.DELETE_PET, petId);
        ResponseValidator.validateContentType(response, "application/json");
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatDeletePetWithInvalidIdShouldReturn404() {
        long petId = PetDF.getInvalidPetId();
        Response response = apiClient.delete(PetEndpoints.DELETE_PET, petId);
        ResponseValidator.validateStatusCode(response, 404);
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatDeletePetShouldReturnSuccessMessage() {
        long petId = PetDF.getValidPetId();
        Response response = apiClient.delete(PetEndpoints.DELETE_PET, petId);
        ResponseValidator.validateFieldExists(response, "message");
    }
}
