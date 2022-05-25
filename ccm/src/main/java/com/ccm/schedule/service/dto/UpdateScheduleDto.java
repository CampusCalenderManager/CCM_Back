/*
 * Created by VertexToEdge on 2022/5/10.
 */

package com.ccm.schedule.service.dto;

import java.time.LocalDateTime;
import java.util.Optional;

import lombok.Builder;
import lombok.Getter;

@Builder
public class UpdateScheduleDto {

    private String title;
    private String content;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private LocalDateTime startAlarm;

    private boolean isShared;
    private boolean isAlarm;

    private String color;




    public Optional<String> getTitle() {
        return Optional.ofNullable(this.title);
    }
    public Optional<String> getContent() {return Optional.ofNullable(this.content);}

    public Optional<LocalDateTime> getStartDate() {
        return Optional.ofNullable(this.startDate);
    }
    public Optional<LocalDateTime> getEndDate() {
        return Optional.ofNullable(this.endDate);
    }

    public Optional<LocalDateTime> getStartAlarm() {
        return Optional.ofNullable(this.startAlarm);
    }
    public Optional<Boolean> getShared() {
        return Optional.ofNullable(this.isShared);
    }

    public Optional<Boolean> getAlarm() {
        return Optional.ofNullable(this.isAlarm);
    }

    public Optional<String> getColor() {
        return Optional.ofNullable(this.color);
    }
}
