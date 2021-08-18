package com.couponsystem.test;

import com.couponsystem.db.ConnectionPool;
import com.couponsystem.db.TablesDatabaseManager;
import com.couponsystem.threads.CouponExpirationDailyJob;
import com.couponsystem.utils.TestUtils;

public class MainTest {

	public static void main(String[] args) throws ClassNotFoundException, InterruptedException {

		Thread dailyJob = null;

		try {

//			STEP N.0:
//			---------
			TestUtils.welcome();
			TestUtils.loadingConnections();

			
//			STEP N.1 - Loading JDBC MySQL Driver + Setup Connection Pool:
//			-------------------------------------------------------------
			Class.forName("com.mysql.cj.jdbc.Driver");

			
//			STEP N.2 - Drop & Create All Tables:
//			------------------------------------
//			SchemaDatabaseManager.createSchema(); //runtime
//			TablesDatabaseManager.createAllTables(); //runtime
			TablesDatabaseManager.dropAndCreateDatabase(); // for testing

			
//			STEP N.3 - The Real Thing:		
//			--------------------------
			AdminTest.adminTest();
			CompanyTest.companyTest();
			CustomerTest.customerTest();
			
			
//			STEP N.4 - Start Daily Thread:
//			------------------------------
			TestUtils.dailyJobManifulation();
			dailyJob = new Thread(new CouponExpirationDailyJob());
			dailyJob.start();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {

			try {

//				STEP N.5 - Stop Daily Thread:
//				-----------------------------
				dailyJob.interrupt();
				dailyJob.join();

//				STEP N.6 - Drop All Tables:
//				---------------------------
				TablesDatabaseManager.dropAllTables();

//				STEP N.7 - Closing Connection Pool:
//				-----------------------------------
				ConnectionPool.getInstance().closeAllConnections();
				TestUtils.end();

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
