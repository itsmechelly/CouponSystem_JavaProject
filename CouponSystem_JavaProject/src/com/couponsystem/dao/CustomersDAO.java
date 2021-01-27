package com.couponsystem.dao;

import java.util.List;

import com.couponsystem.beans.Customer;

public interface CustomersDAO {

	void addCustomer(Customer customer);

	void updateCustomer(int customerID, Customer customer);

	void deleteCustomer(int customerID);

	Customer getOneCustomer(int customerID);

	List<Customer> getAllCustomers();

	boolean isCustomerExist(String email, String password);

	// This method has been used in customerFacade.getCustomerIdByEmailAndPasswordForLogin method.
	int getCustomerIdByEmailAndPasswordForLogin(String email, String password);

}
