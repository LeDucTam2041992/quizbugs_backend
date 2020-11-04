package com.hm2t.quizbugs.config.jwt;


import com.hm2t.quizbugs.service.users.Impl.UserTokenServiceImpl;
import com.hm2t.quizbugs.service.users.Impl.UserServiceImpl;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.lang.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserServiceImpl appUserService;

    @Autowired
    UserTokenServiceImpl appUserTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String jwt = getJwtFromRequest(request);

        try {
            if (jwt==null || jwt.equals("")) {
                filterChain.doFilter(request, response);
                return;
            }

            boolean jwtVerified = StringUtils.hasText(jwt)
                    && tokenProvider.validateToken(jwt)
                    && appUserTokenService.isTokenExists(jwt);

            if (jwtVerified) {
                Long userId = tokenProvider.getUserIdFromJWT(jwt);

                UserDetails userDetails = appUserService.loadUserDetailById(userId);
                if (userDetails != null) {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails,
                                    null, userDetails.getAuthorities());

                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (JwtException ex) {
            log.error("failed on set user authentication", ex);
        } catch (Exception ex) {
            log.error("Error: " + this.getClass() + " " + ex.getLocalizedMessage());
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(tokenProvider.getJWT_TOKEN_PREFIX())) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
