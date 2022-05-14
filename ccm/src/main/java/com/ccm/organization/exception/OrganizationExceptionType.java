package com.ccm.organization.exception;

import org.springframework.http.HttpStatus;

import com.ccm.common.exception.BaseExceptionType;

/**
 * Created by ShinD on 2022/05/10.
 */
public enum OrganizationExceptionType implements BaseExceptionType {

	NOT_FOUND(200, HttpStatus.NOT_FOUND, "그룹이 존재하지 않습니다"),
	MISMATCH_CODE(201,HttpStatus.BAD_REQUEST , "참여코드가 일치하지 않습니다"),
	NO_AUTHORITY(202,HttpStatus.FORBIDDEN , "작업을 수행할 권한이 없습니다"),
	MISMATCH_MEMBER_PRESIDENT(203, HttpStatus.BAD_REQUEST,"그룹 생성을 보낸 사람과 그룹의 대표가 일치하지 않습니다.");


	private final int errorCode;
	private final HttpStatus httpStatus;
	private final String errorMessage;

	OrganizationExceptionType(int errorCode, HttpStatus httpStatus, String errorMessage) {
		this.errorCode = errorCode;
		this.httpStatus = httpStatus;
		this.errorMessage = errorMessage;
	}

	@Override
	public int getErrorCode() {
		return this.errorCode;
	}

	@Override
	public HttpStatus getHttpStatus() {
		return this.httpStatus;
	}

	@Override
	public String getErrorMessage() {
		return this.errorMessage;
	}
}
