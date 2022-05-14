package com.ccm.member.service;

import static com.ccm.member.exception.MemberExceptionType.*;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ccm.member.domain.Member;
import com.ccm.member.exception.MemberException;
import com.ccm.member.exception.MemberExceptionType;
import com.ccm.member.repository.MemberRepository;
import com.ccm.member.service.dto.CreateMemberDto;

import lombok.RequiredArgsConstructor;

/**
 * Created by ShinD on 2022/05/14.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;




	@Override
	public void create(CreateMemberDto createMemberDto) {
		createMemberDto.encodePassword(passwordEncoder);

		Member member = createMemberDto.toEntity();

		checkDuplicateUsername(member.getUsername());

		memberRepository.save(member);
	}

	private void checkDuplicateUsername(String username) {
		memberRepository.findByUsername(username).ifPresent((member)-> {
			throw new MemberException(DUPLICATE_USERNAME);
		} );
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberRepository.findByUsername(username)
			.orElseThrow(() -> new UsernameNotFoundException("회원이 없습니다."));

		return User.builder().username(member.getId().toString())
			.password(member.getPassword())
			.authorities(new ArrayList<>())
			.build();
	}

}
