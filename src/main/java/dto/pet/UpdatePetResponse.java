package dto.pet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdatePetResponse {
    private long id;
    private CategoryRequest category;
    private String name;
    private List<String> photoUrls;
    private List<TagRequest> tags;
    private String status;
}