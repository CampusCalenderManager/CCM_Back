package com.ccm.schedule.domain;

/**
 * Created by ShinD on 2022/05/09.
 */

import com.ccm.common.BaseTimeEntity;
import com.ccm.member.domain.Member;
import com.ccm.organization.domain.Organization;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "schedule")
public class Schedule extends BaseTimeEntity {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "schedule_id")
	private Long id;

	private String title;
	private String content;

	private LocalDateTime startDate;
	private LocalDateTime endDate;

	private LocalDateTime startAlarm;

	private boolean isShared;
	private boolean isAlarm;

	//private String color;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id")
	private Organization organization;



	@Builder
	public Schedule(String title,String content, LocalDateTime startDate, LocalDateTime endDate, LocalDateTime startAlarm, boolean isShared,boolean isAlarm,  Member member, Organization organization) {
		this.title = title;
		this.content = content;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startAlarm = startAlarm;
		this.isShared = isShared;
		this.isAlarm = isAlarm;
		//this.color = color;
		this.member = member;
		this.organization = organization;
	}


	public void setTitle(String title) {
		this.title = title;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public void setStartAlarm(LocalDateTime startAlarm) {
		this.startAlarm = startAlarm;
	}

	public void setShared(boolean shared) {
		this.isShared = shared;
	}
	public void setAlarm(boolean isAlarm) {
		this.isAlarm = isAlarm;
	}

/*	public void setColor(String color) {
		this.color = color;
	}*/

	public void setMember(Member member) {
		this.member = member;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
