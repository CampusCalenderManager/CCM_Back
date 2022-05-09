package com.ccm.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by ShinD on 2022/05/09.
 */
@Data
@AllArgsConstructor
public class ExceptionDto {
	private Integer errorCode;
	private String errorMessage;
}
