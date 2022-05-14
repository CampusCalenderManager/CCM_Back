package com.ccm.common.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by ShinD on 2022/05/09.
 */
public interface BaseExceptionType {
	int getErrorCode();

	HttpStatus getHttpStatus();

	String getErrorMessage();
}