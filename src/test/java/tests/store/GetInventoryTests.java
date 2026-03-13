package tests.store;

import base.baseApiTest;
import endpoints.storeEndpoints;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import validators.responseValidator;

public class GetInventoryTests extends baseApiTest {

    @Test(groups = {"smoke", "regression", "store", "positive"})
    public void verifyThatGetInventoryShouldReturn200() {
        Response response = apiClient.get(storeEndpoints.GET_INVENTORY);
        responseValidator.validateStatusCode(response, 200);
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatGetInventoryShouldReturnInventoryData() {
        Response response = apiClient.get(storeEndpoints.GET_INVENTORY);
        responseValidator.validateFieldExists(response, "available");
        responseValidator.validateFieldExists(response, "pending");
        responseValidator.validateFieldExists(response, "sold");
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatGetInventoryShouldReturnResponseWithinTimeLimit() {
        Response response = apiClient.get(storeEndpoints.GET_INVENTORY);
        responseValidator.validateResponseTime(response, 3000);
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatGetInventoryShouldReturnProperContentType() {
        Response response = apiClient.get(storeEndpoints.GET_INVENTORY);
        responseValidator.validateContentType(response, "application/json");
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatGetInventoryShouldReturnIntegerValues() {
        Response response = apiClient.get(storeEndpoints.GET_INVENTORY);
        responseValidator.validateJsonField(response, "available", org.hamcrest.Matchers.greaterThanOrEqualTo(0));
        responseValidator.validateJsonField(response, "pending", org.hamcrest.Matchers.greaterThanOrEqualTo(0));
        responseValidator.validateJsonField(response, "sold", org.hamcrest.Matchers.greaterThanOrEqualTo(0));
    }

    @Test(groups = {"regression", "store", "edge"})
    public void verifyThatGetInventoryShouldReturnNonNegativeValues() {
        Response response = apiClient.get(storeEndpoints.GET_INVENTORY);
        responseValidator.validateJsonField(response, "available", org.hamcrest.Matchers.not(org.hamcrest.Matchers.lessThan(0)));
        responseValidator.validateJsonField(response, "pending", org.hamcrest.Matchers.not(org.hamcrest.Matchers.lessThan(0)));
        responseValidator.validateJsonField(response, "sold", org.hamcrest.Matchers.not(org.hamcrest.Matchers.lessThan(0)));
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatGetInventoryReturnsCompleteObject() {
        Response response = apiClient.get(storeEndpoints.GET_INVENTORY);
        responseValidator.validateJsonField(response, "available", org.hamcrest.Matchers.greaterThanOrEqualTo(0));
    }
}
