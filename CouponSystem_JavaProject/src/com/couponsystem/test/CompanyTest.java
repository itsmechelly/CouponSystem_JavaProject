package com.couponsystem.test;

import com.couponsystem.dao.CategoryDAO;
import com.couponsystem.dao.CompaniesDAO;
import com.couponsystem.dao.CouponsDAO;
import com.couponsystem.dao.CustomersDAO;
import com.couponsystem.dbdao.CategoryDBDAO;
import com.couponsystem.dbdao.CompaniesDBDAO;
import com.couponsystem.dbdao.CouponsDBDAO;
import com.couponsystem.dbdao.CustomersDBDAO;
import com.couponsystem.facade.CompanyFacade;
import com.couponsystem.utils.TestUtils;

public class CompanyTest {

	public static void companyTest() {

		CompanyFacade companyFacade = new CompanyFacade();

		CompaniesDAO companiesDAO = new CompaniesDBDAO();
		CustomersDAO customersDAO = new CustomersDBDAO();
		CouponsDAO couponsDAO = new CouponsDBDAO();
		CategoryDAO categoryDAO = new CategoryDBDAO();

		TestUtils.DoubleSeparatedLine();
		TestUtils.companyFacade();

		try {

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
