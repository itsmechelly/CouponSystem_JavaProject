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

		CompaniesDAO companiesDAO = new CompaniesDBDAO();
		CustomersDAO customersDAO = new CustomersDBDAO();
		CouponsDAO couponsDAO = new CouponsDBDAO();
		CategoryDAO categoryDAO = new CategoryDBDAO();

		TestUtils.DoubleSeparatedLine();
		TestUtils.customerFacade();

//		------------------------------------------------------------------------------------------------------------

		TestUtils.testSeparatedLine("Testing Customer Facade - login:");
		CustomerFacade customerUser = null;

		try {
			System.out.println("Going to test login exception  - *WRONG* *Email* for customerFacade.login:");
			customerUser = (CustomerFacade) LoginManager.getInstance().login("BADcustomer@email.com", "2222",
					ClientType.CUSTOMER);
		} catch (LogException e) {
			System.out.println(e.getMessage());
		}

		try {
			System.out.println();
			System.out.println("Going to test login exception - *WRONG* *Password* for customerFacade.login:");
			customerUser = (CustomerFacade) LoginManager.getInstance().login("Customer2@email.com", "BADcustomer",
					ClientType.CUSTOMER);
		} catch (LogException e) {
			System.out.println(e.getMessage());
		}

		try {
			System.out.println();
			System.out.println("Going to test GOOD customerFacade.login:");
			customerUser = (CustomerFacade) LoginManager.getInstance().login("Customer2@email.com", "2222",
					ClientType.CUSTOMER);
		} catch (LogException e) {
			System.out.println(e.getMessage());
		}
		
//		------------------------------------------------------------------------------------------------------------

	}
}
