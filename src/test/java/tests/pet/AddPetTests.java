package tests.pet;

import base.BaseApiTest;
import dataFactory.pet.PetDF;
import dto.request.pet.AddPetRequest;
import endpoints.PetEndpoints;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import validators.ResponseValidator;

public class AddPetTests extends BaseApiTest {

    @Test(groups = {"smoke", "regression", "pet", "positive"})
    public void verifyThatAddPetShouldReturn200WhenValidPayload() {
        AddPetRequest request = PetDF.createValidAddPetRequest();
        Response response = apiClient.post(PetEndpoints.ADD_PET, request);
        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateFieldExists(response, "id");
        ResponseValidator.validateFieldExists(response, "name");
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatAddPetShouldReturn200WhenMinimalDataProvided() {
        AddPetRequest request = PetDF.createAddPetRequestWithMinimalData();
        Response response = apiClient.post(PetEndpoints.ADD_PET, request);
        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateFieldExists(response, "id");
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatAddPetShouldReturnResponseWithinTimeLimit() {
        AddPetRequest request = PetDF.createValidAddPetRequest();
        Response response = apiClient.post(PetEndpoints.ADD_PET, request);
        ResponseValidator.validateResponseTime(response, 3000);
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatAddPetShouldReturnProperContentType() {
        AddPetRequest request = PetDF.createValidAddPetRequest();
        Response response = apiClient.post(PetEndpoints.ADD_PET, request);
        ResponseValidator.validateContentType(response, "application/json");
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatAddPetShouldReturnAllFields() {
        AddPetRequest request = PetDF.createValidAddPetRequest();
        Response response = apiClient.post(PetEndpoints.ADD_PET, request);
        ResponseValidator.validateFieldExists(response, "id");
        ResponseValidator.validateFieldExists(response, "name");
        ResponseValidator.validateFieldExists(response, "status");
        ResponseValidator.validateFieldExists(response, "category");
        ResponseValidator.validateFieldExists(response, "photoUrls");
        ResponseValidator.validateFieldExists(response, "tags");
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatAddPetShouldIncludeCategoryDetails() {
        AddPetRequest request = PetDF.createValidAddPetRequest();
        Response response = apiClient.post(PetEndpoints.ADD_PET, request);
        ResponseValidator.validateJsonFieldEquals(response, "category.id", 1);
        ResponseValidator.validateJsonFieldEquals(response, "category.name", "Dogs");
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatAddPetShouldIncludeTagDetails() {
        AddPetRequest request = PetDF.createValidAddPetRequest();
        Response response = apiClient.post(PetEndpoints.ADD_PET, request);
        ResponseValidator.validateArrayNotEmpty(response, "tags");
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatAddPetWithInvalidStatusShouldReturn200() {
        AddPetRequest request = PetDF.createAddPetRequestWithInvalidStatus();
        Response response = apiClient.post(PetEndpoints.ADD_PET, request);
        ResponseValidator.validateStatusCode(response, 200);
    }

    @Test(groups = {"regression", "pet", "edge"})
    public void verifyThatAddPetWithEmptyPayloadShouldReturn200() {
        AddPetRequest request = PetDF.createAddPetRequestWithEmptyPayload();
        Response response = apiClient.post(PetEndpoints.ADD_PET, request);
        ResponseValidator.validateStatusCode(response, 200);
    }

    @Test(groups = {"regression", "pet", "edge"})
    public void verifyThatAddPetWithNullNameShouldReturn200() {
        AddPetRequest request = PetDF.createAddPetRequestWithNullName();
        Response response = apiClient.post(PetEndpoints.ADD_PET, request);
        ResponseValidator.validateStatusCode(response, 200);
    }
}
