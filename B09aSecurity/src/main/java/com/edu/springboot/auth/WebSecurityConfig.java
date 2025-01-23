package com.edu.springboot.auth;

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


/*
 * 스프링 컨테이너가 시작될 때 빈이 생성되어야 하므로 기본패키지 하위에 정의한 후 어노테이션 부착
 * 스프링 시큐리티 사용을 위한 설정 클래스로 정의한다.
 */

@Configuration
public class WebSecurityConfig {
	
	/*
	 * Spring Security는 특정 페이지에 로그인 인증을 위한 코드를 삽입하지 않고
	 * 아래와 같이 요청명의 패턴을 통해 설정한다.
	 * 
	 * - permitAll() : 인증 없이 누구나 접근할 수 있는 페이지 지정
	 * - hasAnyRole() : 인증 후 권한을 획득해야 접근할 수 있는 페이지로 지정.
	 * 					단, 여러 권한 중 하나만 획득하면 접근 가능
	 * - hasRole() : hasAnyRole()과 비슷하지만 한 가지의 권한을 획득해야 접근 가능
	 */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http)
		throws Exception{
		/**
		 * 정적리소스 css,js,images폴더와 guest폴더는 권한없이 누구나 접근 - permitAll()
		 * member폴더 하위는 USER, ADMIN 둘 중 하나의 권한이 있어야 접근 가능
		 * admin 하위는 ADMIN 권한만 접근 가능
		 */
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
		 * 로그인 폼과 로그아웃은 디폴트로 제공되어 페이지와 요청명을 사용한다.
		 * 추가 커스터마이징(디자인)은 다음 예제에서
		 */
		http.formLogin((formLogin) -> formLogin.permitAll());
		
		http.logout((logout) -> logout.permitAll());
		
		return http.build();
	}
	
	/*
	 * 로그인 후 획득할 수 있는 권한에 대한 설정을 한다.
	 * USER와 ADMIN 권한을 획득하기 위한 아이디/비번을 메모리에 저장(인메모리 방식)
	 * DB에 저장하기 위해서는 별도의 과정이 또 필요
	 */
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
