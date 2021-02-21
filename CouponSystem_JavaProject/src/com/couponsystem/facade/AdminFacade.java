package com.couponsystem.facade;

import java.util.List;

import com.couponsystem.beans.Company;
import com.couponsystem.exceptions.AlreadyExistException;
import com.couponsystem.exceptions.LogException;

public class AdminFacade extends ClientFacade {

//	------------------------------------------------------------------------------------------------------------

	@Override
	public boolean login(String email, String password) throws LogException {
		if (email.equalsIgnoreCase("admin@admin.com") && password.equals("admin")) {
			return true;
		}
		return false;
	}

//	------------------------------------------------------------------------------------------------------------
	
	public void addCompany(Company company) throws AlreadyExistException {

		List<Company> companies = companiesDAO.getAllCompanies();

		for (Company comp : companies) {
			if (comp.getName().equals(company.getName())) {
				throw new AlreadyExistException("Company name ", company.getName());
			}
			if (comp.getEmail().equalsIgnoreCase(company.getEmail())) {
				throw new AlreadyExistException("Company email ", company.getEmail());
			}
		}
		companiesDAO.addCompany(company);
	}
}
