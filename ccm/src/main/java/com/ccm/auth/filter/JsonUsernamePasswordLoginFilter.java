package com.ccm.auth.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StreamUtils;

import com.ccm.auth.jwt.JwtService;
import com.ccm.member.domain.Member;
import com.ccm.member.exception.MemberException;
import com.ccm.member.exception.MemberExceptionType;
import com.ccm.member.repository.MemberRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;

/**
 * Created by ShinD on 2022/05/14.
 */
public class JsonUsernamePasswordLoginFilter extends UsernamePasswordAuthenticationFilter {

	private final ObjectMapper objectMapper;
	private final JwtService jwtService;
	private final MemberRepository memberRepository;

	public JsonUsernamePasswordLoginFilter(AuthenticationManager authenticationManager,
		ObjectMapper objectMapper,
		JwtService jwtService,
		MemberRepository memberRepository) {
		super(authenticationManager);
		this.jwtService = jwtService;
		this.objectMapper = objectMapper;
		this.memberRepository = memberRepository;
	}



	@SneakyThrows
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
		throws AuthenticationException {
		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}


		Map<String, String> authMap = objectMapper.readValue(getMessageBody(request), new TypeReference<Map<String, String>>() {});


		String username = authMap.get(SPRING_SECURITY_FORM_USERNAME_KEY);
		username = (username != null) ? username.trim() : "";

		String password =  authMap.get(SPRING_SECURITY_FORM_PASSWORD_KEY); //UsernamePasswordAuthenticationFilter 에서 정의되어있음
		password = (password != null) ? password : "";

		return this.getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(username, password));
	}

	private String getMessageBody(HttpServletRequest request) throws IOException {
		return StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
	}




	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
		jwtService
			.sendToken(response , jwtService.createAccessToken(
											Long.parseLong(((User) authResult.getPrincipal()).getUsername())));//getUsername 에는 회원의 PK, 즉 id 가 들어있음
		Member member = memberRepository
			.findById(Long.parseLong(((User)authResult.getPrincipal()).getUsername()))
			.orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND));

		HashMap<String ,String> infoMap = new HashMap<>();
		infoMap.put("username", member.getUsername());
		infoMap.put("name", member.getName());
		infoMap.put("id", member.getId().toString());

		response.getWriter().write(objectMapper.writeValueAsString(infoMap));
	}
}
