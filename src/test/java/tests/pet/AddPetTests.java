package tests.pet;

import base.BaseApiTest;
import dataFactory.PetDF;
import dto.pet.AddPetResponse;
import endpoints.PetEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AddPetTests extends BaseApiTest {

    @Test(groups = {"smoke", "regression", "pet", "positive"})
    public void verifyThatAddPetShouldReturn200WhenValidPayload() {
        Response response = apiClient.post(PetEndpoints.ADD_PET, PetDF.getData());
        int statusCode = response.getStatusCode();
        AddPetResponse responseDto = response.as(AddPetResponse.class);
        
        Assert.assertEquals(statusCode, 200);
        
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(responseDto.getId());
        softAssert.assertNotNull(responseDto.getName());
        softAssert.assertAll();
    }

    @Test(groups = {"regression", "pet", "negative"})
    public void verifyThatAddPetShouldReturn405WhenInvalidPayload() {
        Response response = apiClient.post(PetEndpoints.ADD_PET, "");
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 405);
    }
}