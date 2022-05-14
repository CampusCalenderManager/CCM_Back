/*
 * Created by VertexToEdge on 2022/5/10.
 */

package com.ccm.schedule.service;

import com.ccm.schedule.domain.Schedule;
import com.ccm.schedule.service.dto.*;

public interface ScheduleService {
    /**
     * 스케줄 생성
     * 스케줄 수정
     * 스케줄 삭제
     * 스케줄 조회
     */
    void create(CreateScheduleDto createScheduleDto);
    void update(Long scheduleId, UpdateScheduleDto updateScheduleDto);
    void delete(Long scheduleId, Long memberId);
    ScheduleDto findOne(Long scheduleId);
    ScheduleListDto getAllByMemberId(Long memberId);
    ScheduleListDto search(ScheduleSearchCond scheduleSearchCond);
}
