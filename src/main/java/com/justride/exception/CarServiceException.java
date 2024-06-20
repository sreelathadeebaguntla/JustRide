package com.justride.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class CarServiceException extends RuntimeException {

	private static final long serialVersionUID = -3297064646976574994L;

	public CarServiceException(String message) {
		super(message);
	}
}
