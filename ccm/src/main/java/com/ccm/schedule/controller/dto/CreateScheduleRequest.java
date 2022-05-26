package com.ccm.schedule.controller.dto;

import java.time.LocalDateTime;

import com.ccm.schedule.service.dto.CreateScheduleDto;

import lombok.Builder;

/**
 * Created by ShinD on 2022/05/14.
 */
@Builder
public record CreateScheduleRequest(String title,
									LocalDateTime startDate,
									LocalDateTime endDate,
									LocalDateTime startAlarm,
									boolean isShared,
									boolean isAlarm,
									//String color,
									Long organizationId) {

	public CreateScheduleDto toServiceDto() {
		return CreateScheduleDto.builder()
			.title(title)
			.startDate(startDate)
			.endDate(endDate)
			.startAlarm(startAlarm)
			.isShared(isShared)
			.isAlarm(isShared)
			//.color(color)
			.organizationId(organizationId)
			.build();
	}
}
