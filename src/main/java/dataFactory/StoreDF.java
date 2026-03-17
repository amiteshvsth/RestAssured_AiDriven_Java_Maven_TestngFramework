package dataFactory;

import dto.store.PlaceOrderRequest;

import java.time.OffsetDateTime;

public class StoreDF {

    public static PlaceOrderRequest createValidPlaceOrderRequest() {
        return PlaceOrderRequest.builder()
                .id(1L)
                .petId(1L)
                .quantity(1)
                .shipDate(OffsetDateTime.now().plusDays(7))
                .status("placed")
                .complete(false)
                .build();
    }

    public static PlaceOrderRequest createPlaceOrderRequestWithMinimalData() {
        return PlaceOrderRequest.builder()
                .petId(1L)
                .quantity(1)
                .status("placed")
                .build();
    }

    public static PlaceOrderRequest createPlaceOrderRequestWithCompleteStatus() {
        return PlaceOrderRequest.builder()
                .id(2L)
                .petId(2L)
                .quantity(5)
                .shipDate(OffsetDateTime.now().plusDays(3))
                .status("approved")
                .complete(true)
                .build();
    }

    public static PlaceOrderRequest createPlaceOrderRequestWithDeliveredStatus() {
        return PlaceOrderRequest.builder()
                .id(3L)
                .petId(3L)
                .quantity(2)
                .shipDate(OffsetDateTime.now().minusDays(1))
                .status("delivered")
                .complete(true)
                .build();
    }

    public static PlaceOrderRequest createPlaceOrderRequestWithInvalidQuantity() {
        return PlaceOrderRequest.builder()
                .petId(1L)
                .quantity(0)
                .status("placed")
                .build();
    }

    public static PlaceOrderRequest createPlaceOrderRequestWithEmptyPayload() {
        return PlaceOrderRequest.builder().build();
    }

    public static PlaceOrderRequest createPlaceOrderRequestWithNegativeQuantity() {
        return PlaceOrderRequest.builder()
                .petId(1L)
                .quantity(-1)
                .status("placed")
                .build();
    }

    public static Long getValidOrderId() {
        return 1L;
    }

    public static Long getNonexistentOrderId() {
        return 999999999L;
    }

    public static Long getInvalidOrderId() {
        return -1L;
    }
}
