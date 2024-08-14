package com.xkong.blog_web.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;
import java.util.Date;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: 行空XKong
 * Date: 2024-07-30
 * Time: 19:14
 * Version:
 */
@Slf4j
public class JwtUtils {
    //                                      1s    1m   1h   1d  0.5m
    private static final long expiration = 1000 * 60 * 60 * 24 * 15;// 有效时间
    private static final String secretString = "r7MMGOcdN8HnN9K0rzKpK8C0PFzv9bUzDWX9znMgue8=";
    private static final Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretString));

    public static String genToken(Map<String, Object> claim) {
        String token = Jwts.builder()
                .setClaims(claim)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key)
                .compact();
        return token;
    }

    public static Claims parseToken(String token) {
        if (token == null) {
            return null;
        }
        JwtParser build = Jwts.parserBuilder().setSigningKey(key).build();
        Claims claims = null;
        try {
            claims = build.parseClaimsJws(token).getBody();
        } catch (Exception e) {
            log.error("token 解析失败, token:{}", token);
        }
        return claims;
    }

    public static Integer getUserIdFromToken(String token) {
//        System.out.println(token);
        Claims claims = parseToken(token);
//        System.out.println(claims);
        if (claims == null) {
            return null;
        }
        return (Integer) claims.get("userId");
    }
}
