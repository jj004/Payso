package com.PaysoMOIMPS.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/eMpower", "eMpowerTest", "eMpowerTest@123");
			if (con != null) {
				System.out.println("DB Connection Success");
				return con;
			} else {
				System.out.println("DB Connection Failed");
				return null;
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Exception");
			e.printStackTrace();
		}
		return null;

	}
}