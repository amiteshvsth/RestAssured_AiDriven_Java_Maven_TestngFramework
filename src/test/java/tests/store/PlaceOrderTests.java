package tests.store;

import base.BaseApiTest;
import dataFactory.StoreDF;
import dto.store.PlaceOrderRequest;
import endpoints.StoreEndpoints;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import validators.ResponseValidator;

public class PlaceOrderTests extends BaseApiTest {

    @Test(groups = {"smoke", "regression", "store", "positive"})
    public void verifyThatPlaceOrderShouldReturn200WhenValidPayload() {
        PlaceOrderRequest request = StoreDF.createValidPlaceOrderRequest();
        Response response = apiClient.post(StoreEndpoints.PLACE_ORDER, request);
        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateFieldExists(response, "id");
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatPlaceOrderShouldReturn200WhenMinimalDataProvided() {
        PlaceOrderRequest request = StoreDF.createPlaceOrderRequestWithMinimalData();
        Response response = apiClient.post(StoreEndpoints.PLACE_ORDER, request);
        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateFieldExists(response, "id");
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatPlaceOrderShouldReturnResponseWithinTimeLimit() {
        PlaceOrderRequest request = StoreDF.createValidPlaceOrderRequest();
        Response response = apiClient.post(StoreEndpoints.PLACE_ORDER, request);
        ResponseValidator.validateResponseTime(response, 3000);
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatPlaceOrderShouldReturnProperContentType() {
        PlaceOrderRequest request = StoreDF.createValidPlaceOrderRequest();
        Response response = apiClient.post(StoreEndpoints.PLACE_ORDER, request);
        ResponseValidator.validateContentType(response, "application/json");
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatPlaceOrderShouldReturnAllRequiredFields() {
        PlaceOrderRequest request = StoreDF.createValidPlaceOrderRequest();
        Response response = apiClient.post(StoreEndpoints.PLACE_ORDER, request);
        ResponseValidator.validateFieldExists(response, "id");
        ResponseValidator.validateFieldExists(response, "petId");
        ResponseValidator.validateFieldExists(response, "quantity");
        ResponseValidator.validateFieldExists(response, "status");
        ResponseValidator.validateFieldExists(response, "complete");
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatPlaceOrderWithCompleteStatusShouldReturn200() {
        PlaceOrderRequest request = StoreDF.createPlaceOrderRequestWithCompleteStatus();
        Response response = apiClient.post(StoreEndpoints.PLACE_ORDER, request);
        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateJsonFieldEquals(response, "status", "approved");
        ResponseValidator.validateBooleanField(response, "complete", true);
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatPlaceOrderWithDeliveredStatusShouldReturn200() {
        PlaceOrderRequest request = StoreDF.createPlaceOrderRequestWithDeliveredStatus();
        Response response = apiClient.post(StoreEndpoints.PLACE_ORDER, request);
        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateJsonFieldEquals(response, "status", "delivered");
    }

    @Test(groups = {"regression", "store", "negative"})
    public void verifyThatPlaceOrderWithInvalidQuantityShouldReturn200() {
        PlaceOrderRequest request = StoreDF.createPlaceOrderRequestWithInvalidQuantity();
        Response response = apiClient.post(StoreEndpoints.PLACE_ORDER, request);
        ResponseValidator.validateStatusCode(response, 200);
    }

    @Test(groups = {"regression", "store", "edge"})
    public void verifyThatPlaceOrderWithEmptyPayloadShouldReturn200() {
        PlaceOrderRequest request = StoreDF.createPlaceOrderRequestWithEmptyPayload();
        Response response = apiClient.post(StoreEndpoints.PLACE_ORDER, request);
        ResponseValidator.validateStatusCode(response, 200);
    }

    @Test(groups = {"regression", "store", "edge"})
    public void verifyThatPlaceOrderWithNegativeQuantityShouldReturn200() {
        PlaceOrderRequest request = StoreDF.createPlaceOrderRequestWithNegativeQuantity();
        Response response = apiClient.post(StoreEndpoints.PLACE_ORDER, request);
        ResponseValidator.validateStatusCode(response, 200);
    }
}
