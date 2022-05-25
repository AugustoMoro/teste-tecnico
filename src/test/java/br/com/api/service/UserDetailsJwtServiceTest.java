package br.com.api.service;

import br.com.api.exception.AuthenticationException;
import br.com.api.mock.UserMocks;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class UserDetailsJwtServiceTest {
    @InjectMocks
    UserDetailsJwtService userDetailsJwtService;

    @Test
    void When_LoadUserByUsername_With_Invalid_Username_Expect_Null() {
        var invalidUsernameMock = "test";
        var actualResult = userDetailsJwtService.loadUserByUsername(invalidUsernameMock);

        Assertions.assertThat(actualResult).isNull();
    }

    @Test
    void When_LoadUserByUsername_With_Valid_Username_Expect_NotNull() {
        var validUsernameMock = "admin";
        var actualResult = userDetailsJwtService.loadUserByUsername(validUsernameMock);

        Assertions.assertThat(actualResult).isNotNull();
    }

    @Test
    void When_ValidateUserDetails_With_Invalid_Username_Expect_Throw_AuthenticationException() {
        var validUserDto = UserMocks.createInvalidUser();

        Assertions.assertThatThrownBy(() -> userDetailsJwtService.validateUserDetails(validUserDto))
                .isInstanceOf(AuthenticationException.class);
    }

    @Test
    void When_ValidateUserDetails_With_Invalid_Username_Expect_Not_Throw_AuthenticationException() {
        var validUserDto = UserMocks.createValidUser();
        userDetailsJwtService.validateUserDetails(validUserDto);
    }
}