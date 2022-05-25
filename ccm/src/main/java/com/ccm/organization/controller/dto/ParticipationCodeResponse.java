package com.ccm.organization.controller.dto;

import com.ccm.organization.domain.ParticipationCode;

/**
 * Created by ShinD on 2022/05/26.
 */
public record ParticipationCodeResponse(String code) {
	public static ParticipationCodeResponse from(ParticipationCode participationCode) {
		return new ParticipationCodeResponse(participationCode.getCode());
	}
}
