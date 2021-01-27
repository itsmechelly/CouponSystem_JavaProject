package com.couponsystem.dao;

import java.util.List;

import com.couponsystem.beans.Company;

public interface CompaniesDAO {

	void addCompany(Company company);

	void updateCompany(Company company);

	void deleteCompany(int companyID);

	Company getOneCompany(int companyID);

	List<Company> getAllCompanies();

	boolean isCompanyExists(String email, String password);

	// This method has been used in companyFacade.getCompanyIdByEmailAndPassword method.
	int getCompanyIdByEmailAndPasswordForLogin(String email, String password);
}
