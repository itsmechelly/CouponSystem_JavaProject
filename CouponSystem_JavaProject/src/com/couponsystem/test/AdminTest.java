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
import com.couponsystem.exceptions.NotAllowedException;
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

		TestUtils.testSeparatedLine("Going to test GOOD adminFacade.login:");
		try {

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

		Company com1 = new Company();
		Company com2 = new Company();
		Company com3 = new Company();
		Company com4 = new Company();
		Company com5 = new Company();
		Company com6 = new Company();
		Company com7 = new Company();
		
		try {
			
			TestUtils.testSeparatedLine("Testing Admin Facade - addCompany:");
			System.out.println("Going to add 5 companies... Server is running...");

			com1.setName("Company1");
			com1.setEmail("Company1@email.com");
			com1.setPassword("1111");
			com1.setCoupons(null);
			
			com2.setName("Company2");
			com2.setEmail("Company2@email.com");
			com2.setPassword("2222");
			com2.setCoupons(null);
			
			com3.setName("Company3");
			com3.setEmail("Company8@email.com");
			com3.setPassword("8888");
			com3.setCoupons(null);
			
			com4.setName("Company4");
			com4.setEmail("Company4@email.com");
			com4.setPassword("4444");
			com4.setCoupons(null);
			
			com5.setName("Company5");
			com5.setEmail("Company5@email.com");
			com5.setPassword("5555");
			com5.setCoupons(null);
			
			adminUser.addCompany(com1);
			adminUser.addCompany(com2);
			adminUser.addCompany(com3);
			adminUser.addCompany(com4);
			adminUser.addCompany(com5);

			System.out.println("Added successfully: Company1, Company2, Company3, Company4, Company5.");

		} catch (AlreadyExistException e) {
			System.err.println(e.getMessage());
		}

		try {
			
			System.out.println();
			System.out.println("Going to test addCompany exception - *Company name* already exists:");
						
			com6.setName("Company1");
			com6.setEmail("Company6@email.com");
			com6.setPassword("6666");
			com6.setCoupons(null);
			
			adminUser.addCompany(com6);

		} catch (AlreadyExistException e) {
			System.out.println(e.getMessage());
		}
		
		try {

			System.out.println();
			System.out.println("Going to test addCompany exception - *Company email* already exists:");
			
			com7.setName("Company7");
			com7.setEmail("Company1@email.com");
			com7.setPassword("7777");
			com7.setCoupons(null);
			
			adminUser.addCompany(com7);

		} catch (AlreadyExistException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			
			TestUtils.testSeparatedLine("Testing Admin Facade - updateCompany:");
			System.out.println("Going to update Company3 email & password:");
			
			Company com3FromDB = adminUser.getOneCompany(3);

			System.out.println(com3FromDB);

			com3FromDB.setEmail("Company3@email.com");
			com3FromDB.setPassword("3333");
			com3FromDB.setCoupons(null);
		
			adminUser.updateCompany(3, com3FromDB);
			System.out.println("Updated successfully: Company3.");
			System.out.println(com3FromDB);

		} catch (NotAllowedException e) {
			System.out.println(e.getMessage());
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}