/*
 * Created by VertexToEdge on 2022/5/10.
 */

package com.ccm.schedule.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.ccm.member.domain.Member;
import com.ccm.organization.domain.Organization;
import com.ccm.organization.repository.OrganizationRepository;
import com.ccm.organization_member.domain.OrganizationMember;
import com.ccm.organization_member.repository.OrganizationMemberRepository;
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
    private final OrganizationRepository organizationRepository;
    private final OrganizationMemberRepository organizationMemberRepository;



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


    @Override
    public ScheduleListDto getAllByMemberId(Long memberId) {
        List<OrganizationMember> organizationMembers = organizationMemberRepository.findByMember(new Member(memberId));

        List<Long> organizationIds = organizationMembers.stream()
            .map(OrganizationMember::getOrganization)
            .map(Organization::getId).toList();

        List<Schedule> organizationSchedules = scheduleRepository.findAllByOrganizationIdAndSharedTrue(
            organizationIds);
        List<Schedule> mySchedules = scheduleRepository.findAllByMemberId(memberId);



        ArrayList<Schedule> schedules = new ArrayList<>(organizationSchedules);
        schedules.removeAll(mySchedules);
        schedules.addAll(mySchedules);

        return ScheduleListDto.from(schedules);

    }
}
