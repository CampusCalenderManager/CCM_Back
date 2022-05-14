package com.ccm.member.controller.dto;

import com.ccm.member.service.dto.CreateMemberDto;

/**
 * Created by ShinD on 2022/05/14.
 */
public record SignUpRequest(String username, String password, String name) {
	public CreateMemberDto toServiceDto() {
		return new CreateMemberDto(username, password , name);
	}
}
