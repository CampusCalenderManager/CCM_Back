package com.ccm.schedule.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ccm.schedule.controller.dto.CreateScheduleRequest;
import com.ccm.schedule.controller.dto.ScheduleInfoResponse;
import com.ccm.schedule.controller.dto.UpdateScheduleRequest;
import com.ccm.schedule.repository.ScheduleRepository;
import com.ccm.schedule.service.ScheduleService;
import com.ccm.schedule.service.dto.ScheduleDto;
import com.ccm.schedule.service.dto.ScheduleListDto;
import com.ccm.schedule.service.dto.UpdateScheduleDto;
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

	/**
	 * 일정등록
	 */
	@PostMapping("/schedule")
	public ResponseEntity<ScheduleInfoResponse> create(@RequestBody CreateScheduleRequest createScheduleRequest){
		ScheduleDto scheduleDto = scheduleService.create(securityService.getMemberId(),
			createScheduleRequest.toServiceDto());
		return ResponseEntity.status(HttpStatus.CREATED).body(ScheduleInfoResponse.from(scheduleDto));
	}

	/**
	 * 일정수정
	 */
	@PutMapping("/schedule/{scheduleId}")
	public ResponseEntity<ScheduleInfoResponse> update(@PathVariable("scheduleId") Long scheduleId,
														@RequestBody UpdateScheduleRequest updateScheduleRequest){

		ScheduleDto scheduleDto = scheduleService.update(securityService.getMemberId(),
														scheduleId,
														updateScheduleRequest.toServiceDto());
		return ResponseEntity.status(HttpStatus.OK).body(ScheduleInfoResponse.from(scheduleDto));
	}

	/**
	 * 일정삭제
	 */
	@DeleteMapping("/schedule/{scheduleId}")
	public ResponseEntity<?> delete(@PathVariable("scheduleId") Long scheduleId){

		scheduleService.delete(securityService.getMemberId(), scheduleId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	/**
	 * 일정조회
	 */
	@GetMapping("/schedule/{scheduleId}")
	public ResponseEntity<ScheduleInfoResponse> getOne(@PathVariable("scheduleId") Long scheduleId){

		ScheduleDto scheduleDto = scheduleService.findOne(scheduleId);

		return ResponseEntity.status(HttpStatus.OK).body(ScheduleInfoResponse.from(scheduleDto));
	}

	/**
	 * 나의 일정 조회
	 */
	@GetMapping("/schedule/my")
	public ResponseEntity<ScheduleListDto> getMyList(){

		//TODO : RESPONSE dto 로 변경하기
		return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getAllByMemberId(securityService.getMemberId()));
	}

}
