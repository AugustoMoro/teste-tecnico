package br.com.api.controller;

import br.com.api.dto.PersonDTO;
import br.com.api.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = { "PersonController" })
@RestController
@RequestMapping("pessoa")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @ApiOperation("Create or update a person")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created/updated successfully"),
            @ApiResponse(code = 401, message = "User unauthorized")
    })
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid PersonDTO personDto) {
        this.personService.saveDto(personDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation("Find a person by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Person found"),
            @ApiResponse(code = 204, message = "Person not found"),
            @ApiResponse(code = 401, message = "User unauthorized")
    })
    @GetMapping("{id}")
    public ResponseEntity<PersonDTO> findPersonDto(@PathVariable Long id) {
        return this.personService.findPersonDtoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @ApiOperation("Find all persons registered")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Persons found"),
            @ApiResponse(code = 204, message = "No one person found"),
            @ApiResponse(code = 401, message = "User unauthorized")
    })
    @GetMapping
    public ResponseEntity<List<PersonDTO>> findAllPersonDto() {
        return this.personService.findAllPersonsDto()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }
}
