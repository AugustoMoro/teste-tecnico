package br.com.serasa.controller;

import br.com.serasa.DemoApplication;
import br.com.serasa.exception.AuthenticationException;
import br.com.serasa.mock.TokenMocks;
import br.com.serasa.mock.UserMocks;
import br.com.serasa.security.JWTSecurityUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SecurityController.class)
@ContextConfiguration(classes = { DemoApplication.class })
@AutoConfigureMockMvc(addFilters = false)
class SecurityControllerTest {
    @MockBean
    private JWTSecurityUtil jwtSecurityUtil;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void When_User_Is_Invalid_Expect_401_Unauthorized() throws Exception {
        Mockito.when(jwtSecurityUtil.authenticate(Mockito.any())).thenThrow(AuthenticationException.class);
        var invalidUserDtoMock = UserMocks.createInvalidUser();
        mockMvc.perform(
                post("/autenticar")
                        .characterEncoding("utf8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(invalidUserDtoMock)))
                .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()))
                .andReturn();
    }

    @Test
    void When_User_Is_Valid_Expect_200_OK_And_NotNull_TokenDto() throws Exception {
        var tokenDtoMock = TokenMocks.createTokenDtoMock();
        Mockito.when(jwtSecurityUtil.authenticate(Mockito.any())).thenReturn(tokenDtoMock);
        var invalidUserDtoMock = UserMocks.createValidUser();
        mockMvc.perform(
                post("/autenticar")
                        .characterEncoding("utf8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(invalidUserDtoMock)))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.accessToken", is(notNullValue())))
                .andReturn();
    }
}