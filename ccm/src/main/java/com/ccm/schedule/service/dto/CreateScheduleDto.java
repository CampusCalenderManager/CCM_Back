package com.ccm.schedule.controller.dto;

import java.time.LocalDateTime;

import com.ccm.schedule.service.dto.CreateScheduleDto;

/**
 * Created by ShinD on 2022/05/14.
 */
public record CreateScheduleRequest(String title,
									LocalDateTime startDate,
									LocalDateTime endDate,
									LocalDateTime startAlarm,
									LocalDateTime endAlarm,
									boolean isShared,
									String color,
									Long organizationId) {

	public CreateScheduleDto toServiceDto() {
		return CreateScheduleDto.builder().title(title)
			.startDate(startDate)
			.endDate(endDate)
			.startAlarm(startAlarm)
			.endAlarm(endAlarm)
			.isShared(isShared)
			.color(color)
			.organizationId(organizationId)
			.build();
	}
}
