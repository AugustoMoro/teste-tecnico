package br.com.api.config;

import br.com.api.security.JWTAuthenticationFilter;
import br.com.api.security.JWTSecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JWTSecurityUtil jwtSecurityUtil;

    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http.cors()
            .and()
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/").permitAll()
            .antMatchers(HttpMethod.POST, "/autenticar").permitAll()
            .antMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
            .antMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll()
            .antMatchers(HttpMethod.GET, "/swagger-ui.html/**").permitAll()
            .antMatchers(HttpMethod.GET, "/swagger-resources/**").permitAll()
            .antMatchers(HttpMethod.GET, "/webjars/springfox-swagger-ui/**").permitAll()
            .antMatchers(HttpMethod.GET, "/webjars/swagger-resources/**").permitAll()
            .antMatchers(HttpMethod.GET, "/v2/api-docs/**").permitAll()
            .antMatchers(HttpMethod.GET, "/csrf").permitAll()
            .antMatchers(HttpMethod.GET, "/**").authenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .anyRequest()
            .authenticated()
            .and()
            .addFilterBefore(new JWTAuthenticationFilter(jwtSecurityUtil), UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling()
            .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
            .and()
            .build();

    }
}