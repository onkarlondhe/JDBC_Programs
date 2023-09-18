package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementDemo {

	public static void main(String[] args) {
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loaded...");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://@localhost:3306/mzos","root","Onkar@4721");
			System.out.println("Got Connection...");
			
			Statement st = con.createStatement();
			
			int count = st.executeUpdate("insert into student values(7,'Yashodhan',95)");
 
			System.out.println(count);
			
			ResultSet rs = st.executeQuery("select * from student");
//			
//			while(rs.next()) {
//				System.out.print(rs.getInt(1)+" ");
//				System.out.print(rs.getString(2)+" ");
//				System.out.println(rs.getInt(3)+" ");
//
//			}
			
//			ResultSetMetaData rsmd=rs.getMetaData();
//			
//			int countCol=rsmd.getColumnCount();
//			
//			for(int i=1;i<=countCol;i++)
//			{
//				System.out.println("Col Label : "+rsmd.getColumnLabel(i));
//				System.out.println("Col Class Name : "+rsmd.getColumnClassName(i));
//				System.out.println("Col Display Size : "+rsmd.getColumnDisplaySize(i));
//				System.out.println("Col Column Count : "+rsmd.getColumnCount());
//				System.out.println("Col Catalog Name : "+rsmd.getCatalogName(i));
//				System.out.println("Col Name : "+rsmd.getColumnName(i));
//				System.out.println("Col Type : "+rsmd.getColumnType(i));
//				System.out.println("Col Type Name : "+rsmd.getColumnTypeName(i));
//				System.out.println("Col Precision : "+rsmd.getPrecision(i));
//				System.out.println("Col Scale : "+rsmd.getScale(i));
//				System.out.println("Table Name : "+rsmd.getTableName(i));
//				System.out.println("***********************************************");
//			}

 		} catch (ClassNotFoundException e) {
 			e.printStackTrace();
			
		} catch (SQLException e) {
 			e.printStackTrace();
		}
	
	}

}
