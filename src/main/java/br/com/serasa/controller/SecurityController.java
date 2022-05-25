package br.com.serasa.controller;

import br.com.serasa.dto.TokenDTO;
import br.com.serasa.dto.UserDTO;
import br.com.serasa.exception.AuthenticationException;
import br.com.serasa.security.JWTSecurityUtil;
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

@Api(tags = { "SecurityController" })
@RestController
@RequestMapping("autenticar")
@RequiredArgsConstructor
public class SecurityController {
    private final JWTSecurityUtil jwtSecurityUtil;

    @ApiOperation("Get token to authenticate")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User authenticated"),
            @ApiResponse(code = 401, message = "User not authenticated, invalid credentials")
    })
    @PostMapping
    public ResponseEntity<TokenDTO> authenticate(@RequestBody @Valid UserDTO userDto) {
        try {
            return ResponseEntity
                    .ok(jwtSecurityUtil.authenticate(userDto));
        } catch (AuthenticationException ex) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
