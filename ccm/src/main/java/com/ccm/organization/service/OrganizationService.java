package com.ccm.organization.service;

import com.ccm.organization.domain.ParticipationCode;
import com.ccm.organization.service.dto.OrganizationCreateDto;
import com.ccm.organization.service.dto.OrganizationDto;
import com.ccm.organization.service.dto.OrganizationSearchCond;
import com.ccm.organization_member.domain.Role;

/**
 * Created by ShinD on 2022/05/10.
 */
public interface OrganizationService {

	ParticipationCode create(OrganizationCreateDto groupCreateDto);//그룹생성

	OrganizationDto findById(Long organizationId);//PK 로 그룹 찾기
	OrganizationDto findByParticipationCode(ParticipationCode participationCode);//참여코드로 그룹 찾기 -> 정말 가입하시겠습니까?를 보여주기 위한 화면

	void apply(Long memberId, ParticipationCode participationCode);//가입 (참여코드 필수(보안? 관련해서))
	void resign(Long memberId, Long organizationIdId);//탈퇴
	void search(OrganizationSearchCond organizationIdSearchCond);//검색
	void findJoined(Long memberId);//회원이 가입합 그룹 찾기


	void changeMemberRole(Long memberId, Long organizationId, Role role);



}
