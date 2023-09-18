package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
 
public class PreparedStatementDemo {

	public static void main(String[] args) {
		 
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://@localhost:3306/mzos","root","Onkar@4721");
		
			PreparedStatement psmt = con.prepareStatement("update student set marks=? where id=?");
			Scanner sc = new Scanner(System.in);
			System.out.print("enter student marks and student id : ");
			int marks = sc.nextInt();
			int id= sc.nextInt();
			
			psmt.setInt(1,marks);
			psmt.setInt(2,id);
			int update = psmt.executeUpdate();
			System.out.println(update);
			
			psmt = con.prepareStatement("select * from student where id=?");
			System.out.print("enter student id : ");
			psmt.setInt(1,sc.nextInt());
			
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				System.out.print(rs.getInt("id")+" ");
				System.out.print(rs.getString("name")+" ");
				System.out.print(rs.getInt("marks")+" ");	
			}
			
 		} catch (ClassNotFoundException e) {
 			e.printStackTrace();
			
		} catch (SQLException e) {
 			e.printStackTrace();
		}

	}

}
