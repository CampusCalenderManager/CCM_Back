package com.ccm.organization_member.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ccm.organization_member.domain.OrganizationMember;
import com.ccm.organization.domain.Organization;
import com.ccm.member.domain.Member;

/**
 * Created by ShinD on 2022/05/10.
 */
public interface OrganizationMemberRepository extends JpaRepository<OrganizationMember, Long> {
	Optional<OrganizationMember> findByMemberAndOrganization(Member member, Organization organization);

	List<OrganizationMember> findByMember(Member member);
}
