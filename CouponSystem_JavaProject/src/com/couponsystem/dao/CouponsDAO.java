package com.couponsystem.dao;

import java.util.List;

import com.couponsystem.beans.Category;
import com.couponsystem.beans.Coupon;
import com.couponsystem.beans.CustomersVsCoupons;

public interface CouponsDAO {

	void addCoupon(Coupon coupon);

	void updateCoupon(Coupon coupon);

	void deleteCoupon(int couponID);

	Coupon getOneCoupon(int couponID);

	List<Coupon> getAllCoupons();
	
	boolean isCouponTitleByCompanyIdExist(String title, int companyId);
	
	void deleteCouponByCouponIdAndCompanyId(int couponId, int companyId);

	List<Coupon> getAllCouponsByCompanyID(int companyID);
	
	List<Coupon> getAllCouponsByCategoryAndCompanyId(Category category, int companyId);

	List<Coupon> getAllCouponsByCompanyIdUnderMaxPrice(int companyId, double maxPrice);

	void addCouponPurchase(int customerID, int couponID);

	void deleteCouponPurchase(int customerID, int couponID);

	void deleteCouponPurchaseForFacade(int couponID);

	List<CustomersVsCoupons> getAllCustomersVsCoupons();

	List<Coupon> getAllCouponsByCustomerID(int customerID);

	List<Coupon> getAllCouponsByCategoryAndCustomerId(Category category, int CustomerId);

	List<Coupon> getAllCouponsByCustomerIdUnderMaxPrice(int CustomerId, double maxPrice);

}
