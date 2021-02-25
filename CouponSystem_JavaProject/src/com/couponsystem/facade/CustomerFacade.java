package com.couponsystem.facade;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.couponsystem.beans.Category;
import com.couponsystem.beans.Coupon;
import com.couponsystem.beans.Customer;
import com.couponsystem.beans.CustomersVsCoupons;
import com.couponsystem.exceptions.LogException;
import com.couponsystem.exceptions.PurchaseCouponException;

public class CustomerFacade extends ClientFacade {

	private int customerId;

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public CustomerFacade() {
		super();
	}

//	------------------------------------------------------------------------------------------------------------

	@Override
	public boolean login(String email, String password) throws LogException {
		if (customersDAO.isCustomerExist(email, password)) {
			return true;
		}
		return false;
	}

	public int getCustomerIdByEmailAndPasswordForLogin(String email, String password) {
		return customersDAO.getCustomerIdByEmailAndPasswordForLogin(email, password);
	}

//	------------------------------------------------------------------------------------------------------------

	public void purchaseCoupon(Coupon coupon) throws PurchaseCouponException {

//		The customer can't purchase the coupon more then once:

		List<CustomersVsCoupons> customerVsCoupons = couponsDAO.getAllCustomersVsCoupons();

		for (CustomersVsCoupons custVsCoup : customerVsCoupons) {
			if (custVsCoup.getCustomerId() == customerId && custVsCoup.getCouponId() == coupon.getId()) {
				throw new PurchaseCouponException("Purchasing this type of coupon is limited to one use only."
						+ " you are welcome to choose another coupon.");
			}
		}

//		The customer can't purchase the coupon if amount<0:

		Coupon couponFromDb = couponsDAO.getOneCoupon((coupon.getId()));

		if (couponFromDb.getAmount() == 0) {
			throw new PurchaseCouponException("Coupon out of stock, you are welcome to choose another coupon.");
		}

//		The customer can't purchase the coupon if the coupon has expired:

		if (couponFromDb.getEndDate().before(java.sql.Date.valueOf(LocalDate.now()))) {
			throw new PurchaseCouponException("This coupon has expired, you are welcome to choose another coupon.");
		}

//		If purchase was done - going to lower the quantity in stock:

		System.out.println("Testing decrease amount by 1 after coupon purchase:");
		System.out.println(couponFromDb);
		couponFromDb.setAmount(couponFromDb.getAmount() - 1);
		System.out.println(couponFromDb);

//			Going to update purchase in all relevant tables:

		couponsDAO.updateCoupon(couponFromDb);
		System.out.println("Going to update purchase in all relevant tables...");
		couponsDAO.addCouponPurchase(customerId, coupon.getId());
		System.out.println("DONE");
		System.out.println(couponsDAO.getAllCouponsByCustomerID(customerId));
		System.out.println("************************************************************************************");
	}

	public List<Coupon> getAllCustomerCoupons() {

		return couponsDAO.getAllCouponsByCustomerID(customerId);
	}

	public List<Coupon> getCustomerCouponsByCategory(Category category) {

		List<Coupon> coup = couponsDAO.getAllCouponsByCustomerID(customerId);
		List<Coupon> coupByCategory = new ArrayList<Coupon>();

		for (Coupon c : coup) {
			if (c.getCategory().equals(category)) {
				coupByCategory.add(c);
			}
		}
		return coupByCategory;
	}

	public List<Coupon> getCustomerCouponsUnderMaxPrice(double maxPrice) {

		List<Coupon> coup = couponsDAO.getAllCouponsByCustomerID(customerId);
		List<Coupon> CouponsUnderMaxPrice = new ArrayList<Coupon>();

		for (Coupon c : coup) {
			if (c.getPrice() < maxPrice) {
				CouponsUnderMaxPrice.add(c);
			}
		}
		return CouponsUnderMaxPrice;
	}

	public Customer getCustomerDetails() {

		Customer cust = customersDAO.getOneCustomer(customerId);
		cust.setCoupons(getAllCustomerCoupons());

		return cust;
	}
}