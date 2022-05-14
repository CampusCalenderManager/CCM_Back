package com.ccm.common.exception;

/**
 * Created by ShinD on 2022/05/09.
 */
public abstract class BaseException extends RuntimeException {
	public abstract BaseExceptionType getExceptionType();
}
