package com.couponsystem.beans;

public class CustomerVsCoupons {

	private int customerId;
	private int couponId;

	public CustomerVsCoupons() {
		super();
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getCouponId() {
		return couponId;
	}

	public void setCouponId(int couponId) {
		this.couponId = couponId;
	}

	@Override
	public String toString() {
		return "CustomerVsCoupons [customerId=" + customerId + ", couponId=" + couponId + "]";
	}
}
