/*
 * Created by VertexToEdge on 2022/5/10.
 */

/*
 * Created by VertexToEdge on 2022/5/10.
 */

package com.ccm.schedule.repository;

import com.ccm.schedule.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

	@Query("select s from Schedule s where s.organization.id in (:organizationIds) and s.isShared = true")
	List<Schedule> findAllByOrganizationIdAndSharedTrue(@Param("organizationIds") List<Long> organizationIds);


	List<Schedule> findAllByMemberId(Long memberId);
}
