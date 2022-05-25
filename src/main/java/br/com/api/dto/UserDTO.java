package br.com.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    @ApiModelProperty(value = "User name", required = true)
    @JsonProperty("usuario")
    @NotNull
    @NotBlank
    private String username;

    @ApiModelProperty(value = "User password", required = true)
    @JsonProperty("senha")
    @NotNull
    @NotBlank
    private String password;
}
