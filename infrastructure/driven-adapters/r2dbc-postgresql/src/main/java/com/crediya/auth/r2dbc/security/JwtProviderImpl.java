package com.crediya.auth.r2dbc.security;

import com.crediya.auth.model.jwt.JwtProvider;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;


public class JwtProviderImpl implements JwtProvider {

    private Key key;
    private final long expiration;

    public JwtProviderImpl(String secret, long expiration) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes()); // genera una key segura desde el secret
        this.expiration = expiration;
    }

    @Override
    public String generateToken(String dni, String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("dni", dni)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public String getEmailFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    @Override
    public String getRoleFromToken(String token) {
        Object role = Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().get("role");
        return role == null ? null : role.toString();
    }
}
