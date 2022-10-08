package com.zr.common_project.custominterceptor;

import java.lang.reflect.Method;
import java.util.Base64;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.zr.common_project.customannotation.JwtToken;



public class TokenInteceptor implements HandlerInterceptor {
	
    @Value("${app.secretKey}")
    private String appKey = null;
    
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    		throws Exception {
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        response.setContentType("text/html;charset=utf8");
        // 如果方法上有这个注解我们就要验证jwt，没有就直接通过
        if(method.isAnnotationPresent(JwtToken.class)) {
        	String token = request.getHeader("token");
        	System.out.println(token);
        	// 如果客户端携带的token是空的，但是方法上的注解又是有jwt验证的
        	if(token == null) {
        		 JwtToken jwtToken = method.getAnnotation(JwtToken.class);
        		 if(jwtToken.required() == true) {
        			 response.getWriter().write("header没有携带token");
        			 return false;
        		 }
        	//如果客户端携带的token不是空的,验证token的有效性
        	}else {
        		
        		try {
                    String info = JWT.decode(token).getClaims().get("info").asString();
                    Algorithm algorithm = Algorithm.HMAC256(appKey);
                    JWTVerifier verifier = JWT.require(algorithm)
                            .withClaim("info", info)
                            .build();
                    //验证
                    DecodedJWT jwt = verifier.verify(token);
                    return  true;
				} catch (JWTVerificationException exception) {
					// TODO: handle exception
					response.getWriter().write("token解析失败");
					return  false;
				}
        	}
        }
    	return true;
    }

}
