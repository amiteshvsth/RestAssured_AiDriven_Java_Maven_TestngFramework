package tests.pet;

import base.BaseApiTest;
import dataFactory.PetDF;
import dto.pet.AddPetRequest;
import utils.ApiEndPoints;
import utils.ApiHelpers;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeletePetTests extends BaseApiTest {
    private ApiHelpers helpers = new ApiHelpers();

    @Test(groups = {"smoke", "regression", "pet", "positive"})
    public void verifyThatDeletePetShouldReturn200WhenPetExists() {
        AddPetRequest petRequest = PetDF.getData();
        given().spec(helpers.requestSpecificationWithJSONHeader()).body(petRequest).when().
                post(ApiEndPoints.ADD_PET);
        
        long petId = petRequest.getId();
        Response response = given().spec(helpers.requestSpecificationBaseURI()).pathParam("petId", petId).when().
                delete(ApiEndPoints.DELETE_PET);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200);
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatDeletePetShouldReturn404WhenPetNotFound() {
        long petId = 999999999L;
        Response response = given().spec(helpers.requestSpecificationBaseURI()).pathParam("petId", petId).when().
                delete(ApiEndPoints.DELETE_PET);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 404);
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatDeletePetShouldReturn400WhenInvalidId() {
        long petId = -1L;
        Response response = given().spec(helpers.requestSpecificationBaseURI()).pathParam("petId", petId).when().
                delete(ApiEndPoints.DELETE_PET);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 404);
    }
}