package com.ccm.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ccm.member.domain.Member;

/**
 * Created by ShinD on 2022/05/10.
 */
public interface MemberRepository extends JpaRepository<Member, Long> {

	Optional<Member> findByUsername(String username);
}
