package com.ac.gateway.util;

import com.ac.gateway.response.ResponseCodeEnum;
import com.ac.gateway.response.TokenAuthenticationException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

/**
 * @author Alan Chen
 * @description
 * @date 2020/11/10
 */

public class JWTUtil {

    public static final long TOKEN_EXPIRE_TIME = 7200 * 1000;
    private static final String ISSUER = "cheng";

     public static String generateToken(String username, String secretKey) {
         Algorithm algorithm = Algorithm.HMAC256(secretKey);
         Date now = new Date();
         Date expireTime = new Date(now.getTime() + TOKEN_EXPIRE_TIME);

         String token = JWT.create()
                 .withIssuer(ISSUER)
                 .withIssuedAt(now)
                 .withExpiresAt(expireTime)
                 .withClaim("username", username)
                 .sign(algorithm);

         return token;
     }

     public static void verifyToken(String token, String secretKey) {
         try {
                 Algorithm algorithm = Algorithm.HMAC256(secretKey);
                 JWTVerifier jwtVerifier = JWT.require(algorithm).withIssuer(ISSUER).build();
                 jwtVerifier.verify(token);
             } catch (JWTDecodeException jwtDecodeException) {
                 throw new TokenAuthenticationException(ResponseCodeEnum.TOKEN_INVALID.getCode(), ResponseCodeEnum.TOKEN_INVALID.getMessage());
             } catch (SignatureVerificationException signatureVerificationException) {
                 throw new TokenAuthenticationException(ResponseCodeEnum.TOKEN_SIGNATURE_INVALID.getCode(), ResponseCodeEnum.TOKEN_SIGNATURE_INVALID.getMessage());
             } catch (TokenExpiredException tokenExpiredException) {
                 throw new TokenAuthenticationException(ResponseCodeEnum.TOKEN_EXPIRED.getCode(), ResponseCodeEnum.TOKEN_INVALID.getMessage());
             } catch (Exception ex) {
                 throw new TokenAuthenticationException(ResponseCodeEnum.UNKNOWN_ERROR.getCode(), ResponseCodeEnum.UNKNOWN_ERROR.getMessage());
             }
     }

     public static String getUserInfo(String token) {
         DecodedJWT decodedJWT = JWT.decode(token);
         String username = decodedJWT.getClaim("username").asString();
         return username;
    }
}
