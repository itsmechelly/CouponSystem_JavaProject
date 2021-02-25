package com.couponsystem.test;

import java.util.Date;

import com.couponsystem.beans.Category;
import com.couponsystem.beans.Coupon;
import com.couponsystem.dao.CategoryDAO;
import com.couponsystem.dao.CompaniesDAO;
import com.couponsystem.dao.CouponsDAO;
import com.couponsystem.dao.CustomersDAO;
import com.couponsystem.dbdao.CategoryDBDAO;
import com.couponsystem.dbdao.CompaniesDBDAO;
import com.couponsystem.dbdao.CouponsDBDAO;
import com.couponsystem.dbdao.CustomersDBDAO;
import com.couponsystem.exceptions.AlreadyExistException;
import com.couponsystem.exceptions.LogException;
import com.couponsystem.exceptions.NotAllowedException;
import com.couponsystem.exceptions.PurchaseCouponException;
import com.couponsystem.facade.CompanyFacade;
import com.couponsystem.security.ClientType;
import com.couponsystem.security.LoginManager;
import com.couponsystem.utils.TestUtils;

public class CompanyTest {

	public static void companyTest() {

		CouponsDAO couponsDAO = new CouponsDBDAO();
		CategoryDAO categoryDAO = new CategoryDBDAO();

		TestUtils.DoubleSeparatedLine();
		TestUtils.companyFacade();

//		------------------------------------------------------------------------------------------------------------

		try {
			categoryDAO.addCategory(1, "FOOD");
			categoryDAO.addCategory(2, "ELECTRICITY");
			categoryDAO.addCategory(3, "RESTAURANT");
			categoryDAO.addCategory(4, "VACATION");
		} catch (Exception e) {
			e.getMessage();
		}

//		------------------------------------------------------------------------------------------------------------

		TestUtils.testSeparatedLine("Testing Company Facade - login:");
		CompanyFacade companyUser = null;

		try {
			System.out.println("Going to test login exception - *WRONG* *Email* for companyFacade.login:");
			companyUser = (CompanyFacade) LoginManager.getInstance().login("BADcompany@email.com", "2222",
					ClientType.COMPANY);
		} catch (LogException e) {
			System.out.println(e.getMessage());
		}

		try {
			System.out.println();
			System.out.println("Going to test login exception - *WRONG* *Password* for companyFacade.login:");
			companyUser = (CompanyFacade) LoginManager.getInstance().login("Company2@email.com", "BADcompany",
					ClientType.COMPANY);
		} catch (LogException e) {
			System.out.println(e.getMessage());
		}

		try {
			System.out.println();
			System.out.println("Going to test GOOD companyFacade.login:");
			companyUser = (CompanyFacade) LoginManager.getInstance().login("Company2@email.com", "2222",
					ClientType.COMPANY);
		} catch (LogException e) {
			System.out.println(e.getMessage());
		}

//		------------------------------------------------------------------------------------------------------------

		TestUtils.testSeparatedLine("Testing Company Facade - addCompanyCoupon:");
		System.out.println("Going to add 6 coupons... Server is running...");

		Coupon cou1 = new Coupon();
		cou1.setCompanyId(2);
		cou1.setCategory(Category.values()[(0)]);
		cou1.setTitle("Title1");
		cou1.setDescription("10% Discount for All");
		cou1.setStartDate(new Date(2026, 06, 30));
		cou1.setEndDate(new Date(2026, 07, 06));
		cou1.setAmount(100);
		cou1.setPrice(1000);
		cou1.setImage("www.image1.com");

		Coupon cou2 = new Coupon();
		cou2.setCompanyId(2);
		cou2.setCategory(Category.values()[(1)]);
		cou2.setTitle("Title2");
		cou2.setDescription("Some description that going to be updated...");
		cou2.setStartDate(new Date(2026, 06, 30));
		cou2.setEndDate(new Date(2026, 07, 29));
		cou2.setAmount(200);
		cou2.setPrice(2000);
		cou2.setImage("www.image2.com");

		Coupon cou3 = new Coupon();
		cou3.setCompanyId(2);
		cou3.setCategory(Category.values()[(2)]);
		cou3.setTitle("Title3");
		cou3.setDescription("30% Discount for All");
		cou3.setStartDate(new Date(2026, 06, 30));
		cou3.setEndDate(new Date(2026, 07, 29));
		cou3.setAmount(300);
		cou3.setPrice(3000);
		cou3.setImage("www.image3.com");

		Coupon cou4 = new Coupon();
		cou4.setCompanyId(2);
		cou4.setCategory(Category.values()[(3)]);
		cou4.setTitle("Title4");
		cou4.setDescription("40% Discount for All");
		cou4.setStartDate(new Date(2026, 06, 30));
		cou4.setEndDate(new Date(2026, 07, 29));
		cou4.setAmount(400);
		cou4.setPrice(4000);
		cou4.setImage("www.image4.com");

		Coupon cou5 = new Coupon();
		cou5.setCompanyId(3);
		cou5.setCategory(Category.values()[(2)]);
		cou5.setTitle("Title5");
		cou5.setDescription("50% Discount for All");
		cou5.setStartDate(new Date(2026, 06, 30));
		cou5.setEndDate(new Date(2026, 07, 29));
		cou5.setAmount(0);
		cou5.setPrice(5000);
		cou5.setImage("www.image5.com");

		Coupon cou6 = new Coupon();
		cou6.setCompanyId(2);
		cou6.setCategory(Category.values()[(3)]);
		cou6.setTitle("Title6");
		cou6.setDescription("60% Discount for All");
		cou6.setStartDate(new Date(2026, 06, 30));
		cou6.setEndDate(new Date(2026, 07, 29));
		cou6.setAmount(600);
		cou6.setPrice(6000);
		cou6.setImage("www.image6.com");

		try {
			companyUser.addCompanyCoupon(cou1);
			companyUser.addCompanyCoupon(cou2);
			companyUser.addCompanyCoupon(cou3);
			companyUser.addCompanyCoupon(cou4);
			companyUser.addCompanyCoupon(cou5);
			companyUser.addCompanyCoupon(cou6);
			System.out.println("Added successfully: Coupon1, Coupon2, Coupon3, Coupon4, Coupon5, Coupon6.");
		} catch (AlreadyExistException e) {
			System.out.println(e.getMessage());
		}

		System.out.println();
		System.out.println("Going to test addCompanyCoupon exception - *Title* already exists:");

		Coupon cou7 = new Coupon();
		cou7.setCompanyId(1);
		cou7.setCategory(Category.values()[(1)]);
		cou7.setTitle("Title2");
		cou7.setDescription("70% Discount for All");
		cou7.setStartDate(new Date(2026, 06, 30));
		cou7.setEndDate(new Date(2026, 07, 29));
		cou7.setAmount(700);
		cou7.setPrice(7000);
		cou7.setImage("www.image7.com");

		try {
			companyUser.addCompanyCoupon(cou7);
		} catch (AlreadyExistException e) {
			System.out.println(e.getMessage());
		}

		TestUtils.testSeparatedLine("Testing Company Facade - updateCompanyCoupon:");
		System.out.println("Going to update - Coupon2:");

		Coupon couFromDB2 = couponsDAO.getOneCoupon(2);
		couFromDB2.setDescription("20% Discount for All");

		try {
			companyUser.updateCompanyCoupon(couFromDB2);
			System.out.println("Updated successfully: Company2.");
		} catch (NotAllowedException e) {
			System.out.println(e.getMessage());
		} catch (PurchaseCouponException e) {
			System.out.println(e.getMessage());
		}

		TestUtils.testSeparatedLine("Testing Company Facade - deleteCompanyCoupon:");
		System.out.println("Going to delete Coupon6 that belongs to Company2:");

		try {
			companyUser.deleteCompanyCoupons(6);
			System.out.println("Deleted successfully: Coupon6.");
		} catch (NotAllowedException e) {
			System.out.println(e.getMessage());
		}

		System.out.println();
		System.out.println("Going to test deleteCompanyCoupons exception - other company Coupon5 - is not allowed:");

		try {
			companyUser.deleteCompanyCoupons(5);
		} catch (NotAllowedException e) {
			System.out.println(e.getMessage());
		}

		TestUtils.testSeparatedLine("Testing Company Facade - getAllCompanyCoupons:");
		TestUtils.printCouponsTable(companyUser.getAllCompanyCoupons());

		TestUtils.testSeparatedLine("Testing Company Facade - getCompanyCouponsByCategory:");
		System.out.println("Going to print *Electricity category coupons*:");
		TestUtils.printCouponsTable(companyUser.getCompanyCouponsByCategory(Category.ELECTRICITY));

		TestUtils.testSeparatedLine("Testing Company Facade - getCompanyCouponsUnderMaxPrice:");
		System.out.println("Going to print company coupon purchase - under amount of 2500:");
		TestUtils.printCouponsTable(companyUser.getCompanyCouponsUnderMaxPrice(2500));

		TestUtils.testSeparatedLine("Testing Company Facade - getCompanyDetails:");
		System.out.println(companyUser.getCompanyDetails());

	}
}
