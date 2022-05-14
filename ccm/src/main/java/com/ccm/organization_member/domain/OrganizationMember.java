package com.ccm.organization_member.domain;

/**
 * Created by ShinD on 2022/05/09.
 */
import java.util.Arrays;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ccm.common.BaseTimeEntity;
import com.ccm.organization.domain.Organization;
import com.ccm.member.domain.Member;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "organization_member")
public class OrganizationMember extends BaseTimeEntity {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "organization_member_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "organization_id")
	private Organization organization;

	@Enumerated(EnumType.STRING)
	private Role role;

	public OrganizationMember(Member member, Organization organization, Role role) {
		this.member = member;
		this.organization = organization;
		this.role = role;
	}




	public static OrganizationMember basic(Member member, Organization organization) {
		return new OrganizationMember(member, organization, Role.BASIC);
	}

	public static OrganizationMember president(Member member, Organization organization) {
		return new OrganizationMember(member, organization, Role.PRESIDENT);
	}

	public void changeRole(Role role) {
		this.role = role;
	}

	public boolean checkRole(Role ...roles) {

		return Arrays.stream(roles).anyMatch(role -> role.equals(this.getRole()));

	}
}
