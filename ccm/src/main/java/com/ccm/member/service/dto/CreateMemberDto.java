package com.ccm.member.service.dto;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.ccm.member.domain.Member;

/**
 * Created by ShinD on 2022/05/14.
 */
public class CreateMemberDto {

	private String username;
	private String password;
	private String name;

	public CreateMemberDto(String username, String password, String name) {
		this.username = username;
		this.password = password;
		this.name = name;
	}

	public Member toEntity() {
		return Member.builder().username(username).password(password).name(name).build();
	}

	public void encodePassword(PasswordEncoder passwordEncoder) {
		this.password = passwordEncoder.encode(this.password);
	}
}
