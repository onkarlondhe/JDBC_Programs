package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcDemo {

	public static void main(String[] args) {
		 
		try { 
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("driver Loaded...");
			String hostname="localhost";
			String port="3306";
			String dbName="mzos";
			String userName="root";
			String password="Onkar@4721";
			String jdbcUrl = "jdbc:mysql://" + hostname + ":" +
				    port + "/" + dbName + "?user=" + userName + "&password=" + password;
			Connection con=DriverManager.getConnection(jdbcUrl);
			System.out.println("Got Connection...");
		}catch(Exception e) {
			e.printStackTrace();
}
		
	}

}
