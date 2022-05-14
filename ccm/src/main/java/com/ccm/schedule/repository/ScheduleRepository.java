/*
 * Created by VertexToEdge on 2022/5/10.
 */

/*
 * Created by VertexToEdge on 2022/5/10.
 */

package com.ccm.schedule.repository;

import com.ccm.schedule.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
