package com.example.sbtickets.authentication.service;



//import com.nimbusds.jose.JWSVerifier;
//import com.nimbusds.jose.crypto.MACVerifier;
//import com.nimbusds.jwt.JWTClaimsSet;
//import com.nimbusds.jwt.SignedJWT;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.stereotype.Service;
//import lombok.extern.slf4j.Slf4j;
//
//import java.util.Date;
//
//@Service
//public class JwtService {
//    public static final String USERNAME = "username";
//    public static final String SECRET_KEY = "11111111111111111111111111111111";
//    public static final int EXPIRE_TIME = 86400000;
//    public String generateTokenLogin(String username) {
//        String token = null;
//        try {
//            final Date createdDate = new Date();
//            final Date expirationDate = new Date(createdDate.getTime() + EXPIRE_TIME);
//            return Jwts.builder()
//                    .setSubject(username)
//                    .setIssuedAt(createdDate)
//                    .setExpiration(expirationDate)
//                    .signWith(SignatureAlgorithm.HS512, generateShareSecret())
//                    .compact();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//    private JWTClaimsSet getClaimsFromToken(String token) {
//        JWTClaimsSet claims = null;
//        try {
//            SignedJWT signedJWT = SignedJWT.parse(token);
//            JWSVerifier verifier = new MACVerifier(generateShareSecret());
//            if (signedJWT.verify(verifier)) {
//                claims = signedJWT.getJWTClaimsSet();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return claims;
//    }
//    private Date generateExpirationDate() {
//        return new Date(System.currentTimeMillis() + EXPIRE_TIME);
//    }
//    private Date getExpirationDateFromToken(String token) {
//        Date expiration = null;
//        JWTClaimsSet claims = getClaimsFromToken(token);
//        expiration = claims.getExpirationTime();
//        return expiration;
//    }
//    public String getUsernameFromToken(String token) {
//        String username = null;
//        try {
//            JWTClaimsSet claims = getClaimsFromToken(token);
//            username = claims.getStringClaim(USERNAME);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return username;
//    }
//    private byte[] generateShareSecret() {
//        // Generate 256-bit (32-byte) shared secret
//        byte[] sharedSecret = new byte[32];
//        sharedSecret = SECRET_KEY.getBytes();
//        return sharedSecret;
//    }
//    private Boolean isTokenExpired(String token) {
//        Date expiration = getExpirationDateFromToken(token);
//        return expiration.before(new Date());
//    }
//    public Boolean validateTokenLogin(String token) {
//        if (token == null || token.trim().length() == 0) {
//            return false;
//        }
//        String username = getUsernameFromToken(token);
//        if (username == null || username.isEmpty()) {
//            return false;
//        }
//        if (isTokenExpired(token)) {
//            return false;
//        }
//        return true;
//    }
//}

//package vn.com.nsmv.config.security;
import java.util.Date;

import com.example.sbtickets.authentication.entity.User;
import org.apache.logging.log4j.Logger; import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
//import vn.com.nsmv.entity.User;

@Component
@Slf4j

public class JwtService {
    private static final Logger logger = LogManager.getLogger(JwtService.class);
    // Đoạn JWT_SECRET này là bí mật, chỉ có phía server biết
    private final String JWT_SECRET = "JWT_SECRET_1584547915594";

    //Thời gian có hiệu lực của chuỗi jwt

    private final long JWT_EXPIRATION = 288000000;

    // Tạo ra jwt từ thông tin user
    public String generateToken(User userDetails) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
        // Tạo chuỗi json web token từ id của user.
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    // Lấy thông tin user từ jwt
    public String getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (Exception ex) {
            logger.error(ex.getMessage(),ex);
            return false;
        }
    }
}

