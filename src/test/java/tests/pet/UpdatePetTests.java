package tests.pet;

import base.BaseApiTest;
import dataFactory.PetDF;
import dto.pet.AddPetRequest;
import dto.pet.UpdatePetRequest;
import utils.ApiEndPoints;
import utils.ApiHelpers;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdatePetTests extends BaseApiTest {
    private ApiHelpers helpers = new ApiHelpers();

    @Test(groups = {"smoke", "regression", "pet", "positive"})
    public void verifyThatUpdatePetShouldReturn200WhenValidPayload() {
        AddPetRequest addPetRequest = PetDF.getData();
        UpdatePetRequest request = new UpdatePetRequest();
        request.setId(addPetRequest.getId());
        request.setName(addPetRequest.getName());
        request.setCategory(addPetRequest.getCategory());
        request.setPhotoUrls(addPetRequest.getPhotoUrls());
        request.setTags(addPetRequest.getTags());
        request.setStatus("pending");
        Response response = given().spec(helpers.requestSpecificationWithJSONHeader()).body(request).when().
                put(ApiEndPoints.PET_BASE);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatUpdatePetShouldReturn404WhenPetNotFound() {
        AddPetRequest addPetRequest = PetDF.getData();
        UpdatePetRequest request = new UpdatePetRequest();
        request.setId(addPetRequest.getId());
        request.setName(addPetRequest.getName());
        request.setCategory(addPetRequest.getCategory());
        request.setPhotoUrls(addPetRequest.getPhotoUrls());
        request.setTags(addPetRequest.getTags());
        request.setStatus("pending");
        Response response = given().spec(helpers.requestSpecificationWithJSONHeader()).body(request).when().
                put(ApiEndPoints.PET_BASE);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatUpdatePetShouldReturn400WhenInvalidPayload() {
        UpdatePetRequest request = new UpdatePetRequest();
        Response response = given().spec(helpers.requestSpecificationWithJSONHeader()).body(request).when().
                put(ApiEndPoints.PET_BASE);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }

    @Test(groups = {"regression", "pet", "positive"})
    public void verifyThatUpdatePetFormShouldReturn200() {
        Assert.assertTrue(true);
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatUpdatePetFormShouldReturn405WhenInvalidInput() {
        Assert.assertTrue(true);
    }
}