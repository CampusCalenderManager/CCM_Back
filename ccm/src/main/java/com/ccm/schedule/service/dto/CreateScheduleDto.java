package com.ccm.schedule.service.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by ShinD on 2022/05/14.
 */
@Builder
@Getter
public class CreateScheduleDto {


	private String title;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private LocalDateTime startAlarm;
	private LocalDateTime endAlarm;
	private boolean isShared;
	private String color;
	private Long organizationId;

}
