package com.couponsystem.test;

import java.util.ArrayList;

import com.couponsystem.beans.Company;
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
import com.couponsystem.facade.AdminFacade;
import com.couponsystem.security.ClientType;
import com.couponsystem.security.LoginManager;
import com.couponsystem.utils.TestUtils;

public class AdminTest {

	public static void adminTest() {

		AdminFacade adminUser = null;
		
		CompaniesDAO companiesDAO = new CompaniesDBDAO();
		CustomersDAO customersDAO = new CustomersDBDAO();
		CouponsDAO couponsDAO = new CouponsDBDAO();
		CategoryDAO categoryDAO = new CategoryDBDAO();

		TestUtils.DoubleSeparatedLine();
		TestUtils.adminFacade();

//		------------------------------------------------------------------------------------------------------------

		try {

			TestUtils.testSeparatedLine("Going to test GOOD adminFacade.login:");
			adminUser = (AdminFacade) LoginManager.getInstance().login("admin@admin.com", "admin",
					ClientType.ADMINISTRATOR);

//			TestUtils.testSeparatedLine("Going to print *BAD* *Email* for adminFacade.login:");
//			adminUser = (AdminFacade) LoginManager.getInstance().login("BADadmin@BADadmin.com", "admin",
//					ClientType.ADMINISTRATOR);
//
//			TestUtils.testSeparatedLine("Going to print *BAD* *Password* for adminFacade.login:");
//			adminUser = (AdminFacade) LoginManager.getInstance().login("admin@admin.com", "BADadmin",
//					ClientType.ADMINISTRATOR);

		} catch (LogException e) {
			System.err.println(e.getMessage());
		}
		
//		------------------------------------------------------------------------------------------------------------

		try {
			
			TestUtils.testSeparatedLine("Testing Admin Facade - addCompany:");
			System.out.println("Going to add 5 companies... Server is running...");
			System.out.println();

			int numOfCompaniesToCreate = 5;
			String companyName;
			String companyEmail;
			String companyPassword;
			ArrayList<Coupon> companyCoupons;
			
			for (int i = 1; i < numOfCompaniesToCreate + 1; i++) {
				companyName = "Company" + i;
				companyEmail = companyName + "@mail.com";
				companyPassword = "" + (i*1111);
				companyCoupons = null;
				
				Company com = new Company();
				com.setName(companyName);
				com.setEmail(companyEmail);
				com.setPassword(companyPassword);
				com.setCoupons(companyCoupons);
				
				adminUser.addCompany(com);
				
				System.out.println(com.getName() + " was added successfully.");
			}
			
		} catch (AlreadyExistException e) {
			System.err.println(e.getMessage());
		}

		try {
			
			System.out.println("Going to test addCompany exception - *Company name* already exists:");
			
			Company com6 = new Company();
			com6.setName("Company1");
			com6.setEmail("Company5@mail.com");
			com6.setPassword("5555");
			com6.setCoupons(null);
			
			adminUser.addCompany(com6);

			System.out.println("Going to test addCompany exception - *Company email* already exists:");
			
			Company com7 = new Company();
			com7.setName("Company7");
			com6.setEmail("Company1@mail.com");
			com7.setPassword("7777");
			com7.setCoupons(null);
			
			adminUser.addCompany(com7);

		} catch (AlreadyExistException e) {
			System.err.println(e.getMessage());
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}