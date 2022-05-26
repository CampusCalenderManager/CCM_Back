package com.ccm.organization.controller;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ccm.organization.controller.dto.ChangeMemberRoleRequest;
import com.ccm.organization.controller.dto.OrganizationCreateRequest;
import com.ccm.organization.controller.dto.OrganizationInfoResponse;
import com.ccm.organization.controller.dto.OrganizationListResponse;
import com.ccm.organization.controller.dto.ParticipationCodeRequest;
import com.ccm.organization.controller.dto.ParticipationCodeResponse;
import com.ccm.organization.domain.ParticipationCode;
import com.ccm.organization.service.OrganizationService;
import com.ccm.organization.service.dto.OrganizationDto;
import com.ccm.organization.service.dto.OrganizationListDto;
import com.ccm.security.SecurityService;

import lombok.RequiredArgsConstructor;

/**
 * Created by ShinD on 2022/05/10.
 */
@RestController
@Transactional
@RequiredArgsConstructor
public class OrganizationController {

	private final OrganizationService organizationService;
	private final SecurityService securityService;


	/**
	 * 그룹생성
	 */
	@PostMapping("/organization")
	public ResponseEntity<ParticipationCodeResponse> create(@RequestBody OrganizationCreateRequest organizationCreateRequest){

		ParticipationCode participationCode =
				organizationService.create(securityService.getMemberId(), organizationCreateRequest.toServiceDto());

		return ResponseEntity.status(HttpStatus.CREATED).body(ParticipationCodeResponse.from(participationCode));
	}


	/**
	 * 참여 코드로 그룹 조회
	 */
	@GetMapping("/organization")
	public ResponseEntity<OrganizationInfoResponse> findBy(@RequestBody ParticipationCodeRequest participationCodeRequest){
		OrganizationDto organizationDto = organizationService.findByParticipationCode(
																ParticipationCode.from(participationCodeRequest.code()));

		return ResponseEntity.ok( OrganizationInfoResponse.from(organizationDto) );
	}




	/**
	 * 그룹 가입
	 */
	@PostMapping("/organization/member/{organizationId}")
	public ResponseEntity<?> apply(@PathVariable("organizationId") Long organizationId,
									@RequestBody ParticipationCodeRequest participationCodeRequest){


		organizationService.apply(securityService.getMemberId(),
								  organizationId,
								  ParticipationCode.from(participationCodeRequest.code()));

		return ResponseEntity.ok().build();
	}


	/**
	 * 그룹 탈퇴
	 */
	@DeleteMapping("/organization/member/{organizationId}")
	public ResponseEntity<?> apply(@PathVariable("organizationId") Long organizationId){

		organizationService.resign(securityService.getMemberId(), organizationId);
		return ResponseEntity.ok().build();
	}


	/**
	 * 내가 가입된 그룹 조회
	 */
	@GetMapping("/organization/my")
	public ResponseEntity<OrganizationListResponse> getMyOrganizations(){
		OrganizationListDto organizationListDto = organizationService.findJoined(securityService.getMemberId());

		return ResponseEntity.ok(
			OrganizationListResponse.from(organizationListDto)
		);
	}



	/**
	 * 회원 권한 변경
	 */
	@PutMapping("/organization/member")
	public ResponseEntity<?> changeMemberRole(@RequestBody ChangeMemberRoleRequest changeMemberRoleRequest){

		organizationService.changeMemberRole(securityService.getMemberId(),
											 changeMemberRoleRequest.targetMemberId(),
											 changeMemberRoleRequest.organizationId(),
											 changeMemberRoleRequest.role());

		return ResponseEntity.ok().build();
	}


}
