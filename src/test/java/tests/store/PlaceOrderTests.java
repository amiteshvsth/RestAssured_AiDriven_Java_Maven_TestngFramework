package tests.store;

import base.baseApiTest;
import dataFactory.store.StoreDF;
import dto.request.store.PlaceOrderRequest;
import endpoints.storeEndpoints;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import validators.responseValidator;

public class PlaceOrderTests extends baseApiTest {

    @Test(groups = {"smoke", "regression", "store", "positive"})
    public void verifyThatPlaceOrderShouldReturn200WhenValidPayload() {
        PlaceOrderRequest request = StoreDF.createValidPlaceOrderRequest();
        Response response = apiClient.post(storeEndpoints.PLACE_ORDER, request);
        responseValidator.validateStatusCode(response, 200);
        responseValidator.validateFieldExists(response, "id");
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatPlaceOrderShouldReturn200WhenMinimalDataProvided() {
        PlaceOrderRequest request = StoreDF.createPlaceOrderRequestWithMinimalData();
        Response response = apiClient.post(storeEndpoints.PLACE_ORDER, request);
        responseValidator.validateStatusCode(response, 200);
        responseValidator.validateFieldExists(response, "id");
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatPlaceOrderShouldReturnResponseWithinTimeLimit() {
        PlaceOrderRequest request = StoreDF.createValidPlaceOrderRequest();
        Response response = apiClient.post(storeEndpoints.PLACE_ORDER, request);
        responseValidator.validateResponseTime(response, 3000);
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatPlaceOrderShouldReturnProperContentType() {
        PlaceOrderRequest request = StoreDF.createValidPlaceOrderRequest();
        Response response = apiClient.post(storeEndpoints.PLACE_ORDER, request);
        responseValidator.validateContentType(response, "application/json");
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatPlaceOrderShouldReturnAllRequiredFields() {
        PlaceOrderRequest request = StoreDF.createValidPlaceOrderRequest();
        Response response = apiClient.post(storeEndpoints.PLACE_ORDER, request);
        responseValidator.validateFieldExists(response, "id");
        responseValidator.validateFieldExists(response, "petId");
        responseValidator.validateFieldExists(response, "quantity");
        responseValidator.validateFieldExists(response, "status");
        responseValidator.validateFieldExists(response, "complete");
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatPlaceOrderWithCompleteStatusShouldReturn200() {
        PlaceOrderRequest request = StoreDF.createPlaceOrderRequestWithCompleteStatus();
        Response response = apiClient.post(storeEndpoints.PLACE_ORDER, request);
        responseValidator.validateStatusCode(response, 200);
        responseValidator.validateJsonFieldEquals(response, "status", "approved");
        responseValidator.validateBooleanField(response, "complete", true);
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatPlaceOrderWithDeliveredStatusShouldReturn200() {
        PlaceOrderRequest request = StoreDF.createPlaceOrderRequestWithDeliveredStatus();
        Response response = apiClient.post(storeEndpoints.PLACE_ORDER, request);
        responseValidator.validateStatusCode(response, 200);
        responseValidator.validateJsonFieldEquals(response, "status", "delivered");
    }

    @Test(groups = {"regression", "store", "negative"})
    public void verifyThatPlaceOrderWithInvalidQuantityShouldReturn200() {
        PlaceOrderRequest request = StoreDF.createPlaceOrderRequestWithInvalidQuantity();
        Response response = apiClient.post(storeEndpoints.PLACE_ORDER, request);
        responseValidator.validateStatusCode(response, 200);
    }

    @Test(groups = {"regression", "store", "edge"})
    public void verifyThatPlaceOrderWithEmptyPayloadShouldReturn200() {
        PlaceOrderRequest request = StoreDF.createPlaceOrderRequestWithEmptyPayload();
        Response response = apiClient.post(storeEndpoints.PLACE_ORDER, request);
        responseValidator.validateStatusCode(response, 200);
    }

    @Test(groups = {"regression", "store", "edge"})
    public void verifyThatPlaceOrderWithNegativeQuantityShouldReturn200() {
        PlaceOrderRequest request = StoreDF.createPlaceOrderRequestWithNegativeQuantity();
        Response response = apiClient.post(storeEndpoints.PLACE_ORDER, request);
        responseValidator.validateStatusCode(response, 200);
    }
}
