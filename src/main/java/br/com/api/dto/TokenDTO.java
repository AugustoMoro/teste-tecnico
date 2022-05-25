package br.com.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class TokenDTO {
    @ApiModelProperty("Access token")
    @JsonProperty(value = "accessToken", access = JsonProperty.Access.READ_ONLY)
    private final String accessToken;
}
