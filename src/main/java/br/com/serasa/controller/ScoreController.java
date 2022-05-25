package br.com.serasa.controller;

import br.com.serasa.dto.ScoreDTO;
import br.com.serasa.service.ScoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = { "ScoreController" })
@RestController
@RequestMapping("score")
@RequiredArgsConstructor
public class ScoreController {
    private final ScoreService scoreService;

    @ApiOperation("Create or update a score")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created/updated successfully"),
            @ApiResponse(code = 401, message = "User unauthorized")
    })
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid ScoreDTO scoreDto) {
        this.scoreService.saveDto(scoreDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
