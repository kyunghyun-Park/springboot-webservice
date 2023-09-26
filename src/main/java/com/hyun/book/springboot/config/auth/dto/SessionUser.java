package com.hyun.book.springboot.config.auth.dto;

import com.hyun.book.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

//인증된 사용자 정보만 필요하다. 필요한 정보만 변수로 갖고있음.
//세션에 사용자 정보를 저장하기 위한 Dto 클래스
@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }

}
