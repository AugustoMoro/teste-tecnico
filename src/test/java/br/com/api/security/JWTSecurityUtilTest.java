package br.com.api.security;

import br.com.api.exception.AuthenticationException;
import br.com.api.mock.UserMocks;
import br.com.api.service.UserDetailsJwtService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.TimeZone;

@ExtendWith(SpringExtension.class)
class JWTSecurityUtilTest {
    @InjectMocks
    JWTSecurityUtil jwtSecurityUtil;

    @Mock
    UserDetailsJwtService userDetailsJwtService;

    @BeforeEach
    void init() {
        String jwtSecret = "test";
        String jwtIssuer = "test";
        ReflectionTestUtils.setField(jwtSecurityUtil,"expirationInMinutes", 30L);
        ReflectionTestUtils.setField(jwtSecurityUtil,"jwtSecret", jwtSecret);
        ReflectionTestUtils.setField(jwtSecurityUtil,"jwtIssuer", jwtIssuer);
        ReflectionTestUtils.setField(jwtSecurityUtil,"algorithm", Algorithm.HMAC512(jwtSecret));
        ReflectionTestUtils.setField(jwtSecurityUtil,"jwtVerifier", JWT.require(Algorithm.HMAC512(jwtSecret)).withIssuer(jwtIssuer).build());
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    @Test
    void When_Authenticate_With_Invalid_User_Expect_Throw_AuthenticationException() {
        var invalidUserDtoMock = UserMocks.createInvalidUser();

        Mockito.doThrow(AuthenticationException.class)
                .when(userDetailsJwtService).validateUserDetails(Mockito.any());

        Assertions.assertThatThrownBy(() -> jwtSecurityUtil.authenticate(invalidUserDtoMock))
                .isInstanceOf(AuthenticationException.class);
    }

    @Test
    void When_Authenticate_With_Valid_User_Expect_NotNull_TokenDto() {
        var validUserDtoMock = UserMocks.createValidUser();

        Mockito.doNothing().when(userDetailsJwtService).validateUserDetails(Mockito.any());

        var actualResult = jwtSecurityUtil.authenticate(validUserDtoMock);

        Assertions.assertThat(actualResult).isNotNull();
    }

    @Test
    void When_IsTokenValid_Expect_False_For_Invalid_Token_Value() {
        var tokenMock = "teste";

        var actualResult = jwtSecurityUtil.isTokenValid(tokenMock);

        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    void When_IsTokenValid_Expect_True_For_Valid_Token_Value() {
        var validUserDtoMock = UserMocks.createValidUser();
        var validToken = jwtSecurityUtil.authenticate(validUserDtoMock);

        var actualResult = jwtSecurityUtil.isTokenValid(validToken.getAccessToken());

        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    void When_GetUserDetailsFromToken_Expect_NotNull_UserDetails_Value() {
        var validUserDtoMock = UserMocks.createValidUser();
        var validUserDetails = UserMocks.createValidUserDetails();
        var validToken = jwtSecurityUtil.authenticate(validUserDtoMock);

        Mockito.when(userDetailsJwtService.loadUserByUsername(Mockito.any())).thenReturn(validUserDetails);

        var actualResult = jwtSecurityUtil.getUserDetailsFromToken(validToken.getAccessToken());

        Assertions.assertThat(actualResult).isNotNull();
    }
}