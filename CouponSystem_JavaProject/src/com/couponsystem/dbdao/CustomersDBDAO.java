package com.couponsystem.dbdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.couponsystem.beans.Customer;
import com.couponsystem.dao.CustomersDAO;
import com.couponsystem.db.ConnectionPool;

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

		try {
			connection = ConnectionPool.getInstance().getConnection();

			String sql = ADD_CUSTOMER_QUERY;

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, customer.getFirstName());
			statement.setString(2, customer.getLastName());
			statement.setString(3, customer.getEmail());
			statement.setString(4, customer.getPassword());

			statement.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
	}

	@Override
	public void updateCustomer(int customerID, Customer customer) {

		try {
			connection = ConnectionPool.getInstance().getConnection();

			String sql = UPDATE_CUSTOMER_QUERY;

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, customer.getFirstName());
			statement.setString(2, customer.getLastName());
			statement.setString(3, customer.getEmail());
			statement.setString(4, customer.getPassword());
			statement.setInt(5, customer.getId());

			statement.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
	}

	@Override
	public void deleteCustomer(int customerID) {

		try {
			connection = ConnectionPool.getInstance().getConnection();

			String sql = DELETE_CUSTOMER_QUERY;

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setInt(1, customerID);

			statement.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
	}

	@Override
	public Customer getOneCustomer(int customerID) {

		try {
			connection = ConnectionPool.getInstance().getConnection();

			String sql = GET_ONE_CUSTOMER_QUERY;

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setInt(1, customerID);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				Customer customer = new Customer();
				customer.setId(resultSet.getInt(1));
				customer.setFirstName(resultSet.getString(2));
				customer.setLastName(resultSet.getString(3));
				customer.setEmail(resultSet.getString(4));
				customer.setPassword(resultSet.getString(5));
				return customer;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
		return null;
	}

	@Override
	public List<Customer> getAllCustomers() {

		List<Customer> customers = new ArrayList<>();

		try {
			connection = ConnectionPool.getInstance().getConnection();

			String sql = GET_ALL_CUSTOMERS_QUERY;

			PreparedStatement statement = connection.prepareStatement(sql);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Customer customer = new Customer();
				customer.setId(resultSet.getInt(1));
				customer.setFirstName(resultSet.getString(2));
				customer.setLastName(resultSet.getString(3));
				customer.setEmail(resultSet.getString(4));
				customer.setPassword(resultSet.getString(5));
				customers.add(customer);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
		return customers;
	}

	@Override
	public boolean isCustomerExist(String email, String password) {

		try {
			connection = ConnectionPool.getInstance().getConnection();

			String sql = IS_CUSTOMER_EXISTS_QUERY;

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, email);
			statement.setString(2, password);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				return true;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
		return false;
	}

	/**
	 * This method has been used in customerFacade.login method.
	 */
	@Override
	public int getCustomerIdByEmailAndPasswordForLogin(String email, String password) {

		int id = 0;

		try {
			connection = ConnectionPool.getInstance().getConnection();

			String sql = GET_CUSTOMER_ID_BY_EMAIL_AND_PASSWORD_QUERY;

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, email);
			statement.setString(2, password);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				id = resultSet.getInt(1);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
		return id;
	}

}
