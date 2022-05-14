package com.ccm.member.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ccm.member.service.dto.CreateMemberDto;

/**
 * Created by ShinD on 2022/05/14.
 */
public interface MemberService extends UserDetailsService {
	void create(CreateMemberDto createMemberDto);
}
