package com.hotelManagement.HotelServer.util;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
        private String secret = "";
    // Strategy to create a good private secretKey to build a Jwt on demand
    // more recommended than use hard-coded or enviremental values
     public JwtUtil() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey key = keyGen.generateKey();
            secret = Base64.getEncoder().encodeToString(key.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } 
     }

    // this method task is to generate a Token from the extracted claim coming from the front-end
    private String generateToken(Map<String, Object> extraClaims, UserDetails details) {
        return Jwts.builder().setClaims(extraClaims).setSubject(details.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*24))
                .signWith(SignatureAlgorithm.HS256, getSigningKey())
                .compact();
    }
   // this method will create a token new from the POV of backend once the app is lunched
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public boolean isValidToken(String token,  UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExperied(token);
    }

    private Claims extractAllClaims(String token) {
        Jws<Claims> jws = Jwts.parser()
                .setSigningKey(getSigningKey())
                .parseClaimsJws(token);
                
        return jws.getBody();
    }
    //  Here calling the interface function allows to access the method apply
    // that allow us to excecute a function within a context similar to the .bind() 
    //  function in JS
    private <T>  T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims =  extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExperied(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Key getSigningKey() {
        byte[] keyBytes = Base64.getDecoder().decode(secret);
        SignatureAlgorithm alg = SignatureAlgorithm.HS256;

        // here to best case is to use the method hmacShaKeyFor from Keys class 
        // for to avoid the deprecated error of getMinKeyLength() from 
        // SignatureAlgorithm not found is better to direclty output the 
        // desire result from the base architecture 

        return new SecretKeySpec(keyBytes, alg.getJcaName());
    }
}
