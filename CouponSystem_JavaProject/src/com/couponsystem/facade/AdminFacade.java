package com.couponsystem.facade;

import java.util.List;

import com.couponsystem.beans.Company;
import com.couponsystem.beans.Coupon;
import com.couponsystem.beans.Customer;
import com.couponsystem.exceptions.AlreadyExistException;
import com.couponsystem.exceptions.LogException;
import com.couponsystem.exceptions.NotAllowedException;

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

		if (companiesDAO.isCompanyNameExists(company.getName())) {
			throw new AlreadyExistException("Company name ", company.getName());
		}
		if (companiesDAO.isCompanyEmailExists(company.getEmail())) {
			throw new AlreadyExistException("Company email ", company.getEmail());
		}
		companiesDAO.addCompany(company);
	}

	public void updateCompany(int companyID, Company company) throws NotAllowedException {

		Company comp = companiesDAO.getOneCompany(companyID);

		if (comp.getId() != company.getId()) {
			throw new NotAllowedException("update company id number.");
		}
		if (!comp.getName().equals(company.getName())) {
			throw new NotAllowedException("update company name.");
		}
		companiesDAO.updateCompany(company);
	}

	public void deleteCompany(int companyID) {

		List<Coupon> existingCouponsFromDB = couponsDAO.getAllCoupons();

		for (Coupon coupon : existingCouponsFromDB) {
			if (coupon.getCompanyId() == companyID) {
				couponsDAO.deleteCouponPurchaseForFacade(coupon.getId());
				couponsDAO.deleteCoupon(coupon.getId());
			}
		}
		companiesDAO.deleteCompany(companyID);
	}

	public Company getOneCompany(int companyID) {
		return companiesDAO.getOneCompany(companyID);
	}

	public List<Company> getAllCompanies() {

		List<Company> comp = companiesDAO.getAllCompanies();
		for (Company c : comp) {
			c.setCoupons(couponsDAO.getAllCouponsByCompanyID(c.getId()));
		}
		return comp;
	}

//	------------------------------------------------------------------------------------------------------------

	public void addCustomer(Customer customer) throws AlreadyExistException {

		List<Customer> customers = customersDAO.getAllCustomers();

		for (Customer cust : customers) {
			if (cust.getEmail().equalsIgnoreCase(customer.getEmail())) {
				throw new AlreadyExistException("Customer email ", customer.getEmail());
			}
		}
		customersDAO.addCustomer(customer);
	}

	public void updateCustomer(int customerID, Customer customer) throws NotAllowedException {

		Customer cust = customersDAO.getOneCustomer(customerID);

		if (cust.getId() != customer.getId()) {
			throw new NotAllowedException("update customer id number.");
		}

		customersDAO.updateCustomer(customerID, customer);
	}

	public void deleteCustomer(int customerID) {

		List<Coupon> originalCoupons = couponsDAO.getAllCoupons();

		for (Coupon coup : originalCoupons) {
			couponsDAO.deleteCouponPurchase(customerID, coup.getId());
		}

		customersDAO.deleteCustomer(customerID);
	}

	public Customer getOneCustomer(int customerID) {
		return customersDAO.getOneCustomer(customerID);
	}

	public List<Customer> getAllCustomers() {

		List<Customer> cust = customersDAO.getAllCustomers();
		for (Customer c : cust) {
			c.setCoupons(couponsDAO.getAllCouponsByCustomerID(c.getId()));
		}
		return cust;
	}

}
