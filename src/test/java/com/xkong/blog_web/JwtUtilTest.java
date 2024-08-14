package com.xkong.blog_web;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: 行空XKong
 * Date: 2024-07-30
 * Time: 19:26
 * Version:
 */
public class JwtUtilTest {
    //                                      1s    1m   1h   1d  0.5m
    private static final long expiration = 1000 * 60 * 60 * 24 * 15;
    private static final String secretString = "r7MMGOcdN8HnN9K0rzKpK8C0PFzv9bUzDWX9znMgue8=";
    private static final Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretString));

    @Test
    public void genToken() {
        // 根据 key 生成 token
        Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretString));
        Map<String, Object> claim = new HashMap<>();
        claim.put("id", 5);
        claim.put("name", "zhangsan");

        String token = Jwts.builder()
                .setClaims(claim)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key)
                .compact();
        System.out.println(token);
    }

    @Test
    public void genKey() {
        // 随机生成 key
        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String key = Encoders.BASE64.encode(secretKey.getEncoded());
        System.out.println(key);
    }

    @Test
    public void parseToken() {
        // 校验
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiemhhbmdzYW4iLCJpZCI6NSwiZXhwIjoxNzIzNjQwMDQzfQ.P433Vv7TIHr7MNLaXNdVTGaurN8VzBr_Cp6YuYAEieY";
        JwtParser build = Jwts.parserBuilder().setSigningKey(key).build();
        Claims body = build.parseClaimsJws(token).getBody();
        System.out.println(body);
    }
}
