package com.hyun.book.springboot.config.auth;

import com.hyun.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //Spring Security 설정들을 활성화시켜 준다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()     //h2-console 화면을 사용하기 위해 해당 옵션들을 disabled 한다.
                .and()
                    .authorizeRequests()                //URL별 권한 관리를 설정하는 옵션의 시작점. 이게 선언되어야만 antMatchers 옵션을 사용할 수 있다.
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name()) //이 URL은 USER권한을 가진 사람만 접근할 수 있게 설정
                    .anyRequest().authenticated()       //설정된 값들 이외 나머지 URL들은 로그인한 사용자들이 접근 가능하게 설정
                .and()
                .logout()                               //로그아웃 기능에 대한 설정의 진입점
                        .logoutSuccessUrl("/")          //로그아웃 성공 시 루트 주소로 이동
                .and()
                    .oauth2Login()                      //로그인 기능에 대한 설정의 진입점
                        .userInfoEndpoint()                 //로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당
                            .userService(customOAuth2UserService); //소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록하는 부분
    }
}
