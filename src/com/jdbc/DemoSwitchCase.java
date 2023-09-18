package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DemoSwitchCase {

	public static void main(String[] args) {
		
		String wish;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mzos","root","Onkar@4721");
			
			Statement smt = con.createStatement();
			
			do {
				System.out.println("Press 1 : insert new record");
				System.out.println("Press 2 : show all records");
				System.out.println("Press 3 : change salary");
				
				PreparedStatement psmt = con.prepareStatement("insert into employee values(?,?,?)");
				
				 
				Scanner sc = new Scanner(System.in);
				System.out.print("enter your choice : ");
				int choice = sc.nextInt();
				
				switch (choice) {
				case 1: {
					
					System.out.println("enter employee id : ");
					int id = sc.nextInt();
					
					System.out.println("enter employee name : ");
					String name = sc.next();
					
 					System.out.println("enter employee salary : ");
					double salary = sc.nextDouble();
					
					psmt.setInt(1, id);
					psmt.setString(2, name);
					psmt.setDouble(3, salary);
					
					psmt.executeUpdate(); 
					
					break;
					
				}
				
				case 2 : {
					ResultSet rs = smt.executeQuery("select * from employee");
					while(rs.next()) {
						System.out.print(rs.getInt(1)+" ");
						System.out.print(rs.getString(2)+" ");
						System.out.println(rs.getDouble(3)+" ");
					}
					
					break;
				}
				
				case 3 : {

					System.out.print("enter employee salary : ");
					double salary = sc.nextDouble();
					
					System.out.print("enter employee id : ");
					int id = sc.nextInt();
					
					psmt = con.prepareStatement("update employee set salary=? where id=?");
					psmt.setDouble(1, salary);
					psmt.setInt(2, id);
					
					System.out.println(psmt.executeUpdate());
					
					break;
				}
				default:
					System.out.println("Invalid Choice");
 				}
				
				System.out.println("do you want to perform more operations");
				 wish=sc.next();
				
				}while(wish.toLowerCase().equals("yes"));
			
			
		} catch (ClassNotFoundException e) {
 			e.printStackTrace();
 			
		} catch (SQLException e) {
 			e.printStackTrace();
		}
		

	}

}
