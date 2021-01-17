package com.coupon_system.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Stack;

public class ConnectionPool {
	
	/**
	 * I used Stack Class that knows how to hold connection Pool (instead of Set Collection).
	 */
	private Stack<Connection> connections = new Stack<Connection>();
	
	/**
	 * First step of Singleton.
	 */
	private static ConnectionPool instance = null;// - new ConnectionPool();

	/**
	 * Constructor that:
	 * 1. define 10 connections elements inside the connection pool.
	 * 2. and then 'push' it directly to MySQL Driver.
	 */
//	private ConnectionPool() {
//		System.out.println("Going to create connections:");
//		for (int i = 1; i <= 10; i++) {
//			System.out.println(" #" + i);
//			try {
//				Connection conn = DriverManager.getConnection();
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//		}
//	}
}