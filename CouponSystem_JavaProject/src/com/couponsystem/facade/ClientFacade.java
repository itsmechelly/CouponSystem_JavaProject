package com.couponsystem.facade;

import com.couponsystem.dao.CompaniesDAO;
import com.couponsystem.dao.CouponsDAO;
import com.couponsystem.dao.CustomersDAO;
import com.couponsystem.dbdao.CompaniesDBDAO;
import com.couponsystem.dbdao.CouponsDBDAO;
import com.couponsystem.dbdao.CustomersDBDAO;
import com.couponsystem.exceptions.CouponSystemException;

public abstract class ClientFacade {

	protected CompaniesDAO companiesDAO = null;
	protected CustomersDAO customersDAO = null;
	protected CouponsDAO couponsDAO = null;

	public ClientFacade() {
		companiesDAO = new CompaniesDBDAO();
		customersDAO = new CustomersDBDAO();
		couponsDAO = new CouponsDBDAO();
	}

	public abstract boolean login(String email, String password) throws CouponSystemException;
	
}
