package br.com.serasa.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScoreDTO {
    @ApiModelProperty(value = "Score description", required = true)
    @JsonProperty("scoreDescricao")
    @NotNull
    @NotBlank
    private String scoreDescription;

    @ApiModelProperty(value = "Score initial value", required = true)
    @JsonProperty("inicial")
    @NotNull
    @Min(0)
    @Max(1000)
    private Long min;

    @ApiModelProperty(value = "Score final value", required = true)
    @JsonProperty("final")
    @NotNull
    @Min(0)
    @Max(1000)
    private Long max;
}
