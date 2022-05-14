/*
 * Created by VertexToEdge on 2022/5/10.
 */

package com.ccm.schedule.service.dto;

import java.time.LocalDateTime;
import java.util.Optional;

public class UpdateScheduleDto {

    private String title;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private LocalDateTime startAlarm;
    private LocalDateTime endAlarm;

    private boolean isShared;

    private String color;

    public Optional<String> getTitle() {
        return Optional.ofNullable(this.title);
    }

    public Optional<LocalDateTime> getStartDate() {
        return Optional.ofNullable(this.startDate);
    }
    public Optional<LocalDateTime> getEndDate() {
        return Optional.ofNullable(this.endDate);
    }

    public Optional<LocalDateTime> getStartAlarm() {
        return Optional.ofNullable(this.startAlarm);
    }
    public Optional<LocalDateTime> getEndAlarm() {
        return Optional.ofNullable(this.endAlarm);
    }
    public Optional<Boolean> getShared() {
        return Optional.ofNullable(this.isShared);
    }
    public Optional<String> getColor() {
        return Optional.ofNullable(this.color);
    }
}
