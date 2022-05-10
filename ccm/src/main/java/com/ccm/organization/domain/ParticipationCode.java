package com.ccm.organization.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by ShinD on 2022/05/10.
 */
@Embeddable
@Getter
@NoArgsConstructor
public class ParticipationCode {

	@Column(name = "participation_code")
	private String code;

	public ParticipationCode(String code) {
		this.code = code;
	}

	public static ParticipationCode createRandom() {
		return new ParticipationCode(UUID.randomUUID().toString());
	}

}
