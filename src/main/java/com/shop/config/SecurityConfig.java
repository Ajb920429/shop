package com.shop.config;

import com.shop.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity // // WebSecurityConfigurerAdapter 를 상속 받는 클래스에 @EnableWebSecurity 어노테이션을 선언하면
public class SecurityConfig extends WebSecurityConfigurerAdapter { //  SpringSecurityFiterChain 이 자동으로 포함됩니다. WebSecurityConfigurerAdapter 를 상속받아서 오버라이딩을 통해 보안 설정을 커스터마이징 할 수 있다.

    @Autowired
    MemberService memberService;

    @Override
    protected void configure(HttpSecurity http) throws Exception { // http 요청에 대한 보안설정을 합니다.
//        http.formLogin()
//                .loginPage("/members/login") // 로그인 페이지 URL 설정
//                .defaultSuccessUrl("/") // 로그인 성공 시 이동할 URL 설정
//                .usernameParameter("email") // 로그인 시 사용할 파라미터로 이메일을 지정
//                .failureUrl("/members/login/error") // 로그인 실패시 이동할 URL 지정
//                .and()
//                .logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout")) // 로그아웃 URL 설정
//                .logoutSuccessUrl("/") // 로그아웃 설공시 이동할 URL 설정
//        ;
//
//        http.authorizeRequests()
//                .mvcMatchers("/", "/members/**", "/item/**", "/images/**").permitAll()
//                .mvcMatchers("/admin/**").hasRole("ADMIN")
//                .anyRequest().authenticated()
//        ;
//
////        http.exceptionHandling()
////                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
////        ;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() { // 데이터 베이스가 해킹당하면 그대로 노출 되기 때문에 암호화하여 저장장
//       return new BCryptPasswordEncoder();
//    } //
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception { //
//        auth.userDetailsService(memberService)
//                .passwordEncoder(passwordEncoder());
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
//    }

}