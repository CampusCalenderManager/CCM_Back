package com.ccm.organization.service.dto;

import java.util.ArrayList;
import java.util.List;

import com.ccm.organization.controller.dto.OrganizationInfoResponse;
import com.ccm.organization.domain.Organization;
import com.ccm.organization_member.domain.OrganizationMember;

/**
 * Created by ShinD on 2022/05/10.
 */
public class OrganizationListDto {
	List<OrganizationInfoResponse> organizationInfoResponseList = new ArrayList<>();
	int total;

	public OrganizationListDto(
		List<OrganizationInfoResponse> organizationInfoResponseList, int total) {
		this.organizationInfoResponseList = organizationInfoResponseList;
		this.total = total;
	}

	public static OrganizationListDto from(List<OrganizationMember> byMember) {
		return
			new OrganizationListDto(byMember.stream().map(OrganizationMember::getOrganization).map(OrganizationDto::from).map(OrganizationInfoResponse::from).toList(), byMember.size());
	}
}
