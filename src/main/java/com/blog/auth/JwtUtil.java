package com.blog.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

public class JwtUtil {
    private final Key secretKey;
    private final long tokenExpiration;

    public JwtUtil(String jwtSecret, long tokenExpiration) {
        this.secretKey = new SecretKeySpec(Base64.getDecoder().decode(jwtSecret),
                SignatureAlgorithm.HS256.getJcaName());
        this.tokenExpiration = tokenExpiration;
    }

    public String createToken(Long id, String email, String role) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + tokenExpiration * 1000);

        return Jwts.builder()
                .setSubject(id.toString())
                .claim("email", email)
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(secretKey)
                .compact();
    }

    public Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
