package com.couponsystem.exceptions;

public class CouponsNotFoundException extends CouponSystemException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CouponsNotFoundException() {
		super("Error - coupons from this type Not Found.");
	}
}
