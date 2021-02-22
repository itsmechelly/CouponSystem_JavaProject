package com.couponsystem.test;

import com.couponsystem.beans.Company;
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

		TestUtils.testSeparatedLine("Testing Admin Facade - addCompany:");
		System.out.println("Going to add 5 companies... Server is running...");
		
		Company com1 = new Company();		
		com1.setName("Company1");
		com1.setEmail("Company1@email.com");
		com1.setPassword("1111");
		com1.setCoupons(null);
		
		Company com2 = new Company();
		com2.setName("Company2");
		com2.setEmail("Company2@email.com");
		com2.setPassword("2222");
		com2.setCoupons(null);
		
		Company com3 = new Company();
		com3.setName("Company3");
		com3.setEmail("Company8@email.com");
		com3.setPassword("8888");
		com3.setCoupons(null);
		
		Company com4 = new Company();
		com4.setName("Company4");
		com4.setEmail("Company4@email.com");
		com4.setPassword("4444");
		com4.setCoupons(null);
		
		Company com5 = new Company();
		com5.setName("Company5");
		com5.setEmail("Company5@email.com");
		com5.setPassword("5555");
		com5.setCoupons(null);
		
		try {
			adminUser.addCompany(com1);
			adminUser.addCompany(com2);
			adminUser.addCompany(com3);
			adminUser.addCompany(com4);
			adminUser.addCompany(com5);
			System.out.println("Added successfully: Company1, Company2, Company3, Company4, Company5.");
		} catch (AlreadyExistException e) {
			System.err.println(e.getMessage());
		}

		System.out.println();
		System.out.println("Going to test addCompany exception - *Company name* already exists:");
		
		Company com6 = new Company();
		com6.setName("Company1");
		com6.setEmail("Company6@email.com");
		com6.setPassword("6666");
		com6.setCoupons(null);
		
		try {
			adminUser.addCompany(com6);
		} catch (AlreadyExistException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println();
		System.out.println("Going to test addCompany exception - *Company email* already exists:");
		
		Company com7 = new Company();
		com7.setName("Company7");
		com7.setEmail("Company1@email.com");
		com7.setPassword("7777");
		com7.setCoupons(null);
		
		try {
			adminUser.addCompany(com7);
		} catch (AlreadyExistException e) {
			System.out.println(e.getMessage());
		}
		
		TestUtils.testSeparatedLine("Testing Admin Facade - updateCompany:");
		System.out.println("Going to update Company3 email & password:");
		
		Company com3FromDB = adminUser.getOneCompany(3);
		com3FromDB.setEmail("Company3@email.com");
		com3FromDB.setPassword("3333");
		com3FromDB.setCoupons(null);
		
		try {
			adminUser.updateCompany(3, com3FromDB);
			System.out.println("Updated successfully: Company3.");
		} catch (NotAllowedException e) {
			System.out.println(e.getMessage());
		}

		System.out.println();
		System.out.println("Going to test updateCompany exception - updating *Company name* is not allowed:");
		com3FromDB.setName("Company8");
//		com3FromDB.setName("Company3");
//		
//		System.out.println();
//		System.out.println("Going to test updateCompany exception - updating *Company id* is not allowed:");
//		com3FromDB.setId(3);
//		com3FromDB.setId(2);
//		com3FromDB.setId(222);
			
		try {
			adminUser.updateCompany(3, com3FromDB);
		} catch (NotAllowedException e) {
			System.out.println(e.getMessage());
		}
		
		TestUtils.testSeparatedLine("Testing Admin Facade - deleteCompany:");
		System.out.println("Going to delete Company4:");
		adminUser.deleteCompany(4);
		System.out.println("Deleted successfully: Company4.");
			
		TestUtils.testSeparatedLine("Testing Admin Facade - getOneCompany:");
		System.out.println(adminUser.getOneCompany(3));
		
		TestUtils.testSeparatedLine("Testing Admin Facade - getAllCompanies:");
		TestUtils.printCompaniesTable(companiesDAO.getAllCompanies());
		
//		------------------------------------------------------------------------------------------------------------

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}