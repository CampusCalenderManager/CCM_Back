package com.ccm.organization.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ccm.organization_member.domain.OrganizationMember;
import com.ccm.organization_member.domain.Role;
import com.ccm.organization_member.repository.OrganizationMemberRepository;
import com.ccm.organization.domain.Organization;
import com.ccm.organization.domain.ParticipationCode;
import com.ccm.organization.exception.OrganizationException;
import com.ccm.organization.exception.OrganizationExceptionType;
import com.ccm.organization.repository.OrganizationRepository;
import com.ccm.organization.service.dto.OrganizationCreateDto;
import com.ccm.organization.service.dto.OrganizationDto;
import com.ccm.organization.service.dto.OrganizationSearchCond;
import com.ccm.member.domain.Member;
import com.ccm.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

/**
 * Created by ShinD on 2022/05/10.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

	private final OrganizationRepository organizationRepository;
	private final MemberRepository memberRepository;
	private final OrganizationMemberRepository organizationMemberRepository;

	@Override
	public ParticipationCode create(OrganizationCreateDto groupCreateDto) {

		Organization groups = groupCreateDto.toEntity();

		groups.issueParticipationCode();//참여코드 발급

		organizationRepository.save(groups);//생성된 그룹 저장

		organizationMemberRepository.save(OrganizationMember.president(groups.getPresident(), groups));

		return groups.getParticipationCode();//참여코드 반환
	}


	@Override
	public OrganizationDto findById(Long groupId) {
		return OrganizationDto.from(
			organizationRepository.findById(groupId)
				.orElseThrow(() -> new OrganizationException(OrganizationExceptionType.NOT_FOUND))
		);
	}



	@Override
	public OrganizationDto findByParticipationCode(ParticipationCode participationCode) {
		return OrganizationDto.from(
			organizationRepository.findByParticipationCode(participationCode)
				.orElseThrow(() -> new OrganizationException(OrganizationExceptionType.NOT_FOUND))
		);
	}



	@Override
	public void apply(Long memberId, ParticipationCode participationCode) {
		Organization groups = organizationRepository.findByParticipationCode(participationCode)
			.orElseThrow(() -> new OrganizationException(OrganizationExceptionType.NOT_FOUND));

		organizationMemberRepository.save(OrganizationMember.basic(new Member(memberId), groups));

	}

	@Override
	public void resign(Long memberId, Long groupId) {
		organizationMemberRepository.delete(
			organizationMemberRepository.findByMemberAndOrganization(new Member(memberId), new Organization(groupId)).orElseThrow()
		);
	}



	@Override
	public void search(OrganizationSearchCond groupSearchCond) {

	}

	@Override
	public void findJoined(Long memberId) {
		organizationMemberRepository.findByMember(new Member(memberId));
	}


	@Override
	public void changeMemberRole(Long memberId, Long organizationId, Role role) {
		organizationMemberRepository.findByMemberAndOrganization(new Member(memberId), new Organization(organizationId))
			.orElseThrow(() -> new OrganizationException(OrganizationExceptionType.NOT_FOUND))
			.changeRole(role);
	}

}
