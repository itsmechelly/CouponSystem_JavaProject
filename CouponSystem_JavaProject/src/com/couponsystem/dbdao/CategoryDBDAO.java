package com.couponsystem.dbdao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.couponsystem.dao.CategoryDAO;
import com.couponsystem.db.ConnectionPool;

// Those three below are relevant to all dbdao classes:
// TODO -> SQL syntax should be improved.
// TODO -> Should I use ARM technique to manage connections?
// TODO -> Should I add specific exception?
public class CategoryDBDAO implements CategoryDAO {

	private Connection connection;

	@Override
	public void addCategory(int id, String category) {

		try {

			connection = ConnectionPool.getInstance().getConnection();

			String sql = "INSERT INTO `coupon_system`.`categories` (`id`, `name`) VALUES (?, ?);";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.setNString(2, category);
			statement.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
	}
}
