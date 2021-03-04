package com.couponsystem.facade;

import java.util.ArrayList;
import java.util.List;

import com.couponsystem.beans.Category;
import com.couponsystem.beans.Company;
import com.couponsystem.beans.Coupon;
import com.couponsystem.exceptions.AlreadyExistException;
import com.couponsystem.exceptions.CouponSystemException;
import com.couponsystem.exceptions.CouponsNotFoundException;
import com.couponsystem.exceptions.NotAllowedException;

public class CompanyFacade extends ClientFacade {

	private int companyId;

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public CompanyFacade() {
		super();
	}

//	------------------------------------------------------------------------------------------------------------

	@Override
	public boolean login(String email, String password) {
		if (companiesDAO.isCompanyExists(email, password)) {
			return true;
		}
		return false;
	}

	public int getCompanyIdByEmailAndPassword(String email, String password) {
		return companiesDAO.getCompanyIdByEmailAndPasswordForLogin(email, password);
	}

//	------------------------------------------------------------------------------------------------------------

	public void addCompanyCoupon(Coupon coupon) throws CouponSystemException {

		if (couponsDAO.isCouponTitleByCompanyIdExist(coupon.getTitle(), companyId)) {
			throw new AlreadyExistException("Coupon name: ", coupon.getTitle());
		}
		couponsDAO.addCoupon(coupon);
	}

	public void updateCompanyCoupon(Coupon coupon) throws CouponSystemException {

		Coupon coup = couponsDAO.getOneCoupon(coupon.getId());

		if (coup.getId() != coupon.getId()) {
			throw new NotAllowedException("update coupon id number.");
		}

		if (coup.getCompanyId() != coupon.getCompanyId()) {
			throw new NotAllowedException("update company id number.");
		}

		couponsDAO.updateCoupon(coupon);
	}

	public void deleteCompanyCoupons(int couponId) throws CouponSystemException {

		Coupon couponFromDb = couponsDAO.getOneCoupon(couponId);

		if (couponFromDb.getCompanyId() != this.companyId) {
			throw new NotAllowedException("delete coupon - not exist, please try again.");
		}

		couponsDAO.deleteCouponByCouponIdAndCompanyId(couponId, this.companyId);
	}

	public List<Coupon> getAllCompanyCoupons() {

		return couponsDAO.getAllCouponsByCompanyID(companyId);
	}

	public List<Coupon> getCompanyCouponsByCategory(Category category) throws CouponsNotFoundException {

		List<Coupon> coupByCategory = couponsDAO.getAllCouponsByCategoryAndCompanyID(category, this.companyId);
		
		if (coupByCategory.isEmpty()) {
			throw new CouponsNotFoundException();
		}
		return coupByCategory;
	}

	public List<Coupon> getCompanyCouponsUnderMaxPrice(double maxPrice) {

		List<Coupon> coup = couponsDAO.getAllCouponsByCompanyID(companyId);
		List<Coupon> CouponsUnderMaxPrice = new ArrayList<Coupon>();

		for (Coupon c : coup) {
			if (c.getPrice() < maxPrice) {
				CouponsUnderMaxPrice.add(c);
			}
		}
		return CouponsUnderMaxPrice;
	}

	public Company getCompanyDetails() {

		Company comp = companiesDAO.getOneCompany(companyId);
		comp.setCoupons(getAllCompanyCoupons());

		return comp;
	}

}
