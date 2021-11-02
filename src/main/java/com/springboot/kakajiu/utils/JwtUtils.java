package com.springboot.kakajiu.utils;

import com.springboot.kakajiu.pojo.User;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * @author zlyang
 * @date: 2021-10-31
 * @Discription: 生成Token的工具类
 */
@Slf4j
public class JwtUtils {

    private static final String SECRET = "SljkaDfdsd";
    /** Token 有效时长 多少秒 **/
    private static final int EXPIRATION = 60 * 60;

    private static Key getKeyInstance(){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] bytes = DatatypeConverter.parseBase64Binary(SECRET);
        return new SecretKeySpec(bytes,signatureAlgorithm.getJcaName());
    }

    public static String generatorToken(User user){
        return generatorToken(user, EXPIRATION);
    }

    public static String generatorToken(User user, int expire){
        Date date = new Date(System.currentTimeMillis() + expire * 1000);
        String token = Jwts.builder()
                .setIssuer("kakajiu springboot backend")
                .setAudience(user.getUsername())
                .claim("userId", user.getUserId())
                .claim("roles", user.getRoles())
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS256, getKeyInstance())
                .compact();
        return token;
    }

    public static User validationToken(String token){
        try{
            Claims claims = Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(token).getBody();
            User user = new User();
            user.setUserId(Integer.parseInt(claims.get("userId").toString()));
            user.setUsername(claims.getAudience());
            user.setRoles(claims.get("roles").toString());
            return user;
        } catch (ExpiredJwtException e){
            log.info("ExpiredJwtException" + e.getMessage());
            return null;
        } catch (UnsupportedJwtException | MalformedJwtException e){
            log.warn("UnsupportedJwtException or MalformedJwtException" + e.getMessage());
            return null;
        }
    }

}
