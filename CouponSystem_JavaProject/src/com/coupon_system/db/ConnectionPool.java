package com.coupon_system.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

public class ConnectionPool {

	/**
	 * I used Stack Class that knows how to hold connection Pool (instead of Set
	 * Collection).
	 */
	private Stack<Connection> connections = new Stack<Connection>();

	/**
	 * First step of Singleton.
	 */
	private static ConnectionPool instance = null;// - new ConnectionPool();

	/**
	 * Will be the Stack length.
	 */
	private static final int connectionLength = 10;
	
	/**
	 * This is the second step of singleton. Constructor that:
	 * 1. define connectionLength = 10 connections elements inside the connection pool.
	 * 2. and then 'push' it directly to MySQL Driver.
	 */
	private ConnectionPool() {
		System.out.println("Going to create connections:");
		for (int i = 1; i <= connectionLength; i++) {
			System.out.println(" #" + i);
			try {
				Connection conn = DriverManager.getConnection(DatabaseManager.getUrl(), DatabaseManager.getUsername(),
						DatabaseManager.getPassword());
				connections.push(conn);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println();
		System.out.println();
	}

	/**
	 * This method will initialize elements of the connection pool in a lazily
	 * loaded way (also consider to be one of the singleton steps).
	 * 
	 * @return instance of the connection pool element.
	 */
	public static ConnectionPool getInstance() {
		if (instance == null) {
			synchronized (ConnectionPool.class) {
				if (instance == null) {
					instance = new ConnectionPool();
				}
			}
		}
		return instance;
	}

	/**
	 * "Give me connection": This method wait until the connection pool element is
	 * empty, and then'pop' element connection.
	 * 
	 * @return element connection.
	 * @throws InterruptedException
	 */
	public Connection getConnection() throws InterruptedException {

		synchronized (connections) {

			if (connections.isEmpty()) {
				connections.wait();
			}
			return connections.pop();
		}
	}

	/**sss
	 * When element finished working and 'push' the element outside the query, and
	 * then 'notify' that this space is empty again.
	 * 
	 * @param connection that will be pushed inside the stack.
	 */
	public void returnConnection(Connection conn) {

		synchronized (connections) {
			connections.push(conn);
			connections.notify();
		}
	}

	/**
	 * While connection is smaller then connection size (connectionLength), the connection is open.
	 * When the size turn 10 (connectionLength), the connection will be closed.
	 * 
	 * @throws InterruptedException
	 */
	public void closeAllConnections() throws InterruptedException {

		synchronized (connections) {

			while (connections.size() < connectionLength) {
				connections.wait();
			}

			for (Connection conn : connections) {
				try {
					conn.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
}