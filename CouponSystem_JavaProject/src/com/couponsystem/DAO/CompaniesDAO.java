package com.couponsystem.DAO;

import java.util.List;

import com.couponsystem.beans.Company;

public interface CompaniesDAO {

	void addCompany(Company company);

	void updateCompany(Company company);

	void deleteCompany(int companyID);

	Company getOneCompany(int companyID);

	List<Company> getAllCompanies();

	boolean isCompanyExists(String email, String password);

	// This method used in companyFacade.login method;
	int getCompanyIdByEmailAndPassword(String email, String password);
}
