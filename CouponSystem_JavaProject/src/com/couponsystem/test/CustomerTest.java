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
import com.couponsystem.facade.CustomerFacade;
import com.couponsystem.security.ClientType;
import com.couponsystem.security.LoginManager;
import com.couponsystem.utils.TestUtils;

public class CustomerTest {

	public static void customerTest() {

		CustomerFacade customerUser = new CustomerFacade();

		CompaniesDAO companiesDAO = new CompaniesDBDAO();
		CustomersDAO customersDAO = new CustomersDBDAO();
		CouponsDAO couponsDAO = new CouponsDBDAO();
		CategoryDAO categoryDAO = new CategoryDBDAO();

		TestUtils.DoubleSeparatedLine();
		TestUtils.customerFacade();
		
//		------------------------------------------------------------------------------------------------------------

		try {

			TestUtils.testSeparatedLine("Going to test GOOD customerFacade.login:");
			customerUser = (CustomerFacade) LoginManager.getInstance().login("CustomerNum2@gmail.com", "2222",
					ClientType.CUSTOMER);

//			TestUtils.testSeparatedLine("Going to print *BAD* *Email* for customerFacade.login:");
//			customerUser = (CustomerFacade) LoginManager.getInstance().login("BADcustomer@BADcustomer.com", "2222",
//					ClientType.CUSTOMER);
//
//			TestUtils.testSeparatedLine("Going to print *BAD* *Password* for customerFacade.login:");
//			customerUser = (CustomerFacade) LoginManager.getInstance().login("CustomerNum2@gmail.com", "BADcustomer",
//					ClientType.CUSTOMER);

		} catch (LogException e) {
			System.err.println(e.getMessage());
		}
		
//		------------------------------------------------------------------------------------------------------------

	}
}
