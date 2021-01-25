package com.couponsystem.dbdao;

import java.sql.Connection;
import java.util.List;

import com.couponsystem.beans.Coupon;
import com.couponsystem.beans.CustomersVsCoupons;
import com.couponsystem.dao.CouponsDAO;

public class CouponsDBDAO implements CouponsDAO {

	private Connection connection;

	private static final String ADD_COUPON_QUERY = "INSERT INTO `coupon_system`.`coupons` (`company_id`, `category_id`, `title`, `description`, `start_date`, `end_date`, `amount`, `price`, `image`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String UPDATE_COUPON_QUERY = "UPDATE `coupon_system`.`coupons` SET `company_id` = ?, `category_id` = ?, `title` = ?, `description` = ?, `start_date` = ?, `end_date` = ?, `amount` = ?, `price` = ?, `image` = ? WHERE (`id` = ?);";
	private static final String DELETE_COUPON_QUERY = "DELETE FROM `coupon_system`.`coupons` WHERE (`id` = ?);";
	private static final String GET_ONE_COUPON_QUERY = "SELECT * FROM `coupon_system`.`coupons` WHERE (`id` = ?);";
	private static final String GET_ALL_COUPONS_QUERY = "SELECT * FROM `coupon_system`.`coupons`;";
	private static final String IS_COUPON_EXISTS_QUERY = "SELECT * FROM `coupon_system`.`coupons` WHERE (`id` = ?);";
	private static final String GET_ALL_CUSTOMERS_VS_COUPONS_QUERY = "SELECT * FROM `coupon_system`.`customers_vs_coupons`;";
	private static final String GET_ALL_COUPONS_BY_COMPANY_ID_QUERY = "SELECT * FROM `coupon_system`.`coupons` WHERE (`company_id` = ?);";
	private static final String GET_ALL_COUPONS_BY_CUSTOMER_ID_QUERY = "SELECT `coupon_id` FROM `coupon_system`.`customers_vs_coupons` WHERE (`customer_id` = ?);";
	private static final String ADD_COUPON_PURCHASE_QUERY = "INSERT INTO `coupon_system`.`customers_vs_coupons` (`customer_id`, `coupon_id`) VALUES (?, ?);";
	private static final String DELETE_COUPON_PURCHASEE_QUERY = "DELETE FROM `coupon_system`.`customers_vs_coupons` WHERE `customer_id` = ? AND `coupon_id` = ?;";
	private static final String DELETE_COUPON_PURCHASEE_QUERY_FOR_FACADE = "DELETE FROM `coupon_system`.`customers_vs_coupons` WHERE `coupon_id` = ?;";

	@Override
	public void addCoupon(Coupon coupon) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCoupon(Coupon coupon) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCoupon(int couponID) {
		// TODO Auto-generated method stub

	}

	@Override
	public Coupon getOneCoupon(int couponID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Coupon> getAllCoupons() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addCouponPurchase(int customerID, int couponID) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCouponPurchase(int customerID, int couponID) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isCouponExists(int couponID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<CustomersVsCoupons> getAllCustomersVsCoupons() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Coupon> getAllCouponsByCompanyID(int companyID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Coupon> getAllCouponsByCustomerID(int customerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCouponPurchaseForFacade(int couponID) {
		// TODO Auto-generated method stub

	}

}
