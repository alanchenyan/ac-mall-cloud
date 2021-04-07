package com.ac.gateway.response;

/**
 * @author Alan Chen
 * @description
 * @date 2020/11/10
 */
public class TokenAuthenticationException extends RuntimeException {

    public TokenAuthenticationException(){
        super();
    }

    public TokenAuthenticationException(int code,String message){
       super(code+message);
    }
}
