package com.ccm.organization.service.dto;

import com.ccm.member.domain.Member;
import com.ccm.organization.domain.Organization;

/**
 * Created by ShinD on 2022/05/10.
 */
public record OrganizationCreateDto(String title, String description , Long presidentId) {

	public Organization toEntity() {
		return Organization.builder().title(title).description(description)
			.president(new Member(presidentId))
			.build();
	}
}
