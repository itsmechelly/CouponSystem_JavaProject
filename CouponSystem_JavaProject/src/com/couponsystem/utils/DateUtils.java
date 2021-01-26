package com.couponsystem.utils;

import java.sql.Date;

public class DateUtils {

	public static Date convertJavaUtilDateToSqlUtilDate(java.util.Date date) {
		return new java.sql.Date(date.getYear() - 1900, date.getMonth() - 1, date.getDate() + 1);
	}
}