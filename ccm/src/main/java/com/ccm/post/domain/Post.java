package com.ccm.post.domain;

/**
 * Created by ShinD on 2022/05/09.
 */

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
@Table(name = "post")
public class Post extends BaseTimeEntity {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private Long id;

	private String title;

	@Enumerated(value = EnumType.STRING)
	private Type type;

	private String content;
	private String imagePath;

	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="groups_id")
	private Organization group;

	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="writer_id")
	private Member writer;
}
