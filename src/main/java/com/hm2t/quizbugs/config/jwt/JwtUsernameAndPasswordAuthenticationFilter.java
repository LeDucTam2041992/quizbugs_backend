package com.hm2t.quizbugs.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hm2t.quizbugs.config.jwt.model.CustomUserDetail;
import com.hm2t.quizbugs.config.jwt.model.LoginRequest;
import com.hm2t.quizbugs.model.users.AppUserToken;
import com.hm2t.quizbugs.service.users.Impl.UserTokenServiceImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.security.auth.message.AuthException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserTokenServiceImpl appUserTokenService;

    public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager,
                                                      JwtTokenProvider jwtTokenProvider, UserTokenServiceImpl appUserTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.appUserTokenService = appUserTokenService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            LoginRequest loginRequest = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()
            );

            return authenticationManager.authenticate(authenticationToken);
        } catch (AuthenticationException e) {
            throw new AuthenticationCredentialsNotFoundException(e.getLocalizedMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        CustomUserDetail principal = (CustomUserDetail) authResult.getPrincipal();
        String token = jwtTokenProvider.generateToken(principal);
        appUserTokenService.save(new AppUserToken(principal.getAppUser(), token));
        response.addHeader(HttpHeaders.AUTHORIZATION, jwtTokenProvider.getJWT_TOKEN_PREFIX() + token);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(
                "{\"" + HttpHeaders.AUTHORIZATION + "\":\"" + jwtTokenProvider.getJWT_TOKEN_PREFIX() + token + "\"," +
                        "" + "\"" + "ROLE" + "\":\"" + principal.getAuthorities() +
                        "\"}"
        );
    }


}
