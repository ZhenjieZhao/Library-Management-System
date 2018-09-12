package com.business;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.database.DatabaseConnect;

public class LoanManag {
	
	public boolean isReturnByUID(int cardid){
		ResultSet rset = DatabaseConnect.selectLoanByCardId(cardid);
		try{
			while (rset.next()){
				return false;
			}
		}catch(SQLException ex) {
			System.out.println("Error in connection: " + ex.getMessage());
		}
		return true;
	}
	
	
	public boolean updateFine(int loanid){
		java.sql.Date today = new java.sql.Date( new java.util.Date().getTime() );
		java.sql.Date due = getDue(loanid);
		boolean resl = false;
		if((today.getTime() - due.getTime()) > 0){
			float amt = (float) (0.25 * ((today.getTime() - due.getTime()) / (24*60*60*1000) + 1));
			resl &= DatabaseConnect.addFine(loanid, amt);
		}
		return resl;
	}
	
	public boolean checkIn(int loanid){
		java.sql.Date today = new java.sql.Date( new java.util.Date().getTime() );
		java.sql.Date due = getDue(loanid);
		boolean resl = false;
		resl = DatabaseConnect.updateCheckIn(loanid, today);
		if((today.getTime() - due.getTime()) > 0){
			float amt = (float) (0.25 * ((today.getTime() - due.getTime()) / (24*60*60*1000) + 1));
			resl &= DatabaseConnect.addFine(loanid, amt);
		}
		return resl;
	}
	
	public int getLoanId(String isbn){
		ResultSet rset = DatabaseConnect.selectLoanByISBN(isbn);
		int d = 0;
		try{
			while (rset.next()){
				d = rset.getInt("LOAN_ID");
			}
			
		}catch(SQLException ex) {
			System.out.println("Error in connection: " + ex.getMessage());
		}
		return d;
	}
	
	public Date getDue(int loanid){
		ResultSet rset = DatabaseConnect.getDueDate(loanid);
		Date d = null;
		try{
			while (rset.next()){
				d = rset.getDate("DUE_DATE");
			}
			
		}catch(SQLException ex) {
			System.out.println("Error in connection: " + ex.getMessage());
		}
		return d;
	}
	
	public boolean addLoan(String isbn, int carid){
		java.sql.Date today = new java.sql.Date( new java.util.Date().getTime() );
		java.sql.Date duedate= new java.sql.Date( today.getTime() + 14*24*60*60*1000);
		//DatabaseConnect.connect();
		boolean b = DatabaseConnect.addLoan(isbn, carid, today, duedate);
		//DatabaseConnect.close();
		return b;
	}
	
	public int numOfLoanById(int cardid){
		int sum = 0;
		ResultSet rset = DatabaseConnect.selectLoanByCardId(cardid);
		try{
			while (rset.next()){
				sum++;
			}
			
		}catch(SQLException ex) {
			System.out.println("Error in connection: " + ex.getMessage());
		}
		return sum;
	}
	
	public ArrayList<Loan> listDueLoans(){
		java.sql.Date today = new java.sql.Date( new java.util.Date().getTime() );
		ArrayList<Loan> result = new ArrayList<>();
		ResultSet rset;
		rset = DatabaseConnect.listDueLoans(today);
		
		try{
			while (rset.next()){
				int LOANID = rset.getInt("LOAN_ID");
				int CARDID = rset.getInt("CARD_ID");
				String ISBN = rset.getString("ISBN");
				String TITLE = rset.getString("TITLE");
				String NAME = rset.getString("BNAME");
				Date DATEOUT = rset.getDate("DATE_OUT");
				Date DUEDATE = rset.getDate("DUE_DATE");
								
				Loan b = new Loan(LOANID, ISBN,TITLE, NAME, CARDID, DATEOUT, DUEDATE);
				result.add(b);
			}
			
		}catch(SQLException ex) {
			System.out.println("Error in connection: " + ex.getMessage());
		}
		//DatabaseConnect.close();
		return result;
	}

	
	
	public ArrayList<Loan> searchLoan(String isbn, String cardid, String name){
		ArrayList<Loan> result = new ArrayList<>();
		//DatabaseConnect.connect();
		ResultSet rset;
		if(!(isbn == null || isbn.equals(""))){
			rset = DatabaseConnect.selectLoanByISBN(isbn);
		}else if(!(cardid == null || cardid.equals(""))){
			rset = DatabaseConnect.selectLoanByCardId(Integer.parseInt(cardid));
		}else{
			rset = DatabaseConnect.selectLoanByUname(name);
		}
		
		try{
			while (rset.next()){
				int CARDID = rset.getInt("CARD_ID");
				String ISBN = rset.getString("ISBN");
				String TITLE = rset.getString("TITLE");
				String NAME = rset.getString("BNAME");
				Date DATEOUT = rset.getDate("DATE_OUT");
				Date DUEDATE = rset.getDate("DUE_DATE");
								
				Loan b = new Loan(ISBN,TITLE, NAME, CARDID, DATEOUT, DUEDATE);
				result.add(b);
			}
			
		}catch(SQLException ex) {
			System.out.println("Error in connection: " + ex.getMessage());
		}
		//DatabaseConnect.close();
		return result;
	}

}
