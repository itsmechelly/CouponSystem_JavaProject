package com.couponsystem.DAO;

import java.util.List;

import com.couponsystem.beans.Customer;

public interface CustomersDAO {

	void addCustomer(Customer customer);

	void updateCustomer(int customerID, Customer customer);

	void deleteCustomer(int customerID);

	Customer getOneCustomer(int customerID);

	List<Customer> getAllCustomers();

	boolean isCustomerExist(String email, String password);

	// This method used in customerFacade.login method;
	int getCustomerIdByEmailAndPasswordForLogin(String email, String password);

}
