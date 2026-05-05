package dto.store;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "id",
    "petId",
    "quantity",
    "shipDate",
    "status",
    "complete"
})
public class GetOrderResponse {
    @JsonProperty("id")
    private long id;
    @JsonProperty("petId")
    private long petId;
    @JsonProperty("quantity")
    private int quantity;
    @JsonProperty("shipDate")
    private OffsetDateTime shipDate;
    @JsonProperty("status")
    private String status;
    @JsonProperty("complete")
    private boolean complete;

    @JsonIgnore
    private final Map<String, Object> additionalProperties = new LinkedHashMap<>();
}