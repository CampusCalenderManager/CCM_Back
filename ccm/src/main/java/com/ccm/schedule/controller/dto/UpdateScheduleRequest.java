/*
 * Created by VertexToEdge on 2022/5/10.
 */

package com.ccm.schedule.controller.dto;

import java.time.LocalDateTime;
import java.util.Optional;

import com.ccm.schedule.service.dto.UpdateScheduleDto;

public class UpdateScheduleRequest {

    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime startAlarm;
    private boolean isShared;
    private boolean isAlarm;
   // private String color;




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

    public Optional<Boolean> getShared() {
        return Optional.ofNullable(this.isShared);
    }
    public Optional<Boolean> getAlarm() {
        return Optional.ofNullable(this.isAlarm);
    }
    //public Optional<String> getColor() {
  /*      return Optional.ofNullable(this.color);
    }*/

    public UpdateScheduleDto toServiceDto() {
        return UpdateScheduleDto.builder()
            .title(title)
            .startDate(startDate)
            .endDate(endDate)
            .startAlarm(startAlarm)
            .isShared(isShared)
            .isAlarm(isAlarm)
           // .color(color)
            .build();
    }
}
