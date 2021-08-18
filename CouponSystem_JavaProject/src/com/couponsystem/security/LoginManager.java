package com.couponsystem.security;

import com.couponsystem.exceptions.CouponSystemException;
import com.couponsystem.exceptions.LogException;
import com.couponsystem.facade.AdminFacade;
import com.couponsystem.facade.ClientFacade;
import com.couponsystem.facade.CompanyFacade;
import com.couponsystem.facade.CustomerFacade;

public class LoginManager {

	private static LoginManager instance = null;

	private LoginManager() {
	}

	public static LoginManager getInstance() {
		if (instance == null) {
			synchronized (LoginManager.class) {
				if (instance == null) {
					instance = new LoginManager();
				}
			}
		}
		return instance;
	}

	public ClientFacade login(String email, String password, ClientType clientType) throws CouponSystemException {

		switch (clientType) {

		case ADMINISTRATOR:
			AdminFacade adminFacade = new AdminFacade();
			if (adminFacade.login(email, password)) {
				System.out.println("Admin Email and Password are correct... Loading...");
				return adminFacade;
			}

		case COMPANY:
			CompanyFacade companyFacade = new CompanyFacade();
			if (companyFacade.login(email, password)) {
				int companyId = companyFacade.getCompanyIdByEmailAndPassword(email, password);
				companyFacade.setCompanyId(companyId);
				System.out.println("Company Email and Password are correct... Loading...");
				return companyFacade;
			}

		case CUSTOMER:
			CustomerFacade customerFacade = new CustomerFacade();
			if (customerFacade.login(email, password)) {
				int customerId = customerFacade.getCustomerIdByEmailAndPasswordForLogin(email, password);
				customerFacade.setCustomerId(customerId);
				System.out.println("Customer Email and Password are correct... Loading...");
				return customerFacade;
			}

		default:
			throw new LogException();
		}
	}
}
