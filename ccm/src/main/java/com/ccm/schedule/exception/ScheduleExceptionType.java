package com.ccm.schedule.exception;

import org.springframework.http.HttpStatus;

import com.ccm.common.exception.BaseExceptionType;

/**
 * Created by ShinD on 2022/05/14.
 */
public enum ScheduleExceptionType implements BaseExceptionType {

	NOT_FOUND(500, HttpStatus.NOT_FOUND, "일정이 존재하지 않습니다"),
	NOT_FOUND_GROUP(502, HttpStatus.NOT_FOUND, "그룹이 존재하지 않습니다"),
	NO_AUTHORITY(503,HttpStatus.FORBIDDEN , "작업을 수행할 권한이 없습니다");


	private final int errorCode;
	private final HttpStatus httpStatus;
	private final String errorMessage;

	ScheduleExceptionType(int errorCode, HttpStatus httpStatus, String errorMessage) {
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
