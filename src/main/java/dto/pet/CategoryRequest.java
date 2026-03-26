package dto.pet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryRequest {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
}
