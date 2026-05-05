package tests.pet;

import base.BaseApiTest;
import dataFactory.PetDF;
import dto.pet.AddPetRequest;
import dto.pet.GetPetResponse;
import utils.ApiEndPoints;
import utils.ApiHelpers;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetPetTests extends BaseApiTest {
    private ApiHelpers helpers = new ApiHelpers();

    @Test(groups = {"smoke", "regression", "pet", "positive"})
    public void verifyThatGetPetShouldReturn200WhenPetExists() {
        AddPetRequest petRequest = PetDF.getData();
        given().spec(helpers.requestSpecificationWithJSONHeader()).body(petRequest).when().
                post(ApiEndPoints.ADD_PET);
        
        long petId = petRequest.getId();
        Response response = given().spec(helpers.requestSpecificationBaseURI()).pathParam("petId", petId).when().
                get(ApiEndPoints.GET_PET);
        int statusCode = response.getStatusCode();
        GetPetResponse responseDto = response.as(GetPetResponse.class);
        
        Assert.assertEquals(statusCode, 200);
        Assert.assertNotNull(responseDto);
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatGetPetShouldReturn404WhenPetNotFound() {
        long petId = 999999999L;
        Response response = given().spec(helpers.requestSpecificationBaseURI()).pathParam("petId", petId).when().
                get(ApiEndPoints.GET_PET);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 404);
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatGetPetShouldReturn400WhenInvalidId() {
        long petId = -1L;
        Response response = given().spec(helpers.requestSpecificationBaseURI()).pathParam("petId", petId).when().
                get(ApiEndPoints.GET_PET);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 404);
    }

    @Test(groups = {"smoke", "regression", "pet", "positive"})
    public void verifyThatFindPetsByStatusShouldReturn200() {
        String status = "available";
        Response response = given().spec(helpers.requestSpecificationBaseURI()).when().
                get(ApiEndPoints.FIND_PETS_BY_STATUS + "?status=" + status);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatFindPetsByStatusShouldReturn400ForInvalidStatus() {
        String status = "invalid_status";
        Response response = given().spec(helpers.requestSpecificationBaseURI()).when().
                get(ApiEndPoints.FIND_PETS_BY_STATUS + "?status=" + status);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }
}