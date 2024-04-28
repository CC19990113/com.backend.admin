package com.backend.admin.utils;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    final static String jwtToken = "cccccc";//私钥

//    final static long TOKEN_EXP = 1000 * 60 * 10;//过期时间,测试使用十分钟



    //生成token
    public static String createToken(Long userId){	//参数为用户Id
        Map<String,Object> claims = new HashMap<>();
        claims.put("userId",userId);

        JwtBuilder jwtBuilder = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, jwtToken) // 签发算法，秘钥为jwtToken
                .setClaims(claims) // body数据，要唯一，自行设置
                .setIssuedAt(new Date()) // 设置签发时间
                .setExpiration(new Date(System.currentTimeMillis() + 24L * 60 * 60 * 60 * 1000));//过期时间 一天的有效时间

        return jwtBuilder.compact();
    }

    //检查token是否合法，合法则取出数据
    public static Map<String, Object> checkToken(String token){
        try {
            Jwt parse = Jwts.parser().setSigningKey(jwtToken).parse(token);
            return (Map<String, Object>) parse.getBody();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
