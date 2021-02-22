package com.couponsystem.test;

import com.couponsystem.db.ConnectionPool;
import com.couponsystem.db.TablesDatabaseManager;
import com.couponsystem.utils.TestUtils;

public class MainTest {

	public static void main(String[] args) throws ClassNotFoundException, InterruptedException {
		
//		------------------------------------------------------------------------------------------------------------

		TestUtils.welcome();
		TestUtils.loadingConnections();

//		------------------------------------------------------------------------------------------------------------

//		STEP N.1 - Loading JDBC MySQL Driver + Setup Connection Pool:
		Class.forName("com.mysql.cj.jdbc.Driver");
		
//		STEP N.2 - Drop & Create All Tables:
//		SchemaDatabaseManager.createSchema();
//		TablesDatabaseManager.createAllTables();
		TablesDatabaseManager.dropAndCreateDatabase();
		
//		------------------------------------------------------------------------------------------------------------

//		STEP N.3 - Start Daily Thread;
//		...   ...   ...
		
//		------------------------------------------------------------------------------------------------------------

//		STEP N.4 - The Real Thing:
		AdminTest.adminTest();
//		CompanyTest.companyTest();
//		CustomerTest.customerTest();
//		...   ...   ... need to testAll(); over here.
		
//		------------------------------------------------------------------------------------------------------------

//		STEP N.5 - Stop Daily Thread;
//		...   ...   ...

//		------------------------------------------------------------------------------------------------------------

//		STEP N.6 - Drop All Tables: :
//		TablesDatabaseManager.dropAllTables();
		
//		------------------------------------------------------------------------------------------------------------

//		STEP N.7 - Closing Connection Pool:
		ConnectionPool.getInstance().closeAllConnections();
		
//		------------------------------------------------------------------------------------------------------------

//		TestUtils.end();

//		------------------------------------------------------------------------------------------------------------

	}
}
