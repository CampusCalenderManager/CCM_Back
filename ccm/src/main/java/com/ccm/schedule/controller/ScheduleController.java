package com.ccm.schedule.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ccm.schedule.controller.dto.CreateScheduleRequest;
import com.ccm.schedule.controller.dto.ScheduleInfoResponse;
import com.ccm.schedule.repository.ScheduleRepository;
import com.ccm.schedule.service.ScheduleService;
import com.ccm.schedule.service.dto.ScheduleDto;
import com.ccm.security.SecurityService;

import lombok.RequiredArgsConstructor;

/**
 * Created by ShinD on 2022/05/14.
 */
@RestController
@RequiredArgsConstructor
public class ScheduleController {

	private final ScheduleService scheduleService;
	private final SecurityService securityService;


	@PostMapping("/schedule")
	public ResponseEntity<ScheduleInfoResponse> create(@RequestBody CreateScheduleRequest createScheduleRequest){
		ScheduleDto scheduleDto = scheduleService.create(securityService.getMemberId(),
			createScheduleRequest.toServiceDto());
		return ResponseEntity.status(HttpStatus.CREATED).body(ScheduleInfoResponse.from(scheduleDto));
	}

}
