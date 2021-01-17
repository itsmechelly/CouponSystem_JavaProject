package com.coupon_system.db;

public class SchemaDatabaseManager extends DatabaseManager {

	private static final String CREATE_SCHEMA = "CREATE SCHEMA `coupon_system`;";

	public static void createSchema() {
		queryExecutive(CREATE_SCHEMA);
	}
}
