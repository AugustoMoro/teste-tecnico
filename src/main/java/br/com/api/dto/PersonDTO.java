package br.com.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.*;
import java.util.List;

@EqualsAndHashCode
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonDTO {
    @EqualsAndHashCode.Exclude
    @ApiModelProperty(hidden = true)
    private Long id;

    @ApiModelProperty(value = "Person name", required = true)
    @JsonProperty("nome")
    @NotNull
    @NotBlank
    private String name;

    @ApiModelProperty(value = "Person phone number")
    @JsonProperty("telefone")
    private String phoneNumber;

    @ApiModelProperty("Person age")
    @JsonProperty("idade")
    private Short age;

    @ApiModelProperty("Person city")
    @JsonProperty("cidade")
    private String city;

    @ApiModelProperty("Person state")
    @JsonProperty("estado")
    @NotNull
    @NotBlank
    @Size(min = 2, max = 2)
    private String state;

    @ApiModelProperty(value = "Person region", required = true)
    @JsonProperty("regiao")
    @NotNull
    @NotBlank
    private String region;

    @ApiModelProperty(value = "Person score", required = true)
    @NotNull
    @Min(0)
    @Max(1000)
    private Long score;

    @ApiModelProperty("Person affinity states")
    @JsonProperty(value = "estados", access = JsonProperty.Access.READ_ONLY)
    private List<String> states;

    @ApiModelProperty("Person score description")
    @JsonProperty(value = "scoreDescricao", access = JsonProperty.Access.READ_ONLY)
    private String scoreDescription;
}