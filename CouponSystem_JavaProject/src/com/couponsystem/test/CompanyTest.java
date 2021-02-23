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
import com.couponsystem.facade.CompanyFacade;
import com.couponsystem.security.ClientType;
import com.couponsystem.security.LoginManager;
import com.couponsystem.utils.TestUtils;

public class CompanyTest {

	public static void companyTest() {


		CompaniesDAO companiesDAO = new CompaniesDBDAO();
		CustomersDAO customersDAO = new CustomersDBDAO();
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
		CompanyFacade companyUser = new CompanyFacade();

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

	}
}
