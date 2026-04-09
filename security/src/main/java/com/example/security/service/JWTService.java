package com.example.security.service;

import com.mysql.cj.util.Base64Decoder;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.hibernate.annotations.SecondaryRow;

import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {
    private static final String SECRET = "mysecretkeymysecretkeymysecretkey12";;
    private Key getKey() {
        byte[] keyBytes = SECRET.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }
// 32+ chars



    public String generateToken(String userName) throws NoSuchAlgorithmException {
        Map<String,Object> claims=new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*3))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();

    }



    public String extractUserName(String token) throws NoSuchAlgorithmException {
        //extract the userName

       return extractClaim(token, Claims::getSubject);
    }
    private <T> T extractClaim(String token, Function<Claims,T> claimResolver) throws NoSuchAlgorithmException {
        final Claims claims=extractALlClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractALlClaims(String token) throws NoSuchAlgorithmException {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build().parseClaimsJws(token).getBody();
    }

    public boolean validateToken(String token, UserDetails userDetails) throws NoSuchAlgorithmException {
        String username=extractUserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpire(token));

    }

    private boolean isTokenExpire(String token) throws NoSuchAlgorithmException {
        return extractExpirationDate(token).before(new Date());
    }
    private Date extractExpirationDate(String token) throws NoSuchAlgorithmException {
        return extractClaim(token,Claims::getExpiration);
    }
}
