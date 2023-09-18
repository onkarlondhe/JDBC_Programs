package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class BankTransaction {

	public static void main(String[] args) {
 
 		boi();
	}

	private static void boi() {
		Scanner sc = new Scanner(System.in);

		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loaded...");
			
			Connection con1 = DriverManager.getConnection("jdbc:mysql://@localhost:3306/sbibank","root","Onkar@4721");
			System.out.println("Got Connection...");
			
			Connection con2 = DriverManager.getConnection("jdbc:mysql://@localhost:3306/boibank","root","Onkar@4721");
			System.out.println("Got Connection...");
			
			con1.setAutoCommit(false);
			con2.setAutoCommit(false);

			
			System.out.println("enter from accno : ");
			int accfrom = sc.nextInt();
			
			PreparedStatement psmt1 = con1.prepareStatement("select accbalance from sbi where accno=?");
			psmt1.setInt(1, accfrom);
			
			ResultSet rs1 = psmt1.executeQuery();
			
			 rs1.next(); 
				int frombal = rs1.getInt(1);
			
			
			System.out.println("enter amount to transfer : ");
			int amt = sc.nextInt();
			
			if(frombal-amt<5000) {
				System.out.println("Insufficient balance to perform this transition");
				
				throw new BalanceNotSufficientException("your account balance is not sufficient min amt in ur account is 5000.");
			}else {
				
				psmt1 = con1.prepareStatement("update sbi set accbalance=? where accno=?");
				psmt1.setInt(1, frombal-amt);
				psmt1.setInt(2, accfrom);
				
				int result = psmt1.executeUpdate();
				
				if(result==1) {
					System.out.println("amount successfully deducted...");
				};
			}
			
			
			PreparedStatement psmt2 = con2.prepareStatement("select accbalance from boi where accno=?");
			
			System.out.println("enter to accno: ");
			int toacc = sc.nextInt();
			psmt2.setInt(1, toacc);
			ResultSet rs2 = psmt2.executeQuery();
			rs2.next();
			int toBalance = rs2.getInt(1);
			
			psmt2 = con2.prepareStatement("update boi set accbalance=? where accno=?");
			psmt2.setInt(1, toBalance+amt);
			psmt2.setInt(2, toacc);
			
			int result = psmt2.executeUpdate();
			 
			System.out.println("Transaction Successfully...");
			
				con1.commit();
				con2.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
	
	

	 
		 
