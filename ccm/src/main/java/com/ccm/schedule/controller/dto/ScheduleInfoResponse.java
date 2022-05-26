package com.ccm.schedule.controller.dto;

import java.time.LocalDateTime;

import com.ccm.member.service.dto.MemberDto;
import com.ccm.schedule.service.dto.ScheduleDto;

import lombok.Builder;

/**
 * Created by ShinD on 2022/05/14.
 */
public record ScheduleInfoResponse(Long id,
								   String title,
								   LocalDateTime startDate,
								   LocalDateTime endDate,
								   LocalDateTime startAlarm,
								   boolean isShared,
								   boolean isAlarm,
								   //String color,
								   MemberDto memberDto) {


	@Builder
	public ScheduleInfoResponse(Long id, String title, LocalDateTime startDate, LocalDateTime endDate,
		LocalDateTime startAlarm,boolean isShared,  boolean isAlarm,/* String color,*/ MemberDto memberDto) {
		this.id = id;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startAlarm = startAlarm;
		this.isShared = isShared;
		this.isAlarm = isAlarm;
		//this.color = color;
		this.memberDto = memberDto;
	}

	public static ScheduleInfoResponse from(ScheduleDto scheduleDto) {
		return ScheduleInfoResponse.builder()
			.id(scheduleDto.id())
			.title(scheduleDto.title())
			.startDate(scheduleDto.startDate())
			.endDate(scheduleDto.endDate())
			.startAlarm(scheduleDto.startAlarm())
			.isShared(scheduleDto.isShared())
			.isAlarm(scheduleDto.isAlarm())
			//.color(scheduleDto.color())
			.memberDto(scheduleDto.memberDto())
			.build();

	}
}
