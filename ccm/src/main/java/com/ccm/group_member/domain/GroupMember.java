package com.ccm.group_member.domain;

/**
 * Created by ShinD on 2022/05/09.
 */
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ccm.common.BaseTimeEntity;
import com.ccm.group.domain.Groups;
import com.ccm.member.domain.Member;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "groups_member")
public class GroupMember extends BaseTimeEntity {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "groups_member_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "groups_id")
	private Groups group;

	private String role;
}
