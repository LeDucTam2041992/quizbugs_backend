package com.hm2t.quizbugs.config.jwt;


import com.hm2t.quizbugs.config.jwt.model.CustomUserDetail;
import com.hm2t.quizbugs.service.users.Impl.UserTokenServiceImpl;
import io.jsonwebtoken.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
@Data
public class JwtTokenProvider {
    private final String JWT_SECRET = "HM2T";
    private final String JWT_TOKEN_PREFIX = "Bearer ";
    private final long JWT_EXPIRATION = 30 * 24 * 60 * 60 * 1000L;

    @Autowired
    private UserTokenServiceImpl userTokenService;

    public String generateToken(CustomUserDetail userDetails) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
        return Jwts.builder()
                .setSubject(Long.toString(userDetails.getAppUser().getId()))
                .claim("authorities", userDetails.getAuthorities())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            this.userTokenService.removeAppToken(authToken);
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        } catch (Exception e) {
            log.error("Exception : " + e.getClass() + " : " + e.getMessage());
        }
        return false;
    }
}
