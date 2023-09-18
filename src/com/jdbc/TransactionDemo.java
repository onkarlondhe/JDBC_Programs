package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TransactionDemo {

	public static void main(String args[]) {
		
		transaction();
		
		
	}

	public static void transaction() {
		  
		Scanner sc = new Scanner(System.in);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loaded...");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://@localhost:3306/mzos","root","Onkar@4721");
			System.out.println("Got Connection...");
			
			System.out.println("enter from accno : ");
			int accfrom = sc.nextInt();
			
			PreparedStatement psmt = con.prepareStatement("select accbalance from account where accno=?");
			psmt.setInt(1, accfrom);
			
			ResultSet rs = psmt.executeQuery();
			
			 rs.next(); 
				int frombal = rs.getInt(1);
			
			
			System.out.println("enter amount to transfer : ");
			int amt = sc.nextInt();
			
			if(frombal-amt<5000) {
				System.out.println("Insufficient balance to perform this transition");
				
				throw new BalanceNotSufficientException("your account balance is not sufficient min amt in ur account is 5000.");
			}else {
				
				psmt = con.prepareStatement("update account set accbalance=? where accno=?");
				psmt.setInt(1, frombal-amt);
				psmt.setInt(2, accfrom);
				
				int result = psmt.executeUpdate();
				
				if(result==1) {
					System.out.println("amount successfully deducted...");
				}
				
				psmt = con.prepareStatement("select accbalance from account where accno=?");
				
				System.out.println("enter to accno: ");
				int toacc = sc.nextInt();
				psmt.setInt(1, toacc);
				rs = psmt.executeQuery();
				rs.next();
				int toBalance = rs.getInt(1);
				
				psmt = con.prepareStatement("update account set accbalance=? where accno=?");
				psmt.setInt(1, toBalance+amt);
				psmt.setInt(2, toacc);
				
				result = psmt.executeUpdate();
				 
				System.out.println("Transaction Successfully...");
				
			}
			
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
 			e.printStackTrace();
		} catch (BalanceNotSufficientException e) {
 			e.printStackTrace();
		}
		
		
	}
}
