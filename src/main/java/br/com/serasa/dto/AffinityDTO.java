package br.com.serasa.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AffinityDTO {
    @ApiModelProperty(value = "Affinity Region", required = true)
    @JsonProperty("regiao")
    @NotNull
    @NotBlank
    private String region;

    @ApiModelProperty(value = "Affinity States", required = true, example = "[RS,PR,SC]")
    @JsonProperty("estados")
    @NotNull
    @NotEmpty
    private List<@Size(min = 2, max = 2) String> states;
}
