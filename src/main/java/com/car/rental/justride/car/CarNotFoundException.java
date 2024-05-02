package com.car.rental.justride.car;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CarNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -3297064646976574994L;

	public CarNotFoundException(String message) {
		super(message);
	}
}
