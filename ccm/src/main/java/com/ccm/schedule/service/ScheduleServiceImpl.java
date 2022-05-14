/*
 * Created by VertexToEdge on 2022/5/10.
 */

package com.ccm.schedule.service;

import com.ccm.schedule.domain.Schedule;
import com.ccm.schedule.repository.ScheduleRepository;
import com.ccm.schedule.service.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Override
    public void create(CreateScheduleDto createScheduleDto) {
        Schedule schedule = createScheduleDto.toEntity();
        scheduleRepository.save(schedule);
    }

    @Override
    public void update(Long scheduleId, UpdateScheduleDto updateScheduleDto) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow();
        updateScheduleDto.getTitle().ifPresent(schedule::setTitle);
        updateScheduleDto.getStartDate().ifPresent(schedule::setStartDate);
        updateScheduleDto.getEndDate().ifPresent(schedule::setEndDate);
        updateScheduleDto.getStartAlarm().ifPresent(schedule::setStartAlarm);
        updateScheduleDto.getEndAlarm().ifPresent(schedule::setEndAlarm);
        updateScheduleDto.getShared().ifPresent(schedule::setShared);
        updateScheduleDto.getColor().ifPresent(schedule::setColor);
    }

    @Override
    public void delete(Long scheduleId, Long memberId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow();
        if (schedule.getId().equals(memberId)) {
            scheduleRepository.delete(schedule);
        }
    }

    @Override
    public ScheduleDto findOne(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow();
        return ScheduleDto.from(schedule);
    }

    @Override
    public ScheduleListDto search(ScheduleSearchCond scheduleSearchCond) {
        return null;
    }
}
