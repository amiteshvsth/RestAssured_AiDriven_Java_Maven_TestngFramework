package dto.store;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "inventory"
})
public class InventoryResponse {
    @JsonProperty("inventory")
    private Map<String, Integer> inventory;

    @JsonIgnore
    private final Map<String, Object> additionalProperties = new LinkedHashMap<>();
}
