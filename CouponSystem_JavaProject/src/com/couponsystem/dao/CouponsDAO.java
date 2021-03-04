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

	void addCouponPurchase(int customerID, int couponID);

	void deleteCouponPurchase(int customerID, int couponID);

//	This method used in customerFacade.addPurchase method;
	boolean isCouponExists(int couponID);

//	This method used in CustomerFacade.purchaseCoupon method;
	List<CustomersVsCoupons> getAllCustomersVsCoupons();

//	This method used in CompanyFacade.addCompanyCoupon method;
	List<Coupon> getAllCouponsByCompanyID(int companyID);

//	This method used in CustomerFacade.addCompanyCoupon method;
	List<Coupon> getAllCouponsByCustomerID(int customerID);

// 	This method used in adminFacade.deleteCompany + companyFacade.deleteCompanyCoupon + CouponExpirationDailyJob method;
	void deleteCouponPurchaseForFacade(int couponID);

//	->
// This method has been used in companyFacade.addCompanyCoupon method.
	boolean isCouponTitleByCompanyIdExist(String title, int companyId);
	
// This method has been used in companyFacade.deleteCompanyCoupons method.
	void deleteCouponByCouponIdAndCompanyId(int couponId, int companyId);

// This method has been used in companyFacade.getCompanyCouponsByCategory method.
	List<Coupon> getAllCouponsByCategoryAndCompanyId(Category category, int companyId);

// This method has been used in companyFacade.getCompanyCouponsByCategory method.
	List<Coupon> getAllCouponsByCompanyIdUnderMaxPrice(int companyId, double maxPrice);
}
