package br.com.serasa.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
public final class JWTAuthenticationFilter extends GenericFilterBean {
    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";

    private final JWTSecurityUtil jwtSecurityUtil;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenHeader = ((HttpServletRequest) request).getHeader(AUTHORIZATION);
        String token = null;
        UserDetails userDetails = null;
        if(tokenHeader != null && tokenHeader.startsWith(BEARER)) {
            token = tokenHeader.substring(BEARER.length());
            userDetails = jwtSecurityUtil.getUserDetailsFromToken(token);
        }

        if(jwtSecurityUtil.isTokenValid(token)) {
           this.processUsernamePasswordAuthenticationToken(userDetails, (HttpServletRequest) request);
        }

        filterChain.doFilter(request, response);
    }

    private void processUsernamePasswordAuthenticationToken(UserDetails userDetails, HttpServletRequest request) {
        if(userDetails != null) {
            var userPasswordAuthToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
            );
            userPasswordAuthToken
                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(userPasswordAuthToken);
        }
    }


}
