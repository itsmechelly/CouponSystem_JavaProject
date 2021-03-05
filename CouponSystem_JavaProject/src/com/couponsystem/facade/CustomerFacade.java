package com.couponsystem.facade;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.couponsystem.beans.Category;
import com.couponsystem.beans.Coupon;
import com.couponsystem.beans.Customer;
import com.couponsystem.beans.CustomersVsCoupons;
import com.couponsystem.exceptions.CouponSystemException;
import com.couponsystem.exceptions.CouponsNotFoundException;
import com.couponsystem.exceptions.PurchaseCouponException;

public class CustomerFacade extends ClientFacade {

	private int customerId;

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

//	------------------------------------------------------------------------------------------------------------

	@Override
	public boolean login(String email, String password) {
		if (customersDAO.isCustomerExist(email, password)) {
			return true;
		}
		return false;
	}

	public int getCustomerIdByEmailAndPasswordForLogin(String email, String password) {
		return customersDAO.getCustomerIdByEmailAndPasswordForLogin(email, password);
	}

//	------------------------------------------------------------------------------------------------------------

	public void purchaseCoupon(Coupon coupon) throws CouponSystemException {

		Coupon couponFromDb = couponsDAO.getOneCoupon((coupon.getId()));
		List<CustomersVsCoupons> customerVsCoupons = couponsDAO.getAllCustomersVsCoupons();

		for (CustomersVsCoupons custVsCoup : customerVsCoupons) {
			if (custVsCoup.getCustomerId() == this.customerId && custVsCoup.getCouponId() == coupon.getId()) {
				throw new PurchaseCouponException("Purchasing this type of coupon is limited to one use only. you are welcome to choose another coupon.");
			}
		}
		if (couponFromDb.getAmount() == 0) {
			throw new PurchaseCouponException("Coupon out of stock, you are welcome to choose another coupon.");
		}
		if (couponFromDb.getEndDate().isBefore(LocalDate.now())) {
			throw new PurchaseCouponException("This coupon has expired, you are welcome to choose another coupon.");
		}

		System.out.println("Testing decrease amount by 1 after coupon purchase:");
		couponFromDb.setAmount(couponFromDb.getAmount() - 1);
		couponsDAO.updateCoupon(couponFromDb);
		System.out.println("Going to update purchase in all relevant tables...");
		couponsDAO.addCouponPurchase(this.customerId, coupon.getId());
		System.out.println("DONE");
		System.out.println();
		}

	public List<Coupon> getAllCustomerCoupons() throws CouponSystemException {

		List<Coupon> allCCustCoup = new ArrayList<Coupon>();
		allCCustCoup = couponsDAO.getAllCouponsByCustomerID(customerId);
		
		if (allCCustCoup.isEmpty()) {
			throw new CouponsNotFoundException();
		}
		return allCCustCoup;
	}

	public List<Coupon> getCustomerCouponsByCategory(Category category) throws CouponSystemException {

		List<Coupon> coupByCategory = new ArrayList<Coupon>();
		coupByCategory = couponsDAO.getAllCouponsByCategoryAndCustomerId(category, 2);			
		
		if (coupByCategory.isEmpty()) {
			throw new CouponsNotFoundException();
		}
		return coupByCategory;
	}

	public List<Coupon> getCustomerCouponsUnderMaxPrice(double maxPrice) throws CouponSystemException {
		
		List<Coupon> coupUnderMax = new ArrayList<Coupon>();
		coupUnderMax = couponsDAO.getAllCouponsByCustomerIdUnderMaxPrice(this.customerId, maxPrice);
		
		if (coupUnderMax.isEmpty()) {
			throw new CouponsNotFoundException();
		}
		return coupUnderMax;
	}

	public Customer getCustomerDetails() throws CouponSystemException {

		Customer cust = customersDAO.getOneCustomer(customerId);
		cust.setCoupons(getAllCustomerCoupons());
		return cust;
	}
	
}