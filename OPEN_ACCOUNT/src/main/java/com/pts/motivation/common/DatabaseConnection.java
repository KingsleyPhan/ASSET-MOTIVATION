package com.pts.motivation.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

		public Connection getConnection() {			
			try {
				String DB_URL = "jdbc:sqlserver://113.161.80.144\\SQLEXPRESS:14337; databaseName=ASSET_MANAGEMENT;";
				//  String DB_URL = "jdbc:sqlserver://localhost\\SQLEXPRESS:"+"1433"+";databaseName=AMS_30_04";

				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
				Connection conn = DriverManager.getConnection(DB_URL, "ASSET", "ams@2020");
				if (conn != null) {
				    System.out.println("Database connected successfully!!!!!");
				    return conn;
				} else {
					  System.out.println("Database connected faild!!!!!");
				}
			} catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
}
