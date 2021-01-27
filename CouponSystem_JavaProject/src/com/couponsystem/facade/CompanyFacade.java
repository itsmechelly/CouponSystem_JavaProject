package com.couponsystem.facade;

import com.couponsystem.exceptions.LogException;

public class CompanyFacade extends ClientFacade {

	private int companyId;

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public CompanyFacade() {
		super();
	}

//	------------------------------------------------------------------------------------------------------------

	@Override
	public boolean login(String email, String password) throws LogException {
		// TODO Auto-generated method stub
		return false;
	}

	public int getCompanyIdByEmailAndPassword(String email, String password) {
		return companiesDAO.getCompanyIdByEmailAndPasswordForLogin(email, password);
	}

//	------------------------------------------------------------------------------------------------------------

}
