package tests.pet;

import base.BaseApiTest;
import dataFactory.PetDF;
import endpoints.PetEndpoints;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import validators.ResponseValidator;

public class GetPetTests extends BaseApiTest {

    @Test(groups = {"smoke", "regression", "pet", "positive"})
    public void verifyThatGetPetShouldReturn200WhenPetExists() {
        long petId = PetDF.getValidId();
        Response response = apiClient.get(PetEndpoints.GET_PET, petId);
        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateFieldExists(response, "id");
        ResponseValidator.validateFieldExists(response, "name");
        ResponseValidator.validateFieldExists(response, "status");
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatGetPetShouldReturn404WhenPetNotFound() {
        long petId = PetDF.getNonexistentId();
        Response response = apiClient.get(PetEndpoints.GET_PET, petId);
        ResponseValidator.validateStatusCode(response, 404);
        ResponseValidator.validateJsonFieldEquals(response, "code", 404);
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatGetPetShouldReturnResponseWithinTimeLimit() {
        long petId = PetDF.getValidId();
        Response response = apiClient.get(PetEndpoints.GET_PET, petId);
        ResponseValidator.validateResponseTime(response, 3000);
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatGetPetShouldReturnProperContentType() {
        long petId = PetDF.getValidId();
        Response response = apiClient.get(PetEndpoints.GET_PET, petId);
        ResponseValidator.validateContentType(response, "application/json");
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatGetPetShouldReturnAllRequiredFields() {
        long petId = PetDF.getValidId();
        Response response = apiClient.get(PetEndpoints.GET_PET, petId);
        ResponseValidator.validateFieldExists(response, "id");
        ResponseValidator.validateFieldExists(response, "name");
        ResponseValidator.validateFieldExists(response, "photoUrls");
        ResponseValidator.validateFieldExists(response, "tags");
        ResponseValidator.validateFieldExists(response, "status");
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatGetPetByInvalidIdShouldReturn404() {
        long petId = PetDF.getInvalidId();
        Response response = apiClient.get(PetEndpoints.GET_PET, petId);
        ResponseValidator.validateStatusCode(response, 404);
    }

    @Test(groups = {"smoke", "regression", "pet", "positive"})
    public void verifyThatFindPetsByStatusShouldReturn200() {
        String status = PetDF.getAvailableStatus();
        Response response = apiClient.get(PetEndpoints.FIND_PETS_BY_STATUS + "?status=" + status);
        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateArrayNotEmpty(response, "");
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatFindPetsByStatusShouldReturnEmptyArrayForInvalidStatus() {
        String status = PetDF.getInvalidStatus();
        Response response = apiClient.get(PetEndpoints.FIND_PETS_BY_STATUS + "?status=" + status);
        ResponseValidator.validateStatusCode(response, 200);
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatFindPetsByTagsShouldReturn200() {
        String tag = PetDF.getValidTag();
        Response response = apiClient.get(PetEndpoints.FIND_PETS_BY_TAGS + "?tags=" + tag);
        ResponseValidator.validateStatusCode(response, 200);
    }
}
