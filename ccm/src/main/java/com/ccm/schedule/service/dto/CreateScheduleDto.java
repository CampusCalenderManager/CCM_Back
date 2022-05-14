/*
 * Created by VertexToEdge on 2022/5/10.
 */

package com.ccm.schedule.service.dto;

import com.ccm.member.domain.Member;
import com.ccm.organization.domain.Organization;
import com.ccm.schedule.domain.Schedule;

import java.time.LocalDateTime;

public class CreateScheduleDto {
	private String title;

	private LocalDateTime startDate;
	private LocalDateTime endDate;

	private LocalDateTime startAlarm;
	private LocalDateTime endAlarm;

	private boolean isShared;

	private String color;

	private Long memberId;
	private Long organizationId;

	public Schedule toEntity() {
		return Schedule.builder()
			.title(title)
			.startDate(startDate)
			.endDate(endDate)
			.startAlarm(startAlarm)
			.endAlarm(endAlarm)
			.isShared(isShared)
			.color(color)
			.member(new Member(memberId))
			.organization(new Organization(organizationId))
			.build();
	}
}
