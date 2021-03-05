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

	public List<Coupon> getAllCompanyCoupons() throws CouponSystemException {

		List<Coupon> allCompCoup = new ArrayList<Coupon>();
		allCompCoup = couponsDAO.getAllCouponsByCompanyID(companyId);
		
		if (allCompCoup.isEmpty()) {
			throw new CouponsNotFoundException();
		}
		return allCompCoup;
	}

	public List<Coupon> getCompanyCouponsByCategory(Category category) throws CouponSystemException {

		List<Coupon> coupByCategory = new ArrayList<Coupon>();
		coupByCategory = couponsDAO.getAllCouponsByCategoryAndCompanyId(category, this.companyId);
		
		if (coupByCategory.isEmpty()) {
			throw new CouponsNotFoundException();
		}
		return coupByCategory;
	}

	public List<Coupon> getCompanyCouponsUnderMaxPrice(double maxPrice) throws CouponSystemException {
		
		List<Coupon> coupUnderMax = new ArrayList<Coupon>();
		coupUnderMax = couponsDAO.getAllCouponsByCompanyIdUnderMaxPrice(this.companyId, maxPrice);
		
		if (coupUnderMax.isEmpty()) {
			throw new CouponsNotFoundException();
		}
		return coupUnderMax;
	}

	public Company getCompanyDetails() throws CouponSystemException {

		Company comp = companiesDAO.getOneCompany(companyId);
		comp.setCoupons(getAllCompanyCoupons());
		return comp;
	}

}
