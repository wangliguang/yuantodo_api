package com.guang.yuantodo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.guang.yuantodo.entity.User;
import com.guang.yuantodo.utils.response.ResultData;
import com.guang.yuantodo.utils.response.ReturnCode;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Supplier;

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
        try {
            return JWT.require(Algorithm.HMAC512(secret))
                    .build()
                    .verify(token.replace(prefix, "")).getSubject();
        } catch (TokenExpiredException e){
            throw new Exception(ResultData.fail(ReturnCode.AUTHENTICATION_FAILED).toString());
        } catch (Exception e){
            throw new Exception(ResultData.fail(ReturnCode.AUTHENTICATION_FAILED).toString());
        }
    }

}

