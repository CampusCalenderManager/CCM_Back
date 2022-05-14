/*
 * Created by VertexToEdge on 2022/5/10.
 */

package com.ccm.schedule.service.dto;

import com.ccm.member.domain.Member;
import com.ccm.member.service.dto.MemberDto;
import com.ccm.schedule.domain.Schedule;
import lombok.Builder;


import java.time.LocalDateTime;

public class ScheduleDto {
    private String title;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private LocalDateTime startAlarm;
    private LocalDateTime endAlarm;

    private boolean isShared;

    private String color;

    private MemberDto memberDto;



    @Builder
    public ScheduleDto(String title, LocalDateTime startDate, LocalDateTime endDate, LocalDateTime startAlarm, LocalDateTime endAlarm, boolean isShared, String color, MemberDto member) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startAlarm = startAlarm;
        this.endAlarm = endAlarm;
        this.isShared = isShared;
        this.color = color;
        this.memberDto = member;
    }

    public Schedule toEntity(Long id) {
        return Schedule.builder()
                .title(title)
                .startDate(startDate)
                .endDate(endDate)
                .startAlarm(startAlarm)
                .endAlarm(endAlarm)
                .isShared(isShared)
                .color(color)
                .member(new Member(id))
                .build();
    }
    public static ScheduleDto from(Schedule schedule) {
        return ScheduleDto.builder()
                .title(schedule.getTitle())
                .startDate(schedule.getStartDate())
                .endDate(schedule.getEndDate())
                .startAlarm(schedule.getStartAlarm())
                .endAlarm(schedule.getEndAlarm())
                .isShared(schedule.isShared())
                .color(schedule.getColor())
                .member(MemberDto.from(schedule.getMember()))
                .build();
    }
}
