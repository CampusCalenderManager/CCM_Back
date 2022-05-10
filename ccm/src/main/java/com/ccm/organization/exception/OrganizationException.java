package com.ccm.organization.exception;

import com.ccm.common.exception.BaseException;
import com.ccm.common.exception.BaseExceptionType;

/**
 * Created by ShinD on 2022/05/10.
 */
public class OrganizationException extends BaseException {

	private final OrganizationExceptionType groupsExceptionType;

	public OrganizationException(OrganizationExceptionType groupsExceptionType) {
		this.groupsExceptionType = groupsExceptionType;
	}

	@Override
	public BaseExceptionType getExceptionType() {
		return this.groupsExceptionType;
	}
}
