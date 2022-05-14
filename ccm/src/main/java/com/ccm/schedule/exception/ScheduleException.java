package com.ccm.schedule.exception;

import com.ccm.common.exception.BaseException;
import com.ccm.common.exception.BaseExceptionType;

/**
 * Created by ShinD on 2022/05/14.
 */
public class ScheduleException extends BaseException {

	private final ScheduleExceptionType scheduleExceptionType;

	public ScheduleException(ScheduleExceptionType scheduleExceptionType) {
		this.scheduleExceptionType = scheduleExceptionType;
	}

	@Override
	public BaseExceptionType getExceptionType() {
		return this.scheduleExceptionType;
	}
}

