package dataFactory;

import com.github.javafaker.Faker;
import dto.store.PlaceOrderRequest;

import java.time.OffsetDateTime;

public class StoreDF {

    private static final Faker faker = new Faker();

    public static PlaceOrderRequest getData() {
        PlaceOrderRequest request = new PlaceOrderRequest();
        request.setId(faker.number().randomNumber());
        request.setPetId(faker.number().randomNumber());
        request.setQuantity(faker.number().numberBetween(1, 10));
        request.setShipDate(OffsetDateTime.now().plusDays(7));
        request.setStatus("placed");
        request.setComplete(false);
        return request;
    }

    public static Long getValidId() {
        return faker.number().randomNumber();
    }

    public static Long getNonexistentId() {
        return 999999999L;
    }

    public static Long getInvalidId() {
        return -1L;
    }
}
