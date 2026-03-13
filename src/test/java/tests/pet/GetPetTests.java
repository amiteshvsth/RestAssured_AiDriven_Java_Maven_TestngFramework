package tests.pet;

import base.baseApiTest;
import dataFactory.pet.PetDF;
import endpoints.petEndpoints;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import validators.responseValidator;

public class GetPetTests extends baseApiTest {

    @Test(groups = {"smoke", "regression", "pet", "positive"})
    public void verifyThatGetPetShouldReturn200WhenPetExists() {
        long petId = PetDF.getValidPetId();
        Response response = apiClient.get(petEndpoints.GET_PET, petId);
        responseValidator.validateStatusCode(response, 200);
        responseValidator.validateFieldExists(response, "id");
        responseValidator.validateFieldExists(response, "name");
        responseValidator.validateFieldExists(response, "status");
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatGetPetShouldReturn404WhenPetNotFound() {
        long petId = PetDF.getNonexistentPetId();
        Response response = apiClient.get(petEndpoints.GET_PET, petId);
        responseValidator.validateStatusCode(response, 404);
        responseValidator.validateJsonFieldEquals(response, "code", 404);
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatGetPetShouldReturnResponseWithinTimeLimit() {
        long petId = PetDF.getValidPetId();
        Response response = apiClient.get(petEndpoints.GET_PET, petId);
        responseValidator.validateResponseTime(response, 3000);
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatGetPetShouldReturnProperContentType() {
        long petId = PetDF.getValidPetId();
        Response response = apiClient.get(petEndpoints.GET_PET, petId);
        responseValidator.validateContentType(response, "application/json");
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatGetPetShouldReturnAllRequiredFields() {
        long petId = PetDF.getValidPetId();
        Response response = apiClient.get(petEndpoints.GET_PET, petId);
        responseValidator.validateFieldExists(response, "id");
        responseValidator.validateFieldExists(response, "name");
        responseValidator.validateFieldExists(response, "photoUrls");
        responseValidator.validateFieldExists(response, "tags");
        responseValidator.validateFieldExists(response, "status");
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatGetPetByInvalidIdShouldReturn404() {
        long petId = PetDF.getInvalidPetId();
        Response response = apiClient.get(petEndpoints.GET_PET, petId);
        responseValidator.validateStatusCode(response, 404);
    }

    @Test(groups = {"smoke", "regression", "pet", "positive"})
    public void verifyThatFindPetsByStatusShouldReturn200() {
        String status = PetDF.getAvailableStatus();
        Response response = apiClient.get(petEndpoints.FIND_PETS_BY_STATUS + "?status=" + status);
        responseValidator.validateStatusCode(response, 200);
        responseValidator.validateArrayNotEmpty(response, "");
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatFindPetsByStatusShouldReturnEmptyArrayForInvalidStatus() {
        String status = PetDF.getInvalidStatus();
        Response response = apiClient.get(petEndpoints.FIND_PETS_BY_STATUS + "?status=" + status);
        responseValidator.validateStatusCode(response, 200);
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatFindPetsByTagsShouldReturn200() {
        String tag = PetDF.getValidTag();
        Response response = apiClient.get(petEndpoints.FIND_PETS_BY_TAGS + "?tags=" + tag);
        responseValidator.validateStatusCode(response, 200);
    }
}
