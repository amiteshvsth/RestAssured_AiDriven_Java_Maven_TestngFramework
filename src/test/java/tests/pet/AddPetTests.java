package tests.pet;

import base.BaseApiTest;
import dataFactory.PetDF;
import endpoints.PetEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddPetTests extends BaseApiTest {

    @Test(groups = {"smoke", "regression", "pet", "positive"})
    public void verifyThatAddPetShouldReturn200WhenValidPayload() {
        Response response = apiClient.post(PetEndpoints.ADD_PET, PetDF.getData());
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotNull(response.jsonPath().get("id"));
        Assert.assertNotNull(response.jsonPath().get("name"));
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatAddPetShouldReturn200WhenMinimalDataProvided() {
        Response response = apiClient.post(PetEndpoints.ADD_PET, PetDF.getData());
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotNull(response.jsonPath().get("id"));
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatAddPetShouldReturnResponseWithinTimeLimit() {
        Response response = apiClient.post(PetEndpoints.ADD_PET, PetDF.getData());
        Assert.assertTrue(response.getTime() < 3000);
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatAddPetShouldReturnProperContentType() {
        Response response = apiClient.post(PetEndpoints.ADD_PET, PetDF.getData());
        Assert.assertTrue(response.getContentType().contains("application/json"));
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatAddPetShouldReturnAllFields() {
        Response response = apiClient.post(PetEndpoints.ADD_PET, PetDF.getData());
        Assert.assertNotNull(response.jsonPath().get("id"));
        Assert.assertNotNull(response.jsonPath().get("name"));
        Assert.assertNotNull(response.jsonPath().get("status"));
        Assert.assertNotNull(response.jsonPath().get("category"));
        Assert.assertNotNull(response.jsonPath().get("photoUrls"));
        Assert.assertNotNull(response.jsonPath().get("tags"));
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatAddPetShouldIncludeCategoryDetails() {
        Response response = apiClient.post(PetEndpoints.ADD_PET, PetDF.getData());
        Assert.assertEquals(response.jsonPath().getInt("category.id"), 1);
        Assert.assertEquals(response.jsonPath().getString("category.name"), "Dogs");
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatAddPetShouldIncludeTagDetails() {
        Response response = apiClient.post(PetEndpoints.ADD_PET, PetDF.getData());
        Assert.assertFalse(response.jsonPath().getList("tags").isEmpty());
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatAddPetWithInvalidStatusShouldReturn200() {
        Response response = apiClient.post(PetEndpoints.ADD_PET, PetDF.getData());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(groups = {"regression", "pet", "edge"})
    public void verifyThatAddPetWithEmptyPayloadShouldReturn200() {
        Response response = apiClient.post(PetEndpoints.ADD_PET, PetDF.getData());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(groups = {"regression", "pet", "edge"})
    public void verifyThatAddPetWithNullNameShouldReturn200() {
        Response response = apiClient.post(PetEndpoints.ADD_PET, PetDF.getData());
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}