package com.ccm.schedule.service.dto;

import java.time.LocalDateTime;

import com.ccm.schedule.domain.Schedule;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by ShinD on 2022/05/14.
 */

@Getter
public class CreateScheduleDto {

	private String title;
	private String content;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private LocalDateTime startAlarm;
	private boolean isShared;
	private boolean isAlarm;
	private String color;
	private Long organizationId;

	@Builder
	public CreateScheduleDto(String title, String content, LocalDateTime startDate, LocalDateTime endDate,
		LocalDateTime startAlarm, boolean isShared, boolean isAlarm, String color, Long organizationId) {
		this.title = title;
		this.content = content;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startAlarm = startAlarm;
		this.isShared = isShared;
		this.isAlarm = isAlarm;
		this.color = color;
		this.organizationId = organizationId;
	}

	public Schedule toEntity() {
		return Schedule.builder()
			.title(title)
			.content(content)
			.startDate(startDate)
			.endDate(endDate)
			.startAlarm(startAlarm)
			.isShared(isShared)
			.isAlarm(isAlarm)
			.color(color)
			.build();
	}
}
