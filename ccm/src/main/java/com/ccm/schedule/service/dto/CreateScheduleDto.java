/*
 * Created by VertexToEdge on 2022/5/10.
 */

package com.ccm.schedule.service.dto;

import java.time.LocalDateTime;
import java.util.Optional;

import com.ccm.organization.domain.Organization;
import com.ccm.schedule.domain.Schedule;

import lombok.Builder;

public record CreateScheduleDto(String title,
							   LocalDateTime startDate,
							   LocalDateTime endDate,
							   LocalDateTime startAlarm,
							   LocalDateTime endAlarm,
							   boolean isShared,
							   String color,
							   Long organizationId) {



	@Builder
	public CreateScheduleDto(String title, LocalDateTime startDate, LocalDateTime endDate,
		LocalDateTime startAlarm, LocalDateTime endAlarm, boolean isShared, String color, Long organizationId) {
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startAlarm = startAlarm;
		this.endAlarm = endAlarm;
		this.isShared = isShared;
		this.color = color;
		this.organizationId = organizationId;
	}

	public Schedule toEntity() {
		return Schedule.builder()
			.title(title)
			.startDate(startDate)
			.endDate(endDate)
			.startAlarm(startAlarm)
			.endAlarm(endAlarm)
			.isShared(isShared)
			.color(color)
			.build();
	}
}
