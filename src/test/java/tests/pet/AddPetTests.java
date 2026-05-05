package tests.pet;

import base.BaseApiTest;
import dataFactory.PetDF;
import dto.pet.AddPetResponse;
import utils.ApiEndPoints;
import utils.ApiHelpers;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AddPetTests extends BaseApiTest {
    private ApiHelpers helpers = new ApiHelpers();

    @Test(groups = {"smoke", "regression", "pet", "positive"})
    public void verifyThatAddPetShouldReturn200WhenValidPayload() {
        Response response = given().spec(helpers.requestSpecificationWithJSONHeader()).body(PetDF.getData()).when().
                post(ApiEndPoints.ADD_PET);
        int statusCode = response.getStatusCode();
        AddPetResponse responseDto = response.as(AddPetResponse.class);
        
        Assert.assertEquals(statusCode, 200);
        Assert.assertNotNull(responseDto);
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatAddPetShouldReturn405WhenInvalidPayload() {
        Response response = given().spec(helpers.requestSpecificationWithJSONHeader()).body("").when().
                post(ApiEndPoints.ADD_PET);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 405);
    }
}