package com.camcokback.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.beans.Customizer;
import java.util.Arrays;

@Configuration
@Log4j2
@RequiredArgsConstructor
public class CustomSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("***********************  security config ********************** " + http);


        //cors 설정
        http.cors(httpSecurityCorsConfigurer -> {
            httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource());
        });
        //서버 내부에서 세션을 생성하지 않도록 추가
        // (api 서버는 무상태(stateless)를 기본으로 사용하기 때문), stateful: 상태 유지
        // 무상태: 클라이언트와 서버간의 통신에 필요한 모든 상태 정보들은 클라이언트에서 가지고 있다.
        http.sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS));



        //csrf 비활성화 - csrf의 기능 전체를 비활성화
        http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());
        //csrf 보호가 필요하지 않은 특정 엔드포인트만 비활성화
//        http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.ignoringRequestMatchers("/허용할경로/*"));

        /*
        //csrf 기능 활성화
              토큰은 서버에 의해 생성되어 클라이언트의 세션에 저장되고, 폼을 통해서 서버로 전송되는
              모든 변경 요청에 포함되어야하며, 서버는 이 토큰을 검증하여 요청의 유효성을 확인
              기본 설정은 'GET', 'HEAD', 'TRACE', 'OPTIONS'와 같은 안전한 메소드를 무시하고
              POST, PUT, DELETE와 같은 변경 요청 메소드에 대해서만 CSRF 토큰 검사 수행
              CSRF 토큰은 브라우저에 의해 자동으로 포함되지 않는 요청 부분에 위치해야 하며,
              즉, HTTP 매개변수나 헤더에 실제 CSRF 토큰을 요구하는 것이 CSRF 공격을 방지하는 데
              효과적
              반면에 쿠키에 토큰을 요구하는 것은 부라우저가 쿠키를 자동으로 요청에 포함시키기 때문에
              안전하지 않다.
              http.csrf(Customizer.withDefaults()); //별도 설정하지 않아도 활성화 상태로 초기화 됨
              return http.build();
        */

        return  http.build();
    }

    //PasswordEncoder 설정 - 스프링 시큐리티는 사용자의 패스워드에 PasswordEncoder를 설정해 주어야 한다 .
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public  CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE","OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
//
//@Configuration
//@Log4j2
//@RequiredArgsConstructor
//public class CustomSecurityConfig {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        log.info("***********************  security config ********************** " + http);
//
//        //CORS 설정
//        http.cors(httpSecurityCorsConfigurer -> {
//            httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource());
//        });
//
//        //서버 내부에서 세션을 생성하지 않도록 추가 ( api 서버는 무상태(Stateless)를 기본으로 사용하기 때문엔
//        // 무상태 : 클라이언트와 서버간의 통신에 필요한 모든 상태 정보들은 클라이언트에서 가지고 있다가 서버와 통신할때 데이터를 실어 보내는 것
//        http.sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(
//                SessionCreationPolicy.STATELESS));
//
//        //csrf비활성화
//        http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());
//        return  http.build();
//    }
//
//    //PasswordEncoder 설정
//    @Bean
//    public PasswordEncoder  passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//
//        CorsConfiguration configuration = new CorsConfiguration();
//
//        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
//        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE","OPTIONS" +
//                ""));
//        configuration.setAllowedHeaders(Arrays.asList("Authorization","Cache-Control", "Content-Type"));
//        configuration.setAllowCredentials(true);
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
//}