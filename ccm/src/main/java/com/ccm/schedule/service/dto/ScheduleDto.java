/*
 * Created by VertexToEdge on 2022/5/10.
 */

package com.ccm.schedule.service.dto;

import java.time.LocalDateTime;

import com.ccm.member.service.dto.MemberDto;
import com.ccm.organization.service.dto.OrganizationDto;
import com.ccm.schedule.domain.Schedule;

import lombok.Builder;

public record ScheduleDto(Long id,
                          String title,
                          LocalDateTime startDate,
                          LocalDateTime endDate,
                          LocalDateTime startAlarm,
                          boolean isShared,
                          boolean isAlarm,
                          String color,
                          MemberDto memberDto,
                          OrganizationDto organizationDto){



    @Builder
    public ScheduleDto(Long id, String title, LocalDateTime startDate, LocalDateTime endDate, LocalDateTime startAlarm, boolean isShared,boolean isAlarm, String color, MemberDto memberDto, OrganizationDto organizationDto) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startAlarm = startAlarm;
        this.isShared = isShared;
        this.isAlarm = isAlarm;
        this.color = color;
        this.memberDto = memberDto;
        this.organizationDto = organizationDto;
    }

    public static ScheduleDto from(Schedule schedule) {
        return ScheduleDto.builder()
                .id(schedule.getId())
                .title(schedule.getTitle())
                .startDate(schedule.getStartDate())
                .endDate(schedule.getEndDate())
                .startAlarm(schedule.getStartAlarm())
                .isShared(schedule.isShared())
                .isAlarm(schedule.isAlarm())
                .color(schedule.getColor())
                .memberDto(MemberDto.from(schedule.getMember()))
                .organizationDto(OrganizationDto.from(schedule.getOrganization()))
                .build();
    }
}
