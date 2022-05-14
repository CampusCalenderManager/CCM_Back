package com.ccm.member.domain;

/**
 * Created by ShinD on 2022/05/09.
 */

import lombok.AccessLevel;
import lombok.Builder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ccm.common.BaseTimeEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "member")
public class Member extends BaseTimeEntity {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;
	private String username;
	private String password;
	private String name;

	@Builder
	private Member(String username, String password, String name) {
		this.username = username;
		this.password = password;
		this.name = name;
	}

	public Member(long id) {
		this.id = id;
	}
}
