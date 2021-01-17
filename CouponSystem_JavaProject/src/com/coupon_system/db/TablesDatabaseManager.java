package com.coupon_system.db;

public class TablesDatabaseManager extends DatabaseManager {

	private static final String CREATE_TABLE_COMPANIES = "CREATE TABLE `coupon_system`.`companies` (\r\n"
			+ "  `id` INT NOT NULL AUTO_INCREMENT,\r\n" + "  `name` VARCHAR(45) NOT NULL,\r\n"
			+ "  `email` VARCHAR(45) NOT NULL,\r\n" + "  `password` VARCHAR(45) NOT NULL,\r\n"
			+ "  PRIMARY KEY (`id`));";
	private static final String CREATE_TABLE_CUSTOMERS = "CREATE TABLE `coupon_system`.`customers` (\r\n"
			+ "  `id` INT NOT NULL AUTO_INCREMENT,\r\n" + "  `first_name` VARCHAR(45) NOT NULL,\r\n"
			+ "  `last_name` VARCHAR(45) NOT NULL,\r\n" + "  `email` VARCHAR(45) NOT NULL,\r\n"
			+ "  `password` VARCHAR(45) NOT NULL,\r\n" + "  PRIMARY KEY (`id`));";
	private static final String CREATE_TABLE_CATEGORIES = "CREATE TABLE `coupon_system`.`categories` (\r\n"
			+ "  `id` INT NOT NULL AUTO_INCREMENT,\r\n" + "  `name` VARCHAR(45) NOT NULL,\r\n"
			+ "  PRIMARY KEY (`id`));";
	private static final String CREATE_TABLE_COUPONS = "CREATE TABLE `coupon_system`.`coupons` (\r\n"
			+ "  `id` INT NOT NULL AUTO_INCREMENT,\r\n" + "  `company_id` INT NOT NULL,\r\n"
			+ "  `category_id` INT NOT NULL,\r\n" + "  `title` VARCHAR(45) NOT NULL,\r\n"
			+ "  `description` VARCHAR(45) NOT NULL,\r\n" + "  `start_date` DATE NOT NULL,\r\n"
			+ "  `end_date` DATE NOT NULL,\r\n" + "  `amount` INT NOT NULL,\r\n" + "  `price` DOUBLE NOT NULL,\r\n"
			+ "  `image` VARCHAR(45) NOT NULL,\r\n" + "  PRIMARY KEY (`id`),\r\n"
			+ "  INDEX `company_id_idx` (`company_id` ASC) VISIBLE,\r\n"
			+ "  INDEX `category_id_idx` (`category_id` ASC) VISIBLE,\r\n" + "  CONSTRAINT `company_id`\r\n"
			+ "    FOREIGN KEY (`company_id`)\r\n" + "    REFERENCES `coupon_system`.`companies` (`id`)\r\n"
			+ "    ON DELETE NO ACTION\r\n" + "    ON UPDATE NO ACTION,\r\n" + "  CONSTRAINT `category_id`\r\n"
			+ "    FOREIGN KEY (`category_id`)\r\n" + "    REFERENCES `coupon_system`.`categories` (`id`)\r\n"
			+ "    ON DELETE NO ACTION\r\n" + "    ON UPDATE NO ACTION);";
	private static final String CREATE_TABLE_CUSTOMERS_VS_COUPONS = "CREATE TABLE `coupon_system`.`customers_vs_coupons` (\r\n"
			+ "  `customer_id` INT NOT NULL,\r\n" + "  `coupon_id` INT NOT NULL,\r\n"
			+ "  PRIMARY KEY (`customer_id`, `coupon_id`),\r\n"
			+ "  INDEX `coupon_id_idx` (`coupon_id` ASC) VISIBLE,\r\n" + "  CONSTRAINT `customer_id`\r\n"
			+ "    FOREIGN KEY (`customer_id`)\r\n" + "    REFERENCES `coupon_system`.`customers` (`id`)\r\n"
			+ "    ON DELETE NO ACTION\r\n" + "    ON UPDATE NO ACTION,\r\n" + "  CONSTRAINT `coupon_id`\r\n"
			+ "    FOREIGN KEY (`coupon_id`)\r\n" + "    REFERENCES `coupon_system`.`coupons` (`id`)\r\n"
			+ "    ON DELETE NO ACTION\r\n" + "    ON UPDATE NO ACTION);";

	private static final String DROP_TABLE_COMPANIES = "DROP TABLE `coupon_system`.`companies`;";
	private static final String DROP_TABLE_CUSTOMERS = "DROP TABLE `coupon_system`.`customers`;";
	private static final String DROP_TABLE_CATEGORIES = "DROP TABLE `coupon_system`.`categories`;";
	private static final String DROP_TABLE_COUPONS = "DROP TABLE `coupon_system`.`coupons`;";
	private static final String DROP_TABLE_CUSTOMERS_vs_COUPONS = "DROP TABLE `coupon_system`.`customers_vs_coupons`;";

	public static void createAllTables() {
		queryExecutive(CREATE_TABLE_COMPANIES);
		queryExecutive(CREATE_TABLE_CUSTOMERS);
		queryExecutive(CREATE_TABLE_CATEGORIES);
		queryExecutive(CREATE_TABLE_COUPONS);
		queryExecutive(CREATE_TABLE_CUSTOMERS_VS_COUPONS);
	}

	public static void dropAllTables() {
		queryExecutive(DROP_TABLE_CUSTOMERS_vs_COUPONS);
		queryExecutive(DROP_TABLE_COUPONS);
		queryExecutive(DROP_TABLE_CATEGORIES);
		queryExecutive(DROP_TABLE_CUSTOMERS);
		queryExecutive(DROP_TABLE_COMPANIES);
	}

	/**
	 * This method was created only for the tests, not relevant for production.
	 */
	public static void dropAndCreateDatabase() {
		dropAllTables();
		createAllTables();
	}
}
