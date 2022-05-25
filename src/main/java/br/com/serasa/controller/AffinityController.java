package br.com.serasa.controller;

import br.com.serasa.dto.AffinityDTO;
import br.com.serasa.service.AffinityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = { "AffinityController" })
@RestController
@RequestMapping("afinidade")
@RequiredArgsConstructor
public class AffinityController {
    private final AffinityService affinityService;

    @ApiOperation("Create or update an Affinity")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created/Updated successfully"),
            @ApiResponse(code = 401, message = "User unauthorized")
    })
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid AffinityDTO affinityDto) {
        this.affinityService.saveDto(affinityDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
