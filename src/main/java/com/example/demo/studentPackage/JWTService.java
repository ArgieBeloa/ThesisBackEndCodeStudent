package com.example.demo.studentPackage;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

    @Value("${app.admin.key:default_admin_key}")
    private String adminKey;

    @Value("${jwt.secret:}")
    private String jwtSecret;

    private final String fallbackSecret;

    public JWTService() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk = keyGen.generateKey();
            this.fallbackSecret = Base64.getEncoder().encodeToString(sk.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // ✅ Admin key verification
    public boolean isAdminKey(String key) {
        return key != null && key.trim().equals(adminKey.trim());
    }

    // ✅ Token generation with role
    public String generateToken(String username, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role); // 👈 Add role claim

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
                .and()
                .signWith(getKey())
                .compact();
    }

    // 🧩 Backward-compatible overload (defaults to STUDENT)
    public String generateToken(String username) {
        return generateToken(username, "STUDENT");
    }

    // ✅ Extract role from JWT
    public String extractRole(String token) {
        try {
            return extractAllClaims(token).get("role", String.class);
        } catch (Exception e) {
            return null; // No role claim present
        }
    }

    // ✅ Extract username (studentNumber)
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // ✅ Generic claim extractor
    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // ✅ Token validation
    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // ✅ Key resolver
    private SecretKey getKey() {
        String keyToUse = (jwtSecret != null && !jwtSecret.isEmpty()) ? jwtSecret : fallbackSecret;
        byte[] keyBytes = Decoders.BASE64.decode(keyToUse);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
