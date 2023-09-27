package com.hyun.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) //메소드의 파라미터오 선언된 객체에서만 사용가능하다.
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
}
