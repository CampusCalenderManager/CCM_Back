package com.ccm.schedule.service.dto;

import java.time.LocalDateTime;

import com.ccm.schedule.domain.Schedule;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by ShinD on 2022/05/14.
 */
@Builder
public record CreateScheduleDto(String title, LocalDateTime startDate,
								LocalDateTime endDate,
								LocalDateTime startAlarm,
								LocalDateTime endAlarm,
								boolean isShared,
								String color,
								Long organizationId) {

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
