package com.couponsystem.facade;

import com.couponsystem.exceptions.LogException;

public class AdminFacade extends ClientFacade {

//	------------------------------------------------------------------------------------------------------------

	@Override
	public boolean login(String email, String password) throws LogException {
		if (email.equalsIgnoreCase("admin@admin.com") && password.equals("admin")) {
			return true;
		}
		return false;
	}

//	------------------------------------------------------------------------------------------------------------

}
