package com.zr.common_project.api;

import java.util.Date;

import javax.sql.DataSource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.zr.common_project.customannotation.JwtToken;

@RestController
@RequestMapping("/api")
@Api(tags = "api")
public class HelloApi {
	
	private final long EXPIRE_TIME = 10 * 60 * 1000;
	
    @Value("${app.secretKey}")
    private String appKey = null;
	
	@Autowired 
	private  DataSource  dataSource;
	
	@GetMapping("/hello") // 匹配请求
	@JwtToken
	@ApiOperation("1")
	public String hello() {
		System.out.println(dataSource.getClass());
		return "hello world";  //做回复
	}
	
	@ApiOperation("2")
	@GetMapping("/hello1") // 匹配请求
	@JwtToken
	public String hello1() {
		return "xxxxx";  //做回复
	}
	
	@ApiOperation("login")
	@GetMapping("/login") // 匹配请求
	public String login() {
		//假定已经在数据库验证成功了
	       try {
	            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
	            Algorithm algorithm = Algorithm.HMAC256(appKey);
	            return JWT.create()
	                    // 将 user id 保存到 token 里面
	                    .withAudience("abc")
	                    // 十分钟后token过期
	                    .withExpiresAt(date)
	                    .withClaim("info", "wwj")
	                    // token 的密钥
	                    .sign(algorithm);
	        } catch (Exception e) {
	            return null;
	        }
	}
	
	
	
		

}
