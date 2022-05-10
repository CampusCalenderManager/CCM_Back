package com.ccm.organization.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ccm.organization.service.dto.OrganizationListDto;
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
	public ParticipationCode create(Long memberId, OrganizationCreateDto groupCreateDto) {

		Organization groups = groupCreateDto.toEntity();

		//요청을 보낸 사람과 그룹의 대표로 설정된 사람이 동일한지 확인
		if (memberId.equals(groups.getPresident().getId())) throw new OrganizationException(OrganizationExceptionType.MISMATCH_MEMBER_PRESIDENT);

		groups.issueParticipationCode();//참여코드 발급

		organizationRepository.save(groups);//생성된 그룹 저장

		organizationMemberRepository.save(OrganizationMember.president(groups.getPresident(), groups));

		return groups.getParticipationCode();//참여코드 반환
	}





	@Override
	public OrganizationDto findByParticipationCode(ParticipationCode participationCode) {
		return OrganizationDto.from(
			organizationRepository.findByParticipationCode(participationCode)
				.orElseThrow(() -> new OrganizationException(OrganizationExceptionType.NOT_FOUND))
		);
	}



	@Override
	public void apply(Long memberId, Long organizationId, ParticipationCode participationCode) {

		Organization organization = organizationRepository.findById(organizationId)
			.orElseThrow(() -> new OrganizationException(OrganizationExceptionType.NOT_FOUND));

		if(!organization.matchCode(participationCode)) {
			throw new OrganizationException(OrganizationExceptionType.MISMATCH_CODE);
		}

		organizationMemberRepository.save(OrganizationMember.basic(new Member(memberId), organization));
	}



	@Override
	public void resign(Long memberId, Long groupId) {
		organizationMemberRepository.delete(
			organizationMemberRepository.findByMemberAndOrganization(new Member(memberId), new Organization(groupId)).orElseThrow()
		);
	}



	@Override
	public OrganizationListDto search(OrganizationSearchCond groupSearchCond) {
		//TODO : 구현
		return null;
	}



	@Override
	public OrganizationListDto findJoined(Long memberId) {
		return OrganizationListDto.from(organizationMemberRepository.findByMember(new Member(memberId)));
	}




	@Override
	public void changeMemberRole(Long requesterId ,Long targetMemberId, Long organizationId, Role role) {
		OrganizationMember organizationMember = organizationMemberRepository.findByMemberAndOrganization(
				new Member(requesterId), new Organization(organizationId))
			.orElseThrow(() -> new OrganizationException(OrganizationExceptionType.NOT_FOUND));


		if( organizationMember.checkRole(Role.PRESIDENT, Role.ADMIN))
			throw new OrganizationException(OrganizationExceptionType.NO_AUTHORITY);



		organizationMemberRepository.findByMemberAndOrganization(new Member(targetMemberId), new Organization(organizationId))
			.orElseThrow(() -> new OrganizationException(OrganizationExceptionType.NOT_FOUND))
			.changeRole(role);
	}



}
