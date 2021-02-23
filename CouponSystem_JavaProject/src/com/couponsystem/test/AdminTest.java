package com.couponsystem.test;

import com.couponsystem.beans.Company;
import com.couponsystem.beans.Customer;
import com.couponsystem.exceptions.AlreadyExistException;
import com.couponsystem.exceptions.LogException;
import com.couponsystem.exceptions.NotAllowedException;
import com.couponsystem.facade.AdminFacade;
import com.couponsystem.security.ClientType;
import com.couponsystem.security.LoginManager;
import com.couponsystem.utils.TestUtils;

public class AdminTest {

	public static void adminTest() {

		TestUtils.DoubleSeparatedLine();
		TestUtils.adminFacade();

//		------------------------------------------------------------------------------------------------------------

		TestUtils.testSeparatedLine("Testing Admin Facade - login:");
		AdminFacade adminUser = null;

		try {
			System.out.println("Going to test login exception - *WRONG* *Email* for adminFacade.login:");
			adminUser = (AdminFacade) LoginManager.getInstance().login("BADadmin@BADadmin.com", "admin",
					ClientType.ADMINISTRATOR);
		} catch (LogException e) {
			System.out.println(e.getMessage());
		}

		try {
			System.out.println();
			System.out.println("Going to test login exception - *WRONG* *Password* for adminFacade.login:");
			adminUser = (AdminFacade) LoginManager.getInstance().login("admin@admin.com", "BADadmin",
					ClientType.ADMINISTRATOR);
		} catch (LogException e) {
			System.out.println(e.getMessage());
		}

		try {
			System.out.println();
			System.out.println("Going to test GOOD adminFacade.login:");
			adminUser = (AdminFacade) LoginManager.getInstance().login("admin@admin.com", "admin",
					ClientType.ADMINISTRATOR);
		} catch (LogException e) {
			System.out.println(e.getMessage());
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
		System.out.println("Going to delete Company5:");
		adminUser.deleteCompany(5);
		System.out.println("Deleted successfully: Company5.");

		TestUtils.testSeparatedLine("Testing Admin Facade - getOneCompany:");
		System.out.println(adminUser.getOneCompany(3));

		TestUtils.testSeparatedLine("Testing Admin Facade - getAllCompanies:");
		TestUtils.printCompaniesTable(adminUser.getAllCompanies());

//		------------------------------------------------------------------------------------------------------------

		TestUtils.testSeparatedLine("Testing Admin Facade - addCustomer:");
		System.out.println("Going to add 5 customers... Server is running...");

		Customer cus1 = new Customer();
		cus1.setFirstName("Customer1FirstName");
		cus1.setLastName("Customer1LastName");
		cus1.setEmail("Customer1@email.com");
		cus1.setPassword("1111");
		cus1.setCoupons(null);

		Customer cus2 = new Customer();
		cus2.setFirstName("Customer2FirstName");
		cus2.setLastName("Customer2LastName");
		cus2.setEmail("Customer2@email.com");
		cus2.setPassword("2222");
		cus2.setCoupons(null);

		Customer cus3 = new Customer();
		cus3.setFirstName("Customer3FirstName");
		cus3.setLastName("Customer3LastName");
		cus3.setEmail("Customer8@email.com");
		cus3.setPassword("8888");
		cus3.setCoupons(null);

		Customer cus4 = new Customer();
		cus4.setFirstName("Customer4FirstName");
		cus4.setLastName("Customer4LastName");
		cus4.setEmail("Customer4@email.com");
		cus4.setPassword("4444");
		cus4.setCoupons(null);

		Customer cus5 = new Customer();
		cus5.setFirstName("Customer5FirstName");
		cus5.setLastName("Customer5LastName");
		cus5.setEmail("Customer5@email.com");
		cus5.setPassword("5555");
		cus5.setCoupons(null);

		try {
			adminUser.addCustomer(cus1);
			adminUser.addCustomer(cus2);
			adminUser.addCustomer(cus3);
			adminUser.addCustomer(cus4);
			adminUser.addCustomer(cus5);
			System.out.println("Added successfully: Customer1, Customer2, Customer3, Customer4, Customer5.");
		} catch (AlreadyExistException e) {
			System.out.println(e.getMessage());
		}

		System.out.println();
		System.out.println("Going to test addCustomer exception - *Customer email* already exists:");

		Customer cus6 = new Customer();
		cus6.setFirstName("Customer6FirstName");
		cus6.setLastName("Customer6LastName");
		cus6.setEmail("Customer1@email.com");
		cus6.setPassword("6666");
		cus6.setCoupons(null);

		try {
			adminUser.addCustomer(cus6);
		} catch (AlreadyExistException e) {
			System.out.println(e.getMessage());
		}

		TestUtils.testSeparatedLine("Testing Admin Facade - updateCustomer:");
		System.out.println("Going to update Customer3 email & password:");

		Customer custFromDB = adminUser.getOneCustomer(3);
		custFromDB.setEmail("Customer3@email.com");
		custFromDB.setPassword("3333");

//		System.out.println("Going to test updateCustomer exception - updating *Customer id* is not allowed:");
//		custFromDB.setId(77);

		try {
			adminUser.updateCustomer(3, custFromDB);
			System.out.println("Updated successfully: Customer3.");
		} catch (NotAllowedException e) {
			System.out.println(e.getMessage());
		}

		TestUtils.testSeparatedLine("Testing Admin Facade - deleteCustomer:");
		System.out.println("Going to delete Customer5:");
		adminUser.deleteCustomer(5);
		System.out.println("Deleted successfully: Customer5.");

		TestUtils.testSeparatedLine("Testing Admin Facade - getOneCustomer:");
		System.out.println(adminUser.getOneCustomer(3));

		TestUtils.testSeparatedLine("Testing Admin Facade - getAllCustomers:");
		TestUtils.printCustomersTable(adminUser.getAllCustomers());

	}
}