package dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "users"
})
public class CreateUsersWithListRequest {
    @JsonProperty("users")
    private List<CreateUserRequest> users;

    @JsonIgnore
    private final Map<String, Object> additionalProperties = new LinkedHashMap<>();
}
