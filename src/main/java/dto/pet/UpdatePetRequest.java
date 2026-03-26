package dto.pet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdatePetRequest {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("category")
    private CategoryRequest category;

    @JsonProperty("name")
    private String name;

    @JsonProperty("photoUrls")
    private List<String> photoUrls;

    @JsonProperty("tags")
    private List<TagRequest> tags;

    @JsonProperty("status")
    private String status;
}
