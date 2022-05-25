package br.com.api.service;

import br.com.api.dto.UserDTO;
import br.com.api.exception.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsJwtService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var expectedUser = mockUser();
        if(!expectedUser.getUsername().equals(username)) {
            return null;
        }
        return expectedUser;
    }

    public void validateUserDetails(UserDTO userDto) {
        var userDetails = this.loadUserByUsername(userDto.getUsername());
        if(userDetails == null || !userDetails.getPassword().equals(userDto.getPassword())) {
            throw new AuthenticationException("User not authenticated");
        }
    }

    private UserDetails mockUser() {
        return User.builder()
                .username("admin")
                .password("admin")
                .authorities(new ArrayList<>())
                .build();
    }
}
