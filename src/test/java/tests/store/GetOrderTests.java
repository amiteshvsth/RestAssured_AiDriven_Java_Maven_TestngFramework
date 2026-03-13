package tests.store;

import base.baseApiTest;
import dataFactory.store.StoreDF;
import endpoints.storeEndpoints;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import validators.responseValidator;

public class GetOrderTests extends baseApiTest {

    @Test(groups = {"smoke", "regression", "store", "positive"})
    public void verifyThatGetOrderShouldReturn200WhenOrderExists() {
        long orderId = StoreDF.getValidOrderId();
        Response response = apiClient.getWithPathParam(storeEndpoints.GET_ORDER, "orderId", orderId);
        responseValidator.validateStatusCode(response, 200);
        responseValidator.validateJsonFieldEquals(response, "id", orderId);
        responseValidator.validateFieldExists(response, "petId");
        responseValidator.validateFieldExists(response, "quantity");
        responseValidator.validateFieldExists(response, "status");
    }

    @Test(groups = {"regression", "store", "negative"})
    public void verifyThatGetOrderShouldReturn404WhenOrderNotFound() {
        long orderId = StoreDF.getNonexistentOrderId();
        Response response = apiClient.getWithPathParam(storeEndpoints.GET_ORDER, "orderId", orderId);
        responseValidator.validateStatusCode(response, 404);
        responseValidator.validateJsonFieldEquals(response, "code", 404);
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatGetOrderShouldReturnResponseWithinTimeLimit() {
        long orderId = StoreDF.getValidOrderId();
        Response response = apiClient.getWithPathParam(storeEndpoints.GET_ORDER, "orderId", orderId);
        responseValidator.validateResponseTime(response, 3000);
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatGetOrderShouldReturnProperContentType() {
        long orderId = StoreDF.getValidOrderId();
        Response response = apiClient.getWithPathParam(storeEndpoints.GET_ORDER, "orderId", orderId);
        responseValidator.validateContentType(response, "application/json");
    }

    @Test(groups = {"regression", "store", "positive"})
    public void verifyThatGetOrderShouldReturnAllRequiredFields() {
        long orderId = StoreDF.getValidOrderId();
        Response response = apiClient.getWithPathParam(storeEndpoints.GET_ORDER, "orderId", orderId);
        responseValidator.validateFieldExists(response, "id");
        responseValidator.validateFieldExists(response, "petId");
        responseValidator.validateFieldExists(response, "quantity");
        responseValidator.validateFieldExists(response, "status");
        responseValidator.validateFieldExists(response, "complete");
    }

    @Test(groups = {"regression", "store", "negative"})
    public void verifyThatGetOrderWithInvalidIdShouldReturn404() {
        long orderId = StoreDF.getInvalidOrderId();
        Response response = apiClient.getWithPathParam(storeEndpoints.GET_ORDER, "orderId", orderId);
        responseValidator.validateStatusCode(response, 404);
    }
}
