package com.couponsystem.dbdao;

import java.sql.Connection;
import java.util.List;

import com.couponsystem.beans.Customer;
import com.couponsystem.dao.CustomersDAO;

public class CustomersDBDAO implements CustomersDAO {

	private Connection connection;

	private static final String IS_CUSTOMER_EXISTS_QUERY = "SELECT * FROM coupon_system.customers WHERE `email` = ? AND `password` = ?;";
	private static final String ADD_CUSTOMER_QUERY = "INSERT INTO `coupon_system`.`customers` (`first_name`, `last_name`, `email`, `password`) VALUES (?, ?, ?, ?);";
	private static final String UPDATE_CUSTOMER_QUERY = "UPDATE `coupon_system`.`customers` SET `first_name` = ?, `last_name` = ?, `email` = ?, `password` = ? WHERE (`id` = ?);";
	private static final String DELETE_CUSTOMER_QUERY = "DELETE FROM `coupon_system`.`customers` WHERE (`id` = ?);";
	private static final String GET_ALL_CUSTOMERS_QUERY = "SELECT * FROM `coupon_system`.`customers`;";
	private static final String GET_ONE_CUSTOMER_QUERY = "SELECT * FROM coupon_system.customers WHERE `id` = ?;";
	private static final String GET_CUSTOMER_ID_BY_EMAIL_AND_PASSWORD_QUERY = "SELECT `id` FROM coupon_system.customers WHERE `email` = ? AND `password` = ?;";

	@Override
	public void addCustomer(Customer customer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCustomer(int customerID, Customer customer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCustomer(int customerID) {
		// TODO Auto-generated method stub

	}

	@Override
	public Customer getOneCustomer(int customerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCustomerExist(String email, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getCustomerIdByEmailAndPasswordForLogin(String email, String password) {
		// TODO Auto-generated method stub
		return 0;
	}

}
