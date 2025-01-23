package com.edu.springboot.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;


@Configuration
public class WebSecurityConfig {
	
	/*
	 * 인증 실패에 대한 핸들러를 제작했다면 사용을 위해 빈을 자동주입한다.
	 * 그리고 시큐리티 설정부분에 failureHandler()메서드 추가
	 */
	@Autowired
	public MyAuthFailureHandler myAuthFailureHandler;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http)
		throws Exception{
		http.csrf((csrf)-> csrf.disable())
			.cors((cors)-> cors.disable())
			.authorizeHttpRequests((request) -> request
					.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
					.requestMatchers("/").permitAll()
					.requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
					.requestMatchers("/guest/**").permitAll()
					.requestMatchers("/member/**").hasAnyRole("USER", "ADMIN")
					.requestMatchers("/admin/**").hasRole("ADMIN")
					.anyRequest().authenticated()
			);
		
		/*
		 * 로그인 페이지에 대한 디자인 커스터마이징 설정
		 * loginPage : 로그인 페이지의 요청명
		 * loginProcessingUrl : 폼값을 전송하여 로그인 처리할 요청명
		 * failureUrl : 로그인에 실패한 경우 이동할 요청명
		 * failureHandler : 별도의 핸들러 빈을 등록 후 에러 처리
		 * usernameParameter : 아이디 입력을 위한 <input>의 name 속성값
		 * passwordParameter : 패스워드 입력을 위한 name 속성값
		 */
		http.formLogin((formLogin) -> formLogin.
				loginPage("/myLogin.do") // default : /login
				.loginProcessingUrl("/myLoginAction.do")
//				.failureUrl("/myError.do") // default : login?error
				.failureHandler(myAuthFailureHandler) 
				.usernameParameter("my_id") // default : username
				.passwordParameter("my_pass") // default : password
				.permitAll()
				);
		
		/*
		 * 로그아웃에 대한 커스터마이징
		 * logoutUrl : 로그아웃을 위한 요청명
		 * logoutSuccessUrl : 로그아웃 이후 이동할 페이지
		 */
		http.logout((logout) -> logout
				.logoutUrl("/myLogout.do") // default : logout
				.logoutSuccessUrl("/")
				.permitAll());
		
		/*
		 * 권한이 부족한 경우 이동할 위치 지정
		 */
		http.exceptionHandling((expHandling)->expHandling
				.accessDeniedPage("/denied.do"));
		
		return http.build();
	}
	
	@Bean
	public UserDetailsService users() {
		UserDetails user = User.builder()
				.username("user")
				.password(passwordEncoder().encode("1234"))
				.roles("USER")
				.build();
		UserDetails admin = User.builder()
				.username("admin")
				.password(passwordEncoder().encode("1234"))
				.roles("USER", "ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(user, admin);
	}
	
	// 패스워드 인코더 : 패스워드를 저장하기 전 암호화한다.
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

}
