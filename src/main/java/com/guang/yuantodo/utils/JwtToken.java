package com.guang.yuantodo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.guang.yuantodo.utils.response.CustomException;
import com.guang.yuantodo.utils.response.ResultData;
import com.guang.yuantodo.utils.response.CustomHttpStatus;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtToken {

    private static long expireTime = 100*24*3600;
    private static String secret = "yuanTodo";
    private static String prefix = "Bearn ";


    public static String createToken(){
        return  JWT.create()
                .withSubject("jwt")
                .withExpiresAt(new Date(System.currentTimeMillis() + expireTime))
                .sign(Algorithm.HMAC512(secret));
    }

    public static String validateToken(String token) throws Exception {
        if (null == token) {
            token = "";
        }
        try {
            return JWT.require(Algorithm.HMAC512(secret))
                    .build()
                    .verify(token.replace(prefix, "")).getSubject();
        } catch (TokenExpiredException e){
            throw new CustomException(CustomHttpStatus.AUTHENTICATION_EXPIRE);
        } catch (Exception e){
            throw new CustomException(CustomHttpStatus.AUTHENTICATION_FAILED);
        }
    }

}

