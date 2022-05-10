package com.ccm.organization.service.dto;

import com.ccm.member.domain.Member;
import com.ccm.organization.domain.Organization;

/**
 * Created by ShinD on 2022/05/10.
 */
public record OrganizationDto(Long organizationId, String title, int memberNum,  String description, String presidentName) {

	public static OrganizationDto from(Organization organization) {

		return new OrganizationDto(
			organization.getId(),
			organization.getTitle(),
			organization.getMemberNum(),
			organization.getDescription(),
			organization.getPresident().getName());
	}

}
