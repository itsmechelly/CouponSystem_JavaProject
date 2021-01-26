package com.couponsystem.dbdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.couponsystem.beans.Company;
import com.couponsystem.dao.CompaniesDAO;
import com.couponsystem.db.ConnectionPool;

public class CompaniesDBDAO implements CompaniesDAO {

	private Connection connection;

	private static final String ADD_COMPANY_QUERY = "INSERT INTO `coupon_system`.`companies` (`name`, `email`, `password`) VALUES (?, ?, ?);";
	private static final String UPDATE_COMPANY_QUERY = "UPDATE `coupon_system`.`companies` SET `name` = ?, `email` = ?, `password` = ? WHERE (`id` = ?);";
	private static final String DELETE_COMPANY_QUERY = "DELETE FROM `coupon_system`.`companies` WHERE (`id` = ?);";
	private static final String GET_ONE_COMPANY_QUERY = "SELECT * FROM `coupon_system`.`companies` WHERE (`id` = ?);";
	private static final String GET_ALL_COMPANIES_QUERY = "SELECT * FROM `coupon_system`.`companies`;";
	private static final String IS_COMPANY_EXISTS_QUERY = "SELECT * FROM coupon_system.companies WHERE `email` = ? AND `password` = ?;";
	private static final String GET_COMPANY_ID_BY_EMAIL_AND_PASSWORD_QUERY = "SELECT `id` FROM coupon_system.companies WHERE `email` = ? AND `password` = ?;";

	@Override
	public void addCompany(Company company) {

		try {
			connection = ConnectionPool.getInstance().getConnection();

			String sql = ADD_COMPANY_QUERY;

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, company.getName());
			statement.setString(2, company.getEmail());
			statement.setString(3, company.getPassword());

			statement.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
	}

	@Override
	public void updateCompany(Company company) {

		try {
			connection = ConnectionPool.getInstance().getConnection();

			String sql = UPDATE_COMPANY_QUERY;

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, company.getName());
			statement.setString(2, company.getEmail());
			statement.setString(3, company.getPassword());
			statement.setInt(4, company.getId());

			statement.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}

	}

	@Override
	public void deleteCompany(int companyID) {
		// TODO Auto-generated method stub

	}

	@Override
	public Company getOneCompany(int companyID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Company> getAllCompanies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCompanyExists(String email, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getCompanyIdByEmailAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		return 0;
	}
}
