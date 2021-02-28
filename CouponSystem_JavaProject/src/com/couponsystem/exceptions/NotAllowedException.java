package com.couponsystem.exceptions;

public class NotAllowedException extends CouponSystemException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotAllowedException(String string) {
		super("System error, unable to " + string);
	}

}
