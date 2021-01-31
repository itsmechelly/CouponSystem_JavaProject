package com.couponsystem.test;

import com.couponsystem.dao.CategoryDAO;
import com.couponsystem.dao.CompaniesDAO;
import com.couponsystem.dao.CouponsDAO;
import com.couponsystem.dao.CustomersDAO;
import com.couponsystem.dbdao.CategoryDBDAO;
import com.couponsystem.dbdao.CompaniesDBDAO;
import com.couponsystem.dbdao.CouponsDBDAO;
import com.couponsystem.dbdao.CustomersDBDAO;
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

	}
}