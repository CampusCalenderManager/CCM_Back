package com.ccm.config;

import javax.servlet.Filter;

import com.ccm.auth.filter.JsonUsernamePasswordLoginFilter;
import com.ccm.auth.filter.JwtAuthenticationFilter;
import com.ccm.auth.jwt.JwtService;
import com.ccm.member.repository.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;

/**
 * Created by ShinD on 2022/05/14.
 */
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	private final ObjectMapper objectMapper;
	private final JwtService jwtService;
	private final MemberRepository memberRepository;


	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
			.formLogin().disable()//formLogin 인증방법 비활성화
			.httpBasic().disable()//httpBasic 인증방법 비활성화(특정 리소스에 접근할 때 username과 password 물어봄)
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

			.and()
			.authorizeRequests()
			.antMatchers("/login", "/signUp","/").permitAll()
			.anyRequest().authenticated();

		http.addFilterAfter(jsonUsernamePasswordLoginFilter(), LogoutFilter.class);
		http.addFilterBefore(jwtAuthenticationFilter(), JsonUsernamePasswordLoginFilter.class);


	}

	private Filter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter(jwtService, memberRepository);
	}

	private Filter jsonUsernamePasswordLoginFilter() throws Exception {
		return new JsonUsernamePasswordLoginFilter(authenticationManager(), objectMapper, jwtService);
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}


}
