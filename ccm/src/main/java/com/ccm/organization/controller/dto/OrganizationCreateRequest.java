package com.ccm.organization.controller.dto;

import com.ccm.organization.domain.ParticipationCode;
import com.ccm.organization.service.dto.OrganizationCreateDto;

/**
 * Created by ShinD on 2022/05/10.
 */
public record OrganizationCreateRequest(String title,
										String description) {

	public OrganizationCreateDto toServiceDto() {
		return new OrganizationCreateDto(title, description);
	}
}
