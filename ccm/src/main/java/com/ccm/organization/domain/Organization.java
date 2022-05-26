package com.ccm.organization.domain;

/**
 * Created by ShinD on 2022/05/09.
 */

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ccm.common.BaseTimeEntity;
import com.ccm.member.domain.Member;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "organization")
public class Organization extends BaseTimeEntity {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "organization_id")
	private Long id;

	private String title;
	private int memberNum;

	private String description;
	private String color;

	@Embedded
	private ParticipationCode participationCode;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member president;

	@Builder
	public Organization(String title, String description, Member president, String color) {
		this.title = title;
		this.description = description;
		this.president = president;
		this.color = color;
	}

	public Organization(Long id) {
		this.id = id;
	}





	public void issueParticipationCode() {
		this.participationCode =  ParticipationCode.createRandom();
	}

	public boolean matchCode(ParticipationCode participationCode) {
		return this.participationCode.match(participationCode);
	}

	public void setPresident(Member member) {
		this.president = member;
	}

	public void plusMemberNum() {
		this.memberNum++;
	}
}
