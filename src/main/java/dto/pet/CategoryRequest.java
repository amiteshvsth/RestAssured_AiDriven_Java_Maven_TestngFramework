package dto.pet;

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
    "id",
    "name"
})
public class CategoryRequest {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;

    @JsonIgnore
    private final Map<String, Object> additionalProperties = new LinkedHashMap<>();
}
