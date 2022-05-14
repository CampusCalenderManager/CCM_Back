package com.ccm.learning;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.ccm.organization.domain.Organization;
import com.ccm.organization.repository.OrganizationRepository;
import com.ccm.member.domain.Member;
import com.ccm.member.repository.MemberRepository;

/**
 * Created by ShinD on 2022/05/10.
 */
@SpringBootTest
@Transactional
public class TestJpa {

	@Autowired private EntityManager em;

	@Autowired private MemberRepository memberRepository;
	@Autowired private OrganizationRepository groupsRepository;


	@Test
	@Rollback(value = false)
	public void id만_넣어도_연관관계_저장() throws Exception {
	    //given
		memberRepository.save(Member.builder().name("신동훈").password("123").username("huipuco").build());
		em.flush();
		em.clear();


	    //when
		groupsRepository.save(new Organization("그룹1","그룹입니다", new Member(1L)));
		em.flush();
		em.clear();

	    //then
		Assertions.assertThat(groupsRepository.findById(1L).orElse(null).getPresident().getName()).isEqualTo("신동훈");
		Assertions.assertThat(groupsRepository.findById(1L).orElse(null).getPresident().getPassword()).isNotEqualTo("1234");

	}

}
