package br.com.api.mock;

import br.com.api.dto.UserDTO;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

public class UserMocks {
    public static UserDTO createInvalidUser() {
        return UserDTO.builder()
                .username("test")
                .password("test")
                .build();
    }

    public static UserDTO createValidUser() {
        return UserDTO.builder()
                .username("admin")
                .password("admin")
                .build();
    }

    public static UserDetails createValidUserDetails() {
        return User.builder()
                .username("admin")
                .password("admin")
                .authorities(new ArrayList<>())
                .build();
    }
}
