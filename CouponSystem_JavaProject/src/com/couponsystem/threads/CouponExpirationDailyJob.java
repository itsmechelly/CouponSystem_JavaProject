package com.couponsystem.threads;

import java.util.concurrent.TimeUnit;

import com.couponsystem.dao.CouponsDAO;
import com.couponsystem.dbdao.CouponsDBDAO;

public class CouponExpirationDailyJob implements Runnable{


	private CouponsDAO couponsDBDAO;
	private boolean quit;
	private Thread thread;

	public CouponExpirationDailyJob() {
		super();
		this.couponsDBDAO = new CouponsDBDAO();
	}

	public void setT(Thread t) {
		this.thread = t;
	}

	@Override
	public void run() {
		
		while (!quit) {
			
			System.out.println("Going to test Daily job - delete expired coupons - using DAOs");
			System.out.println("-------------------------------------------------------------");
			
			try {
				
				couponsDBDAO.deleteExpiredCouponsForDailyJob();
				
				Thread.sleep(TimeUnit.SECONDS.toMillis(22)); // for testing
				// Thread.sleep(TimeUnit.HOURS.toMillis(24)); // runtime
				
			} catch (InterruptedException e) {
				
				System.out.println("--> Daily job sleep was interrupted...");
				// e.printStackTrace();
				System.out.println();
				System.out.println();
			}
		}

		System.out.println("--> Daily job job is ended");
	}

	public void stopDaiyJob() {
		this.quit = true;
		thread.interrupt();
	}
	
}
