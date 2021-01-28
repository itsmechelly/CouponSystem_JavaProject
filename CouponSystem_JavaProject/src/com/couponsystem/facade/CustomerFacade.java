package com.couponsystem.facade;

import com.couponsystem.exceptions.LogException;

public class CustomerFacade extends ClientFacade {

	private int customerId;

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public CustomerFacade() {
		super();
	}

//	------------------------------------------------------------------------------------------------------------

	@Override
	public boolean login(String email, String password) throws LogException {
		if (customersDAO.isCustomerExist(email, password)) {
			return true;
		}
		return false;
	}

	public int getCustomerIdByEmailAndPasswordForLogin(String email, String password) {
		return customersDAO.getCustomerIdByEmailAndPasswordForLogin(email, password);
	}

//	------------------------------------------------------------------------------------------------------------

}