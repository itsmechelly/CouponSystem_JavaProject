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

		CompanyFacade companyUser = new CompanyFacade();

		CompaniesDAO companiesDAO = new CompaniesDBDAO();
		CustomersDAO customersDAO = new CustomersDBDAO();
		CouponsDAO couponsDAO = new CouponsDBDAO();
		CategoryDAO categoryDAO = new CategoryDBDAO();

		TestUtils.DoubleSeparatedLine();
		TestUtils.companyFacade();

		try {

			categoryDAO.addCategory(1, "FOOD");
			categoryDAO.addCategory(2, "ELECTRICITY");
			categoryDAO.addCategory(3, "RESTAURANT");
			categoryDAO.addCategory(4, "VACATION");
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}

		try {

			TestUtils.testSeparatedLine("Going to test GOOD companyFacade.login:");
			companyUser = (CompanyFacade) LoginManager.getInstance().login("CompanyNum2@gmail.com", "2222",
					ClientType.COMPANY);

//			TestUtils.testSeparatedLine("Going to print *BAD* *Email* for companyFacade.login:");
//			companyUser = (CompanyFacade) LoginManager.getInstance().login("BADcompany@BADcompany.com", "2222",
//					ClientType.COMPANY);
//			
//			TestUtils.testSeparatedLine("Going to print *BAD* *Password* for companyFacade.login:");
//			companyUser = (CompanyFacade) LoginManager.getInstance().login("CompanyNum2@gmail.com", "BADcompany",
//					ClientType.COMPANY);
			
		} catch (LogException e) {
			System.err.println(e.getMessage());
		}
	}
}
