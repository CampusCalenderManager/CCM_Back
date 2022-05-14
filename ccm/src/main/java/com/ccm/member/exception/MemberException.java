package com.ccm.member.exception;

import com.ccm.common.exception.BaseException;
import com.ccm.common.exception.BaseExceptionType;
import com.ccm.organization.exception.OrganizationExceptionType;

/**
 * Created by ShinD on 2022/05/14.
 */
public class MemberException extends BaseException {
	private final MemberExceptionType memberExceptionType;

	public MemberException(MemberExceptionType memberExceptionType) {
		this.memberExceptionType = memberExceptionType;
	}

	@Override
	public BaseExceptionType getExceptionType() {
		return this.memberExceptionType;
	}
}
