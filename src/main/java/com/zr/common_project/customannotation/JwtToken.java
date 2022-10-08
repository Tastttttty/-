package com.zr.common_project.customannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface JwtToken {
	//属性
	//1.加上这个注解默认是true 就必须要通过jwt去验证
	boolean required() default true;
}
