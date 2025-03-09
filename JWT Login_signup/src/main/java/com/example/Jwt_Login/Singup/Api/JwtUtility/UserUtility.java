package com.example.Jwt_Login.Singup.Api.JwtUtility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.JwtException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserUtility {
    private String secretKey = "Nikon boy"; // Use a stronger secret key, preferably loaded from a secure source
    private long validityInMilliseconds = 3600000; // 1 hour validity

    // Generate JWT Token
    public String generateToken(String username) {
        Claims claims = (Claims) Jwts.claims().setSubject(username);
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    private Claims parseClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException | IllegalArgumentException e) {
            // Log the exception and handle invalid token cases
            return null;
        }
    }

    // Get username from token
    public String getUsernameFromToken(String token) {
        Claims claims = parseClaims(token);
        return claims != null ? claims.getSubject() : null;
    }

    // Check if token is expired
    public boolean isTokenExpired(String token) {
        Claims claims = parseClaims(token);
        return claims != null && claims.getExpiration().before(new Date());
    }

    // Validate the token
    public boolean validateToken(String token, String username) {
        String usernameFromToken = getUsernameFromToken(token);
        return (usernameFromToken != null && usernameFromToken.equals(username) && !isTokenExpired(token));
    }
}