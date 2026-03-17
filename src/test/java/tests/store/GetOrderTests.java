package tests.store;

import base.BaseApiTest;
import dataFactory.store.StoreDF;
import endpoints.StoreEndpoints;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import validators.ResponseValidator;

public class GetOrderTests extends BaseApiTest {

    @Test(groups = {"smoke", "regression", "store", "positive"})
    public void verifyThatGetOrderShouldReturn200WhenOrderExists() {
        long orderId = StoreDF.getValidOrderId();
        Response response = apiClient.getWithPathParam(StoreEndpoints.GET_ORDER, "orderId", orderId);
        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateJsonFieldEquals(response, "id", orderId);
        ResponseValidator.validateFieldExists(response, "petId");
        ResponseValidator.validateFieldExists(response, "quantity");
        ResponseValidator.validateFieldExists(response, "status");
    }

    @Test(groups = {"regression", "store", "negative"})
    public void verifyThatGetOrderShouldReturn404WhenOrderNotFound() {
        long orderId = StoreDF.getNonexistentOrderId();
        Response response = apiClient.getWithPathParam(StoreEndpoints.GET_ORDER, "orderId", orderId);
        ResponseValidator.validateStatusCode(response, 404);
        ResponseValidator.validateJsonFieldEquals(response, "code", 404);
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatGetOrderShouldReturnResponseWithinTimeLimit() {
        long orderId = StoreDF.getValidOrderId();
        Response response = apiClient.getWithPathParam(StoreEndpoints.GET_ORDER, "orderId", orderId);
        ResponseValidator.validateResponseTime(response, 3000);
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatGetOrderShouldReturnProperContentType() {
        long orderId = StoreDF.getValidOrderId();
        Response response = apiClient.getWithPathParam(StoreEndpoints.GET_ORDER, "orderId", orderId);
        ResponseValidator.validateContentType(response, "application/json");
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatGetOrderShouldReturnAllRequiredFields() {
        long orderId = StoreDF.getValidOrderId();
        Response response = apiClient.getWithPathParam(StoreEndpoints.GET_ORDER, "orderId", orderId);
        ResponseValidator.validateFieldExists(response, "id");
        ResponseValidator.validateFieldExists(response, "petId");
        ResponseValidator.validateFieldExists(response, "quantity");
        ResponseValidator.validateFieldExists(response, "status");
        ResponseValidator.validateFieldExists(response, "complete");
    }

    @Test(groups = {"regression", "store", "negative"})
    public void verifyThatGetOrderWithInvalidIdShouldReturn404() {
        long orderId = StoreDF.getInvalidOrderId();
        Response response = apiClient.getWithPathParam(StoreEndpoints.GET_ORDER, "orderId", orderId);
        ResponseValidator.validateStatusCode(response, 404);
    }
}
