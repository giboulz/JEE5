package com.gbz.yaps.petstore.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class ValidationException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9082629726422111963L;

	public ValidationException(String message) {
		super(message);
	}
}
