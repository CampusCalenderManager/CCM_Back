package com.ccm.organization.controller.dto;

import com.ccm.organization.service.dto.OrganizationDto;

/**
 * Created by ShinD on 2022/05/10.
 */
public record OrganizationInfoResponse( Long organizationId,
										String title,
										String color,
										int memberNum,
										String description,
										String presidentName)  {

	public static OrganizationInfoResponse from(OrganizationDto organizationDto) {
		return new OrganizationInfoResponse(
			organizationDto.organizationId(),
			organizationDto.title(),
			organizationDto.color(),
			organizationDto.memberNum(),
			organizationDto.description(),
			organizationDto.presidentName()
		);
	}
}
