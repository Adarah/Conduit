package com.mag.conduit.application;

import com.mag.conduit.application.dto.UserData;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JwtService {

    @Value("${jwt.expiration.milliseconds}")
    private int timeToExpiration;

    @Value("${jwt.secret.key}")
    private String secretKey;

    public String generateToken(UserData userData) {
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .setSubject(userData.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(getExpiration())
                .compact();
    }

    private Date getExpiration() {
        return new Date(System.currentTimeMillis() + timeToExpiration);
    }

    public Optional<String> getSubFromToken(String jwt) {
        try {
            return Optional.of(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody().getSubject());
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

}
