package tests.store;

import base.BaseApiTest;
import endpoints.StoreEndpoints;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import validators.ResponseValidator;

public class GetInventoryTests extends BaseApiTest {

    @Test(groups = {"smoke", "regression", "store", "positive"})
    public void verifyThatGetInventoryShouldReturn200() {
        Response response = apiClient.get(StoreEndpoints.GET_INVENTORY);
        ResponseValidator.validateStatusCode(response, 200);
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatGetInventoryShouldReturnInventoryData() {
        Response response = apiClient.get(StoreEndpoints.GET_INVENTORY);
        ResponseValidator.validateFieldExists(response, "available");
        ResponseValidator.validateFieldExists(response, "pending");
        ResponseValidator.validateFieldExists(response, "sold");
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatGetInventoryShouldReturnResponseWithinTimeLimit() {
        Response response = apiClient.get(StoreEndpoints.GET_INVENTORY);
        ResponseValidator.validateResponseTime(response, 3000);
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatGetInventoryShouldReturnProperContentType() {
        Response response = apiClient.get(StoreEndpoints.GET_INVENTORY);
        ResponseValidator.validateContentType(response, "application/json");
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatGetInventoryShouldReturnIntegerValues() {
        Response response = apiClient.get(StoreEndpoints.GET_INVENTORY);
        ResponseValidator.validateJsonField(response, "available", org.hamcrest.Matchers.greaterThanOrEqualTo(0));
        ResponseValidator.validateJsonField(response, "pending", org.hamcrest.Matchers.greaterThanOrEqualTo(0));
        ResponseValidator.validateJsonField(response, "sold", org.hamcrest.Matchers.greaterThanOrEqualTo(0));
    }

    @Test(groups = {"regression", "store", "edge"})
    public void verifyThatGetInventoryShouldReturnNonNegativeValues() {
        Response response = apiClient.get(StoreEndpoints.GET_INVENTORY);
        ResponseValidator.validateJsonField(response, "available", org.hamcrest.Matchers.not(org.hamcrest.Matchers.lessThan(0)));
        ResponseValidator.validateJsonField(response, "pending", org.hamcrest.Matchers.not(org.hamcrest.Matchers.lessThan(0)));
        ResponseValidator.validateJsonField(response, "sold", org.hamcrest.Matchers.not(org.hamcrest.Matchers.lessThan(0)));
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatGetInventoryReturnsCompleteObject() {
        Response response = apiClient.get(StoreEndpoints.GET_INVENTORY);
        ResponseValidator.validateJsonField(response, "available", org.hamcrest.Matchers.greaterThanOrEqualTo(0));
    }
}
