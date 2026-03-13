package tests.pet;

import base.baseApiTest;
import dataFactory.pet.PetDF;
import dto.request.pet.AddPetRequest;
import endpoints.petEndpoints;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import validators.responseValidator;

public class AddPetTests extends baseApiTest {

    @Test(groups = {"smoke", "regression", "pet", "positive"})
    public void verifyThatAddPetShouldReturn200WhenValidPayload() {
        AddPetRequest request = PetDF.createValidAddPetRequest();
        Response response = apiClient.post(petEndpoints.ADD_PET, request);
        responseValidator.validateStatusCode(response, 200);
        responseValidator.validateFieldExists(response, "id");
        responseValidator.validateFieldExists(response, "name");
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatAddPetShouldReturn200WhenMinimalDataProvided() {
        AddPetRequest request = PetDF.createAddPetRequestWithMinimalData();
        Response response = apiClient.post(petEndpoints.ADD_PET, request);
        responseValidator.validateStatusCode(response, 200);
        responseValidator.validateFieldExists(response, "id");
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatAddPetShouldReturnResponseWithinTimeLimit() {
        AddPetRequest request = PetDF.createValidAddPetRequest();
        Response response = apiClient.post(petEndpoints.ADD_PET, request);
        responseValidator.validateResponseTime(response, 3000);
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatAddPetShouldReturnProperContentType() {
        AddPetRequest request = PetDF.createValidAddPetRequest();
        Response response = apiClient.post(petEndpoints.ADD_PET, request);
        responseValidator.validateContentType(response, "application/json");
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatAddPetShouldReturnAllFields() {
        AddPetRequest request = PetDF.createValidAddPetRequest();
        Response response = apiClient.post(petEndpoints.ADD_PET, request);
        responseValidator.validateFieldExists(response, "id");
        responseValidator.validateFieldExists(response, "name");
        responseValidator.validateFieldExists(response, "status");
        responseValidator.validateFieldExists(response, "category");
        responseValidator.validateFieldExists(response, "photoUrls");
        responseValidator.validateFieldExists(response, "tags");
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatAddPetShouldIncludeCategoryDetails() {
        AddPetRequest request = PetDF.createValidAddPetRequest();
        Response response = apiClient.post(petEndpoints.ADD_PET, request);
        responseValidator.validateJsonFieldEquals(response, "category.id", 1);
        responseValidator.validateJsonFieldEquals(response, "category.name", "Dogs");
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatAddPetShouldIncludeTagDetails() {
        AddPetRequest request = PetDF.createValidAddPetRequest();
        Response response = apiClient.post(petEndpoints.ADD_PET, request);
        responseValidator.validateArrayNotEmpty(response, "tags");
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatAddPetWithInvalidStatusShouldReturn200() {
        AddPetRequest request = PetDF.createAddPetRequestWithInvalidStatus();
        Response response = apiClient.post(petEndpoints.ADD_PET, request);
        responseValidator.validateStatusCode(response, 200);
    }

    @Test(groups = {"regression", "pet", "edge"})
    public void verifyThatAddPetWithEmptyPayloadShouldReturn200() {
        AddPetRequest request = PetDF.createAddPetRequestWithEmptyPayload();
        Response response = apiClient.post(petEndpoints.ADD_PET, request);
        responseValidator.validateStatusCode(response, 200);
    }

    @Test(groups = {"regression", "pet", "edge"})
    public void verifyThatAddPetWithNullNameShouldReturn200() {
        AddPetRequest request = PetDF.createAddPetRequestWithNullName();
        Response response = apiClient.post(petEndpoints.ADD_PET, request);
        responseValidator.validateStatusCode(response, 200);
    }
}
