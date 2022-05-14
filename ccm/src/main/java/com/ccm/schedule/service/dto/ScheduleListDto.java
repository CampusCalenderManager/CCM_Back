/*
 * Created by VertexToEdge on 2022/5/10.
 */

package com.ccm.schedule.service.dto;

import java.util.ArrayList;
import java.util.List;

import com.ccm.schedule.domain.Schedule;

public class ScheduleListDto {


	List<ScheduleDto> scheduleDtoList = new ArrayList<>();

	public ScheduleListDto(List<ScheduleDto> scheduleDtoList) {
		this.scheduleDtoList = scheduleDtoList;
	}

	public static ScheduleListDto from(List<Schedule> scheduleDtoList){
		return new ScheduleListDto(scheduleDtoList.stream().map(ScheduleDto::from).toList());
	}


}
