package com.couponsystem.exceptions;

public class LogException extends CouponSystemException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LogException() {
		super("Email or password incorrect, Please try again.");
	}
}
