package com.ccm.organization.controller.dto;

import com.ccm.organization_member.domain.Role;

/**
 * Created by ShinD on 2022/05/10.
 */
public record ChangeMemberRoleRequest(Long targetMemberId, Long organizationId, Role role) {
}
