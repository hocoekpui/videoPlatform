package com.hezb.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hezb.exception.CustomizeException;
import com.hezb.exception.enums.BaseExceptionEnum;
import com.hezb.exception.enums.UserExceptionEnum;

import java.util.Calendar;
import java.util.Date;

public class TokenUtil {

    public static final String ISSUER = "签发者";

    public static String generateToken(Long userId) throws Exception {
        Algorithm algorithm = Algorithm.RSA256(RSAUtil.getPublicKey(), RSAUtil.getPrivateKey());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, 30);
        return JWT.create().withKeyId(String.valueOf(userId)).withIssuer(ISSUER).withExpiresAt(calendar.getTime()).sign(algorithm);
    }

    public static Long verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.RSA256(RSAUtil.getPublicKey(), RSAUtil.getPrivateKey());
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            String userId = jwt.getKeyId();
            return Long.valueOf(userId);
        } catch (TokenExpiredException tokenExpiredException) {
            throw new CustomizeException(UserExceptionEnum.USER_TOKEN_EXCEED.getErrorCode(), UserExceptionEnum.USER_TOKEN_EXCEED.getErrorMessage());
        } catch (Exception exception) {
            throw new CustomizeException(BaseExceptionEnum.FAIL.getErrorCode(), "非法身份认证信息");
        }
    }
}
