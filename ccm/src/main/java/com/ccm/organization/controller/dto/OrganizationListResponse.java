package com.ccm.organization.controller.dto;

import java.util.ArrayList;
import java.util.List;

import com.ccm.organization.service.dto.OrganizationListDto;

import lombok.Getter;

/**
 * Created by ShinD on 2022/05/10.
 */
@Getter
public class OrganizationListResponse {
	List<OrganizationInfoResponse> organizationInfoResponseList = new ArrayList<>();
	int total;

	public OrganizationListResponse(
		List<OrganizationInfoResponse> organizationInfoResponseList, int total) {
		this.organizationInfoResponseList = organizationInfoResponseList;
		this.total = total;
	}

	public static OrganizationListResponse from(OrganizationListDto organizationListDto) {

		return new OrganizationListResponse(organizationListDto.getOrganizationInfoResponseList(), organizationListDto.getOrganizationInfoResponseList().size());
	}
}
