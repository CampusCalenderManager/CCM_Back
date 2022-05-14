/*
 * Created by VertexToEdge on 2022/5/10.
 */

package com.ccm.schedule.service.dto;

import java.time.LocalDateTime;

import com.ccm.member.service.dto.MemberDto;
import com.ccm.schedule.domain.Schedule;

import lombok.Builder;
import lombok.Getter;

public record ScheduleDto(Long id,
                          String title,
                          LocalDateTime startDate,
                          LocalDateTime endDate,
                          LocalDateTime startAlarm,
                          LocalDateTime endAlarm,
                          boolean isShared,
                          String color,
                          MemberDto memberDto){



    @Builder
    public ScheduleDto(Long id, String title, LocalDateTime startDate, LocalDateTime endDate, LocalDateTime startAlarm, LocalDateTime endAlarm, boolean isShared, String color, MemberDto memberDto) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startAlarm = startAlarm;
        this.endAlarm = endAlarm;
        this.isShared = isShared;
        this.color = color;
        this.memberDto = memberDto;
    }

    public static ScheduleDto from(Schedule schedule) {
        return ScheduleDto.builder()
                .id(schedule.getId())
                .title(schedule.getTitle())
                .startDate(schedule.getStartDate())
                .endDate(schedule.getEndDate())
                .startAlarm(schedule.getStartAlarm())
                .endAlarm(schedule.getEndAlarm())
                .isShared(schedule.isShared())
                .color(schedule.getColor())
                .memberDto(MemberDto.from(schedule.getMember()))
                .build();
    }
}
