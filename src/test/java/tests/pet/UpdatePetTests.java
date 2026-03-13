package tests.pet;

import base.baseApiTest;
import dataFactory.pet.PetDF;
import dto.request.pet.UpdatePetRequest;
import endpoints.petEndpoints;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import validators.responseValidator;

public class UpdatePetTests extends baseApiTest {

    @Test(groups = {"smoke", "regression", "pet", "positive"})
    public void verifyThatUpdatePetShouldReturn200WhenValidPayload() {
        UpdatePetRequest request = PetDF.createValidUpdatePetRequest();
        Response response = apiClient.put(petEndpoints.UPDATE_PET, request);
        responseValidator.validateStatusCode(response, 200);
        responseValidator.validateFieldExists(response, "id");
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatUpdatePetShouldReturn404WhenPetNotFound() {
        UpdatePetRequest request = PetDF.createUpdatePetRequestWithInvalidId();
        Response response = apiClient.put(petEndpoints.UPDATE_PET, request);
        responseValidator.validateStatusCode(response, 404);
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatUpdatePetShouldReturnResponseWithinTimeLimit() {
        UpdatePetRequest request = PetDF.createValidUpdatePetRequest();
        Response response = apiClient.put(petEndpoints.UPDATE_PET, request);
        responseValidator.validateResponseTime(response, 3000);
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatUpdatePetShouldReturnProperContentType() {
        UpdatePetRequest request = PetDF.createValidUpdatePetRequest();
        Response response = apiClient.put(petEndpoints.UPDATE_PET, request);
        responseValidator.validateContentType(response, "application/json");
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatUpdatePetShouldUpdateStatusField() {
        UpdatePetRequest request = PetDF.createValidUpdatePetRequest();
        Response response = apiClient.put(petEndpoints.UPDATE_PET, request);
        responseValidator.validateStatusCode(response, 200);
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatUpdatePetFormShouldReturn200() {
        long petId = PetDF.getValidPetId();
        String name = "updatedname";
        String status = "pending";
        Response response = apiClient.post(petEndpoints.UPDATE_PET_FORM + "?name=" + name + "&status=" + status, "", petId);
        responseValidator.validateStatusCode(response, 200);
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatUpdatePetFormShouldReturn400WhenMissingParameters() {
        long petId = PetDF.getValidPetId();
        Response response = apiClient.post(petEndpoints.UPDATE_PET_FORM + "?name=", "", petId);
        responseValidator.validateStatusCode(response, 400);
    }

    @Test(groups = {"regression", "pet", "edge"})
    public void verifyThatUpdatePetWithEmptyPayloadShouldReturn400() {
        UpdatePetRequest request = PetDF.createUpdatePetRequestWithEmptyPayload();
        Response response = apiClient.put(petEndpoints.UPDATE_PET, request);
        responseValidator.validateStatusCode(response, 400);
    }
}
