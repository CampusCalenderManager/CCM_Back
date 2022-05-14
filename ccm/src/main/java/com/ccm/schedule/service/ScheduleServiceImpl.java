/*
 * Created by VertexToEdge on 2022/5/10.
 */

package com.ccm.schedule.service;

import java.util.ArrayList;
import java.util.List;

import com.ccm.member.domain.Member;
import com.ccm.member.exception.MemberException;
import com.ccm.member.exception.MemberExceptionType;
import com.ccm.member.repository.MemberRepository;
import com.ccm.organization.domain.Organization;
import com.ccm.organization.repository.OrganizationRepository;
import com.ccm.organization_member.domain.OrganizationMember;
import com.ccm.organization_member.repository.OrganizationMemberRepository;
import com.ccm.schedule.domain.Schedule;
import com.ccm.schedule.exception.ScheduleException;
import com.ccm.schedule.exception.ScheduleExceptionType;
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
    private final MemberRepository memberRepository;



    @Override
    public ScheduleDto create(Long memberId, CreateScheduleDto createScheduleDto) {
        Schedule schedule = createScheduleDto.toEntity();

        schedule.setMember(
            memberRepository.findById(memberId).orElseThrow(()-> new MemberException(MemberExceptionType.NOT_FOUND))
        );
        organizationRepository.findById(createScheduleDto.organizationId()).orElseThrow(()->new ScheduleException(ScheduleExceptionType.NOT_FOUND_GROUP));


        scheduleRepository.save(schedule);

        return ScheduleDto.from(schedule);
    }

    @Override
    public ScheduleDto update(Long memberId, Long scheduleId, UpdateScheduleDto updateScheduleDto) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow();

        if(!schedule.getMember().getId().equals(memberId)) throw new ScheduleException(ScheduleExceptionType.NO_AUTHORITY);

        updateScheduleDto.getTitle().ifPresent(schedule::setTitle);
        updateScheduleDto.getStartDate().ifPresent(schedule::setStartDate);
        updateScheduleDto.getEndDate().ifPresent(schedule::setEndDate);
        updateScheduleDto.getStartAlarm().ifPresent(schedule::setStartAlarm);
        updateScheduleDto.getEndAlarm().ifPresent(schedule::setEndAlarm);
        updateScheduleDto.getShared().ifPresent(schedule::setShared);
        updateScheduleDto.getColor().ifPresent(schedule::setColor);

        return ScheduleDto.from(schedule);
    }

    @Override
    public void delete(Long memberId, Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow();

        if(!schedule.getMember().getId().equals(memberId)) throw new ScheduleException(ScheduleExceptionType.NO_AUTHORITY);

        scheduleRepository.delete(schedule);

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
