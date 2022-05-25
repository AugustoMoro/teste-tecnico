package br.com.serasa.security;

import br.com.serasa.dto.TokenDTO;
import br.com.serasa.dto.UserDTO;
import br.com.serasa.service.UserDetailsJwtService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JWTSecurityUtil {
    @Value("${security.token.expiration:30}")
    private Long expirationInMinutes;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.issuer}")
    private String jwtIssuer;

    private Algorithm algorithm;
    private JWTVerifier jwtVerifier;

    private final UserDetailsJwtService userDetailsJwtService;

    @PostConstruct
    private void initialize() {
        algorithm = Algorithm.HMAC512(this.jwtSecret);
        jwtVerifier = JWT.require(algorithm)
                .withIssuer(jwtIssuer)
                .build();
    }

    public TokenDTO authenticate(UserDTO userDto) {
        this.userDetailsJwtService.validateUserDetails(userDto);
        var token = this.generateToken(userDto);
        return new TokenDTO(token);
    }

    public boolean isTokenValid(String token) {
        try {
            if(token == null) {
                return false;
            }
            jwtVerifier.verify(token);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public UserDetails getUserDetailsFromToken(String token) {
        var username = this.getUserNameFromToken(token);
        return this.userDetailsJwtService.loadUserByUsername(username);
    }

    private String generateToken(UserDTO userDto) {
        var expirationDate = Date.from(LocalDateTime.now()
                .plusMinutes(this.expirationInMinutes).toInstant(ZoneOffset.UTC));
        return JWT.create()
                .withIssuedAt(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)))
                .withExpiresAt(expirationDate)
                .withSubject(userDto.getUsername())
                .withIssuer(jwtIssuer)
                .sign(this.algorithm);
    }

    private String getUserNameFromToken(String token) {
        return JWT.decode(token).getSubject();
    }
}
