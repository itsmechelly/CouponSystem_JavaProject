package com.couponsystem.test;

import com.couponsystem.beans.Category;
import com.couponsystem.beans.Coupon;
import com.couponsystem.dao.CouponsDAO;
import com.couponsystem.dbdao.CouponsDBDAO;
import com.couponsystem.exceptions.CouponSystemException;
import com.couponsystem.facade.CustomerFacade;
import com.couponsystem.security.ClientType;
import com.couponsystem.security.LoginManager;
import com.couponsystem.utils.TestUtils;

public class CustomerTest {

	public static void customerTest() {

		CouponsDAO couponsDAO = new CouponsDBDAO();

		TestUtils.DoubleSeparatedLine();
		TestUtils.customerFacade();

//		------------------------------------------------------------------------------------------------------------

		TestUtils.testSeparatedLine("Testing Customer Facade - login:");
		CustomerFacade customerUser = null;

		try {
			System.out.println("Going to test login exception  - *WRONG* *Email* for customerFacade.login:");
			customerUser = (CustomerFacade) LoginManager.getInstance().login("BADcustomer@email.com", "2222",
					ClientType.CUSTOMER);
		} catch (CouponSystemException e) {
			System.out.println(e.getMessage());
		}

		try {
			System.out.println();
			System.out.println("Going to test login exception - *WRONG* *Password* for customerFacade.login:");
			customerUser = (CustomerFacade) LoginManager.getInstance().login("Customer2@email.com", "BADcustomer",
					ClientType.CUSTOMER);
		} catch (CouponSystemException e) {
			System.out.println(e.getMessage());
		}

		try {
			System.out.println();
			System.out.println("Going to test GOOD customerFacade.login:");
			customerUser = (CustomerFacade) LoginManager.getInstance().login("Customer2@email.com", "2222",
					ClientType.CUSTOMER);
		} catch (CouponSystemException e) {
			System.out.println(e.getMessage());
		}

//		------------------------------------------------------------------------------------------------------------

		TestUtils.testSeparatedLine("Testing Customer Facade - purchaseCoupon:");
		System.out.println("Going to print GOOD coupon purchase - Customer2 purchase Coupon2 and Coupon4:");
		System.out.println();
		Coupon couFromDB222 = couponsDAO.getOneCoupon(2);
		Coupon couFromDB444 = couponsDAO.getOneCoupon(4);

		try {
			customerUser.purchaseCoupon(couFromDB222);
			customerUser.purchaseCoupon(couFromDB444);
		} catch (CouponSystemException e) {
			System.out.println(e.getMessage());
		}

		System.out.println(
				"Going to test exception - BAD coupon purchase - The customer can't purchase the same coupon more then once :");

		try {
			customerUser.purchaseCoupon(couFromDB444);
		} catch (CouponSystemException e) {
			System.out.println(e.getMessage());
		}

		System.out.println();
		System.out.println(
				"Going to test exception - BAD coupon purchase - The customer can't purchase the coupon if amount < 0 :");
		Coupon couFromDB555 = couponsDAO.getOneCoupon(5);

		try {
			customerUser.purchaseCoupon(couFromDB555);
		} catch (CouponSystemException e) {
			System.out.println(e.getMessage());
		}

		System.out.println();
		System.out.println(
				"Going to test exception - BAD coupon purchase - The customer can't purchase the coupon if the coupon has expired :");
		Coupon couFromDB11 = couponsDAO.getOneCoupon(1);

		try {
			customerUser.purchaseCoupon(couFromDB11);
		} catch (CouponSystemException e) {
			System.out.println(e.getMessage());
		}

		TestUtils.testSeparatedLine("Testing Customer Facade - getAllCustomerCoupons:");
		try {
			TestUtils.printCouponsTable(customerUser.getAllCustomerCoupons());
		} catch (CouponSystemException e) {
			System.out.println(e.getMessage());
		}

		TestUtils.testSeparatedLine("Testing Customer Facade - getCustomerCouponsByCategory:");
		System.out.println("Going to print customer coupon purchase - of electricity category only:");
		try {
			TestUtils.printCouponsTable(customerUser.getCustomerCouponsByCategory(Category.ELECTRICITY));
		} catch (CouponSystemException e) {
			System.out.println(e.getMessage());
		}

		TestUtils.testSeparatedLine("Testing Customer Facade - getCustomerCouponsUnderMaxPrice:");
		System.out.println("Going to print customer coupon purchase - under amount of 2500:");
		try {
			TestUtils.printCouponsTable(customerUser.getCustomerCouponsUnderMaxPrice(2500));
		} catch (CouponSystemException e) {
			System.out.println(e.getMessage());
		}

		TestUtils.testSeparatedLine("Testing Customer Facade - getCustomerDetails:");
		try {
			System.out.println(customerUser.getCustomerDetails());
			System.out.println();
		} catch (CouponSystemException e) {
			System.out.println(e.getMessage());
		}
	}
}
