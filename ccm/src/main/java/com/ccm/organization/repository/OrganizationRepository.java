package com.ccm.organization.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ccm.organization.domain.Organization;
import com.ccm.organization.domain.ParticipationCode;

/**
 * Created by ShinD on 2022/05/10.
 */
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
	Optional<Organization> findByParticipationCode(ParticipationCode participationCode);
}
