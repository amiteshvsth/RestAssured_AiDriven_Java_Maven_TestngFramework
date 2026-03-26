package dto.store;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceOrderResponse {
    private long id;
    private long petId;
    private int quantity;
    private OffsetDateTime shipDate;
    private String status;
    private boolean complete;
}