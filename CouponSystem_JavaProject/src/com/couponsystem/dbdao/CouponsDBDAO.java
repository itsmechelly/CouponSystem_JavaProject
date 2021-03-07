package com.couponsystem.dbdao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.couponsystem.beans.Category;
import com.couponsystem.beans.Coupon;
import com.couponsystem.beans.CustomersVsCoupons;
import com.couponsystem.dao.CouponsDAO;
import com.couponsystem.db.ConnectionPool;

public class CouponsDBDAO implements CouponsDAO {

	private Connection connection;

	private static final String ADD_COUPON_QUERY = "INSERT INTO `coupon_system`.`coupons` (`company_id`, `category_id`, `title`, `description`, `start_date`, `end_date`, `amount`, `price`, `image`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String UPDATE_COUPON_QUERY = "UPDATE `coupon_system`.`coupons` SET `company_id` = ?, `category_id` = ?, `title` = ?, `description` = ?, `start_date` = ?, `end_date` = ?, `amount` = ?, `price` = ?, `image` = ? WHERE (`id` = ?);";
	private static final String DELETE_COUPON_QUERY = "DELETE FROM `coupon_system`.`coupons` WHERE (`id` = ?);";
	private static final String GET_ONE_COUPON_QUERY = "SELECT * FROM `coupon_system`.`coupons` WHERE (`id` = ?);";
	private static final String GET_ALL_COUPONS_QUERY = "SELECT * FROM `coupon_system`.`coupons`;";

	private static final String IS_COUPON_TITLE_BY_COMPANY_ID_EXIST_QUERY = "SELECT `title` FROM coupon_system.coupons WHERE `title` = ? AND `company_id` = ?;";
	private static final String DELETE_COUPON_BY_COUPON_ID_AND_COMPANY_ID_QUERY = "DELETE FROM `coupon_system`.`coupons` WHERE `id` = ? AND `company_id` = ?;";
	private static final String GET_ALL_COUPONS_BY_COMPANY_ID_QUERY = "SELECT * FROM `coupon_system`.`coupons` WHERE (`company_id` = ?);";
	private static final String GET_ALL_COUPONS_BY_CATEGORY_AND_COMPANY_ID_QUERY = "SELECT * FROM `coupon_system`.`coupons` WHERE `category_id` = ? AND `company_id` = ?;";
	private static final String GET_ALL_COUPONS_BY_COMPANY_ID_UNDER_MAX_PRICE_QUERY = "SELECT * FROM `coupon_system`.`coupons` WHERE `company_id` = ? AND `price` <= ? ORDER BY price;";

	private static final String ADD_COUPON_PURCHASE_QUERY = "INSERT INTO `coupon_system`.`customers_vs_coupons` (`customer_id`, `coupon_id`) VALUES (?, ?);";
	private static final String DELETE_COUPON_PURCHASEE_QUERY = "DELETE FROM `coupon_system`.`customers_vs_coupons` WHERE `customer_id` = ? AND `coupon_id` = ?;";
	private static final String DELETE_COUPON_PURCHASEE_QUERY_FOR_FACADE = "DELETE FROM `coupon_system`.`customers_vs_coupons` WHERE `coupon_id` = ?;";
	private static final String GET_ALL_CUSTOMERS_VS_COUPONS_QUERY = "SELECT * FROM `coupon_system`.`customers_vs_coupons`;";
	private static final String GET_ALL_COUPONS_BY_CUSTOMER_ID_QUERY = "SELECT * from `coupon_system`.`coupons` inner join `coupon_system`.`customers_vs_coupons` ON coupons.id = `customers_vs_coupons`.coupon_id WHERE customer_id = ?;";
	private static final String GET_ALL_COUPONS_BY_CATEGORY_AND_CUSTOMER_ID_QUERY = "SELECT * from `coupon_system`.`coupons` inner join `coupon_system`.`customers_vs_coupons` ON coupons.id = `customers_vs_coupons`.coupon_id WHERE customer_id = ? AND category_id = ?;";
	private static final String GET_ALL_COUPONS_BY_CUSTOMER_ID_UNDER_MAX_PRICE_QUERY = "SELECT * from `coupon_system`.`coupons` inner join `coupon_system`.`customers_vs_coupons` on coupons.id = `customers_vs_coupons`.coupon_id WHERE customer_id = ? AND `price` <= ? ORDER BY `price`;";

	private static final String DELETE_EXPIRED_COUPONS_QUERY = "SELECT id FROM `coupon_system`.`coupons` WHERE end_date < ?;";

	@Override
	public void addCoupon(Coupon coupon) {

		try {
			connection = ConnectionPool.getInstance().getConnection();

			String sql = ADD_COUPON_QUERY;

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setInt(1, coupon.getCompanyId());
			statement.setInt(2, coupon.getCategory().ordinal() + 1);
			statement.setString(3, coupon.getTitle());
			statement.setString(4, coupon.getDescription());
			statement.setDate(5, Date.valueOf(coupon.getStartDate()));
			statement.setDate(6, Date.valueOf(coupon.getEndDate()));
			statement.setInt(7, coupon.getAmount());
			statement.setDouble(8, coupon.getPrice());
			statement.setString(9, coupon.getImage());

			statement.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
	}

	@Override
	public void updateCoupon(Coupon coupon) {

		try {
			connection = ConnectionPool.getInstance().getConnection();

			String sql = UPDATE_COUPON_QUERY;

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setInt(1, coupon.getCompanyId());
			statement.setInt(2, coupon.getCategory().ordinal() + 1);
			statement.setString(3, coupon.getTitle());
			statement.setString(4, coupon.getDescription());
			statement.setDate(5, Date.valueOf(coupon.getStartDate()));
			statement.setDate(6, Date.valueOf(coupon.getEndDate()));
			statement.setInt(7, coupon.getAmount());
			statement.setDouble(8, coupon.getPrice());
			statement.setString(9, coupon.getImage());
			statement.setInt(10, coupon.getId());

			statement.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
	}

	@Override
	public void deleteCoupon(int couponID) {

		try {
			connection = ConnectionPool.getInstance().getConnection();

			String sql = DELETE_COUPON_QUERY;

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setInt(1, couponID);

			statement.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
	}

	@Override
	public Coupon getOneCoupon(int couponID) {

		try {
			connection = ConnectionPool.getInstance().getConnection();

			String sql = GET_ONE_COUPON_QUERY;

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setInt(1, couponID);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Coupon coupon = new Coupon();
				coupon.setId(resultSet.getInt(1));
				coupon.setCompanyId(resultSet.getInt(2));
				coupon.setCategory(Category.values()[resultSet.getInt(3) - 1]);
				coupon.setTitle(resultSet.getString(4));
				coupon.setDescription(resultSet.getString(5));
				coupon.setStartDate(resultSet.getDate(6).toLocalDate());
				coupon.setEndDate(resultSet.getDate(7).toLocalDate());
				coupon.setAmount(resultSet.getInt(8));
				coupon.setPrice(resultSet.getDouble(9));
				coupon.setImage(resultSet.getString(10));
				return coupon;
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
	public List<Coupon> getAllCoupons() {

		List<Coupon> coupons = new ArrayList<>();

		try {
			connection = ConnectionPool.getInstance().getConnection();

			String sql = GET_ALL_COUPONS_QUERY;

			PreparedStatement statement = connection.prepareStatement(sql);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Coupon coupon = new Coupon();
				coupon.setId(resultSet.getInt(1));
				coupon.setCompanyId(resultSet.getInt(2));
				coupon.setCategory(Category.values()[resultSet.getInt(3) - 1]);
				coupon.setTitle(resultSet.getString(4));
				coupon.setDescription(resultSet.getString(5));
				coupon.setStartDate(resultSet.getDate(6).toLocalDate());
				coupon.setEndDate(resultSet.getDate(7).toLocalDate());
				coupon.setAmount(resultSet.getInt(8));
				coupon.setPrice(resultSet.getDouble(9));
				coupon.setImage(resultSet.getString(10));
				coupons.add(coupon);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
		return coupons;
	}

	@Override
	public boolean isCouponTitleByCompanyIdExist(String title, int companyId) {
		try {
			connection = ConnectionPool.getInstance().getConnection();

			String sql = IS_COUPON_TITLE_BY_COMPANY_ID_EXIST_QUERY;

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, title);
			statement.setInt(2, companyId);

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

	@Override
	public void deleteCouponByCouponIdAndCompanyId(int couponId, int companyId) {

		try {
			connection = ConnectionPool.getInstance().getConnection();

			String sql = DELETE_COUPON_BY_COUPON_ID_AND_COMPANY_ID_QUERY;

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setInt(1, couponId);
			statement.setInt(2, companyId);

			statement.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}

	}

	@Override
	public List<Coupon> getAllCouponsByCompanyID(int companyID) {

		List<Coupon> coupons = new ArrayList<Coupon>();

		try {
			connection = ConnectionPool.getInstance().getConnection();

			String sql = GET_ALL_COUPONS_BY_COMPANY_ID_QUERY;

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setInt(1, companyID);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Coupon coupon = new Coupon();
				coupon.setId(resultSet.getInt(1));
				coupon.setCompanyId(resultSet.getInt(2));
				coupon.setCategory(Category.values()[resultSet.getInt(3) - 1]);
				coupon.setTitle(resultSet.getString(4));
				coupon.setDescription(resultSet.getString(5));
				coupon.setStartDate(resultSet.getDate(6).toLocalDate());
				coupon.setEndDate(resultSet.getDate(7).toLocalDate());
				coupon.setAmount(resultSet.getInt(8));
				coupon.setPrice(resultSet.getDouble(9));
				coupon.setImage(resultSet.getString(10));
				coupons.add(coupon);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
		return coupons;
	}

	@Override
	public List<Coupon> getAllCouponsByCategoryAndCompanyId(Category category, int companyId) {

		List<Coupon> coupons = new ArrayList<Coupon>();

		try {
			connection = ConnectionPool.getInstance().getConnection();

			String sql = GET_ALL_COUPONS_BY_CATEGORY_AND_COMPANY_ID_QUERY;

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setInt(1, category.ordinal() + 1);
			statement.setInt(2, companyId);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Coupon coupon = new Coupon();
				coupon.setId(resultSet.getInt(1));
				coupon.setCompanyId(resultSet.getInt(2));
				coupon.setCategory(Category.values()[resultSet.getInt(3) - 1]);
				coupon.setTitle(resultSet.getString(4));
				coupon.setDescription(resultSet.getString(5));
				coupon.setStartDate(resultSet.getDate(6).toLocalDate());
				coupon.setEndDate(resultSet.getDate(7).toLocalDate());
				coupon.setAmount(resultSet.getInt(8));
				coupon.setPrice(resultSet.getDouble(9));
				coupon.setImage(resultSet.getString(10));
				coupons.add(coupon);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
		return coupons;

	}

	@Override
	public List<Coupon> getAllCouponsByCompanyIdUnderMaxPrice(int companyId, double maxPrice) {

		List<Coupon> coupons = new ArrayList<Coupon>();

		try {
			connection = ConnectionPool.getInstance().getConnection();

			String sql = GET_ALL_COUPONS_BY_COMPANY_ID_UNDER_MAX_PRICE_QUERY;

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setInt(1, companyId);
			statement.setDouble(2, maxPrice);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Coupon coupon = new Coupon();
				coupon.setId(resultSet.getInt(1));
				coupon.setCompanyId(resultSet.getInt(2));
				coupon.setCategory(Category.values()[resultSet.getInt(3) - 1]);
				coupon.setTitle(resultSet.getString(4));
				coupon.setDescription(resultSet.getString(5));
				coupon.setStartDate(resultSet.getDate(6).toLocalDate());
				coupon.setEndDate(resultSet.getDate(7).toLocalDate());
				coupon.setAmount(resultSet.getInt(8));
				coupon.setPrice(resultSet.getDouble(9));
				coupon.setImage(resultSet.getString(10));
				coupons.add(coupon);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
		return coupons;

	}

	@Override
	public void addCouponPurchase(int customerID, int couponID) {

		try {
			connection = ConnectionPool.getInstance().getConnection();

			String sql = ADD_COUPON_PURCHASE_QUERY;

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setInt(1, customerID);
			statement.setInt(2, couponID);

			statement.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
	}

	@Override
	public void deleteCouponPurchase(int customerID, int couponID) {

		try {
			connection = ConnectionPool.getInstance().getConnection();

			String sql = DELETE_COUPON_PURCHASEE_QUERY;

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setInt(1, customerID);
			statement.setInt(2, couponID);

			statement.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
	}

	@Override
	public void deleteCouponPurchaseForFacade(int couponID) {

		try {
			connection = ConnectionPool.getInstance().getConnection();

			String sql = DELETE_COUPON_PURCHASEE_QUERY_FOR_FACADE;

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setInt(1, couponID);

			statement.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
	}

	@Override
	public List<CustomersVsCoupons> getAllCustomersVsCoupons() {

		List<CustomersVsCoupons> customersVsCoupons = new ArrayList<>();

		try {
			connection = ConnectionPool.getInstance().getConnection();

			String sql = GET_ALL_CUSTOMERS_VS_COUPONS_QUERY;

			PreparedStatement statement = connection.prepareStatement(sql);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				CustomersVsCoupons custVsCoup = new CustomersVsCoupons();
				custVsCoup.setCustomerId(resultSet.getInt(1));
				custVsCoup.setCouponId(resultSet.getInt(2));
				customersVsCoupons.add(custVsCoup);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
		return customersVsCoupons;
	}

	@Override
	public List<Coupon> getAllCouponsByCustomerID(int customerID) {

		List<Coupon> coupons = new ArrayList<Coupon>();

		try {
			connection = ConnectionPool.getInstance().getConnection();

			String sql = GET_ALL_COUPONS_BY_CUSTOMER_ID_QUERY;

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setInt(1, customerID);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Coupon coupon = new Coupon();
				coupon.setId(resultSet.getInt(1));
				coupon.setCompanyId(resultSet.getInt(2));
				coupon.setCategory(Category.values()[resultSet.getInt(3) - 1]);
				coupon.setTitle(resultSet.getString(4));
				coupon.setDescription(resultSet.getString(5));
				coupon.setStartDate(resultSet.getDate(6).toLocalDate());
				coupon.setEndDate(resultSet.getDate(7).toLocalDate());
				coupon.setAmount(resultSet.getInt(8));
				coupon.setPrice(resultSet.getDouble(9));
				coupon.setImage(resultSet.getString(10));
				coupons.add(coupon);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
		return coupons;
	}

	@Override
	public List<Coupon> getAllCouponsByCategoryAndCustomerId(Category category, int CustomerId) {

		List<Coupon> coupons = new ArrayList<Coupon>();

		try {
			connection = ConnectionPool.getInstance().getConnection();

			String sql = GET_ALL_COUPONS_BY_CATEGORY_AND_CUSTOMER_ID_QUERY;

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setInt(1, category.ordinal() + 1);
			statement.setInt(2, CustomerId);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Coupon coupon = new Coupon();
				coupon.setId(resultSet.getInt(1));
				coupon.setCompanyId(resultSet.getInt(2));
				coupon.setCategory(Category.values()[resultSet.getInt(3) - 1]);
				coupon.setTitle(resultSet.getString(4));
				coupon.setDescription(resultSet.getString(5));
				coupon.setStartDate(resultSet.getDate(6).toLocalDate());
				coupon.setEndDate(resultSet.getDate(7).toLocalDate());
				coupon.setAmount(resultSet.getInt(8));
				coupon.setPrice(resultSet.getDouble(9));
				coupon.setImage(resultSet.getString(10));
				coupons.add(coupon);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
		return coupons;
	}

	@Override
	public List<Coupon> getAllCouponsByCustomerIdUnderMaxPrice(int CustomerId, double maxPrice) {

		List<Coupon> coupons = new ArrayList<Coupon>();

		try {
			connection = ConnectionPool.getInstance().getConnection();

			String sql = GET_ALL_COUPONS_BY_CUSTOMER_ID_UNDER_MAX_PRICE_QUERY;

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setInt(1, CustomerId);
			statement.setDouble(2, maxPrice);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Coupon coupon = new Coupon();
				coupon.setId(resultSet.getInt(1));
				coupon.setCompanyId(resultSet.getInt(2));
				coupon.setCategory(Category.values()[resultSet.getInt(3) - 1]);
				coupon.setTitle(resultSet.getString(4));
				coupon.setDescription(resultSet.getString(5));
				coupon.setStartDate(resultSet.getDate(6).toLocalDate());
				coupon.setEndDate(resultSet.getDate(7).toLocalDate());
				coupon.setAmount(resultSet.getInt(8));
				coupon.setPrice(resultSet.getDouble(9));
				coupon.setImage(resultSet.getString(10));
				coupons.add(coupon);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
		return coupons;
	}

	@Override
	public void deleteExpiredCouponsForDailyJob() {

		try {
			connection = ConnectionPool.getInstance().getConnection();

			String sql = DELETE_EXPIRED_COUPONS_QUERY;

			PreparedStatement statement = connection.prepareStatement(sql);
			LocalDate experationDate = LocalDate.now();
			statement.setDate(1, Date.valueOf(experationDate));
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				deleteCouponPurchaseForFacade(id);
				deleteCoupon(id);
				System.out.println("Expired coupon with couponId = " + id + " was deleted.");
			} 

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}
	}

}