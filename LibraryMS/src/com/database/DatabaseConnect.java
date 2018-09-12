package com.database;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class DatabaseConnect {
	static Connection conn = null;
	public static void connect(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "");
		}catch(SQLException ex){
			System.out.println("Error in connection: " + ex.getMessage());
		}catch(ClassNotFoundException ex){
			System.out.println("Error in connection: " + ex.getMessage());
		}
	}
	
	//import data from book.csv into database table BOOK, AUTHOR, BOOK_AUTOR
	public static void initial(){
		String csvFile = "D:\\book.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = "	";
        connect();
        
        try {
            br = new BufferedReader(new FileReader(csvFile));
            line = br.readLine();//skip the first line
            while ((line = br.readLine()) != null ) {
                String[] book = line.split(cvsSplitBy);
                insertBook(book[0],book[2]);
                String[] authors = book[3].split(",");
                for(String aName : authors){//insert into table Author
                	aName = aName.trim();
                	if(aName != "" && aName != null){
                		insertAuthor(aName);
                		insertBookAndAuthor(book[0], getAuthorIDS(aName));
                	}
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        close();
	}
	
	//get due date by loanid
	public static ResultSet getDueDate(int loanid){
		ResultSet rs = null;
		try{
			String sql = "";
			sql = "SELECT DISTINCT * "
					+ "FROM BOOK_LOANS "
					+ "WHERE LOAN_ID=? ";
			PreparedStatement preStmt = conn.prepareStatement(sql);
			preStmt.setInt(1, loanid);
			rs = preStmt.executeQuery();
		}catch(SQLException ex){
			System.out.println("Error in connection getAuthorID: " + ex.getMessage());
		}
		return rs;
	}
	//check in by updating table loans
	public static boolean updateCheckIn(int loanid, Date datein){
		try{
			String sql = "UPDATE BOOK_LOANS "
					+ "SET DATE_IN=? "
					+ "WHERE LOAN_ID=?";
			PreparedStatement preStmt = conn.prepareStatement(sql);
			preStmt.setDate(1, datein);
			preStmt.setInt(2, loanid);
			
			preStmt.execute();
			return true;
		}catch(SQLException ex){
			System.out.println("Error in connection insertBook: " + ex.getMessage());
			return false;
		}
	}
	//make a payment by updating table Fines
	public static boolean updatePay(int cardid){
		try{
			String sql = "UPDATE FINES "
					+ "JOIN BOOK_LOANS ON FINES.LOAN_ID=BOOK_LOANS.LOAN_ID "
					+ "SET FINES.PAID=1 "
					+ "WHERE BOOK_LOANS.CARD_ID=? AND PAID=0";
			PreparedStatement preStmt = conn.prepareStatement(sql);
			preStmt.setInt(1, cardid);
			
			preStmt.execute();
			return true;
		}catch(SQLException ex){
			System.out.println("Error in connection insertBook: " + ex.getMessage());
			return false;
		}
		
	}
	
	public static ResultSet selectFineByLid(int loanid){
		ResultSet rs = null;
		try{
			String sql = "SELECT * "
					+ "FROM FINES "
					+ "WHERE LOAN_ID=?";
			PreparedStatement preStmt = conn.prepareStatement(sql);
			preStmt.setInt(1, loanid);
			
			rs = preStmt.executeQuery();
		}catch(SQLException ex){
			System.out.println("Error in connection insertBook: " + ex.getMessage());
		}
		return rs;
	}
	
	public static boolean updateAmount(int loanid, float amt){
		try{
			String sql = "UPDATE FINES "
					+ "SET LOAN_ID=?, FINE_AMOUNT=? "
					+ "WHERE BOOK_LOANS.CARD_ID=? AND PAID=FALSE";
			PreparedStatement preStmt = conn.prepareStatement(sql);
			preStmt.setInt(1, loanid);
			preStmt.setFloat(2, amt);
			
			preStmt.execute();
			return true;
		}catch(SQLException ex){
			System.out.println("Error in connection insertBook: " + ex.getMessage());
			return false;
		}
		
	}
	//insert into fine
	public static boolean addFine(int loanid, float amt){
		try{
			String sql = "INSERT INTO FINES (LOAN_ID, FINE_AMOUNT) "
					+ "VALUES (?, ?) "
					+ "ON DUPLICATE KEY UPDATE FINE_AMOUNT=?";
			PreparedStatement preStmt = conn.prepareStatement(sql);
			preStmt.setInt(1, loanid);
			preStmt.setFloat(2, amt);
			preStmt.setFloat(3, amt);
			
			preStmt.execute();
			return true;
		}catch(SQLException ex){
			System.out.println("Error in connection insertBook: " + ex.getMessage());
			return false;
		}
		
	}
	
	public static boolean addLoan(String isbn, int card_id, Date date_out, Date due_out){
		try{
			String sql = "INSERT INTO BOOK_LOANS (ISBN, CARD_ID, DATE_OUT, DUE_DATE) VALUES (?, ?, ?, ?)";
			PreparedStatement preStmt = conn.prepareStatement(sql);
			preStmt.setString(1, isbn);
			preStmt.setInt(2, card_id);
			preStmt.setDate(3, date_out);
			preStmt.setDate(4, due_out);
			preStmt.execute();
			return true;
		}catch(SQLException ex){
			System.out.println("Error in connection insertAuthor: " + ex.getMessage());
			return false;
		}
	}
	
	public static void insertBook(String isbn, String title){
		try{
			String sql = "INSERT INTO BOOK (ISBN, TITLE) VALUES (?,?)";
			PreparedStatement preStmt = conn.prepareStatement(sql);
			preStmt.setString(1, isbn);
			preStmt.setString(2, title);
			
			preStmt.execute();
		}catch(SQLException ex){
			System.out.println("Error in connection insertBook: " + ex.getMessage());
		}
		
	}
	public static boolean insertBorrower(String ssn, String name, String address, String phone){
		try{
			String sql = "INSERT INTO BORROWER (SSN, BNAME, ADDRESSS, PHONE) VALUES (?, ?, ?, ?)";
			PreparedStatement preStmt = conn.prepareStatement(sql);
			preStmt.setString(1, ssn);
			preStmt.setString(2, name);
			preStmt.setString(3, address);
			preStmt.setString(4, phone);
			
			preStmt.execute();
			return true;
		}catch(SQLException ex){
			System.out.println("Error in connection insertAuthor: " + ex.getMessage());
			return false;
		}
	}
	
	public static boolean insertAuthor(String name){
		try{
			String sql = "INSERT INTO AUTHOR (NAME) VALUES (?)";
			PreparedStatement preStmt = conn.prepareStatement(sql);
			preStmt.setString(1, name);
			
			return preStmt.execute();
		}catch(SQLException ex){
			System.out.println("Error in connection insertAuthor: " + ex.getMessage());
			return false;
		}
	}
	
	public static void insertBookAndAuthor(String ISBN, String author_ID){
		try{
			String sql = "INSERT INTO BOOK_AUTHORS (AUTHOR_ID, ISBN) VALUES (?,?)";
			PreparedStatement preStmt = conn.prepareStatement(sql);
			preStmt.setString(1, author_ID);
			preStmt.setString(2, ISBN);
			preStmt.execute();
		}catch(SQLException ex){
			System.out.println("Error in connection insertBookAndAuthor: " + ex.getMessage());
		}
		
	}
	
	public static ResultSet listFineByUser(){
		ResultSet rs = null;
		try{
			String sql = "";
			sql = "SELECT  BL.CARD_ID, SUM(FINE_AMOUNT) AS AMOUNT "
					+ "FROM FINES F, BOOK_LOANS BL "
					+ "WHERE F.LOAN_ID=BL.LOAN_ID AND F.PAID=0 "
					+ "GROUP BY BL.CARD_ID";
			PreparedStatement preStmt = conn.prepareStatement(sql);
			rs = preStmt.executeQuery();
		}catch(SQLException ex){
			System.out.println("Error in connection getAuthorID: " + ex.getMessage());
		}
		return rs;
	}
	
	
	public static ResultSet listDueLoans(Date today){
		ResultSet rs = null;
		try{
			String sql = "";
			sql = "SELECT DISTINCT * "
					+ "FROM BOOK_LOANS BL, BOOK BK, BORROWER BR "
					+ "WHERE BL.ISBN=BK.ISBN AND BL.CARD_ID=BR.CARD_ID "
					+ "AND DUE_DATE<? AND DATE_IN IS NULL";
			PreparedStatement preStmt = conn.prepareStatement(sql);
			preStmt.setDate(1, today);
			rs = preStmt.executeQuery();
		}catch(SQLException ex){
			System.out.println("Error in connection getAuthorID: " + ex.getMessage());
		}
		return rs;
	}
	
	public static ResultSet selectLoanByUname(String name){
		ResultSet rs = null;
		try{
			String sql = "";
			sql = "SELECT DISTINCT * "
					+ "FROM BOOK_LOANS BL, BOOK BK, BORROWER BR "
					+ "WHERE BL.ISBN=BK.ISBN AND BL.CARD_ID=BR.CARD_ID "
					+ "AND BR.BNAME LIKE ? AND DATE_IN IS NULL";
			PreparedStatement preStmt = conn.prepareStatement(sql);
			preStmt.setString(1, "%" + name + "%");
			rs = preStmt.executeQuery();
		}catch(SQLException ex){
			System.out.println("Error in connection getAuthorID: " + ex.getMessage());
		}
		return rs;
	}
	
	public static ResultSet selectLoanByISBN(String isbn){
		ResultSet rs = null;
		try{
			String sql = "";
			sql = "SELECT DISTINCT * "
					+ "FROM BOOK_LOANS BL, BOOK BK, BORROWER BR "
					+ "WHERE BL.ISBN=BK.ISBN AND BL.CARD_ID=BR.CARD_ID "
					+ "AND BL.ISBN=? AND DATE_IN IS NULL";
			PreparedStatement preStmt = conn.prepareStatement(sql);
			preStmt.setString(1, isbn);
			rs = preStmt.executeQuery();
		}catch(SQLException ex){
			System.out.println("Error in connection getAuthorID: " + ex.getMessage());
		}
		return rs;
	}
	
	public static ResultSet selectLoanByCardId(int cardid){
		ResultSet rs = null;
		try{
			String sql = "";
			sql = "SELECT DISTINCT * "
					+ "FROM BOOK_LOANS BL, BOOK BK, BORROWER BR "
					+ "WHERE BL.ISBN=BK.ISBN AND BL.CARD_ID=BR.CARD_ID "
					+ "AND BL.CARD_ID=? AND DATE_IN IS NULL";
			PreparedStatement preStmt = conn.prepareStatement(sql);
			preStmt.setInt(1, cardid);
			rs = preStmt.executeQuery();
		}catch(SQLException ex){
			System.out.println("Error in connection getAuthorID: " + ex.getMessage());
		}
		return rs;
	}
	
	public static ResultSet selectBorrowerByID(int id){
		ResultSet rs = null;
		try{
			String sql = "";
			sql = "SELECT CARD_ID, SSN, BNAME, ADDRESSS, PHONE "
					+ "FROM BORROWER "
					+ "WHERE CARD_ID=?";
			PreparedStatement preStmt = conn.prepareStatement(sql);
			preStmt.setInt(1, id);
			rs = preStmt.executeQuery();
		}catch(SQLException ex){
			System.out.println("Error in connection getAuthorID: " + ex.getMessage());
		}
		return rs;
	}
	
	public static ResultSet selectBorrowerByName(String name){
		ResultSet rs = null;
		try{
			String sql = "";
			sql = "SELECT CARD_ID, SSN, BNAME, ADDRESSS, PHONE "
					+ "FROM BORROWER "
					+ "WHERE BNAME LIKE ?";
			PreparedStatement preStmt = conn.prepareStatement(sql);
			preStmt.setString(1, "%" + name + "%");
			rs = preStmt.executeQuery();
		}catch(SQLException ex){
			System.out.println("Error in connection getAuthorID: " + ex.getMessage());
		}
		return rs;
	}
	
	public static ResultSet getAuthorID(String name){
		ResultSet rs = null;
		try{
			String sql = "";
			sql = "SELECT AUTHOR_ID "
					+ "FROM AUTHOR "
					+ "WHERE NAME=?";
			PreparedStatement preStmt = conn.prepareStatement(sql);
			preStmt.setString(1, name);
			rs = preStmt.executeQuery();
		}catch(SQLException ex){
			System.out.println("Error in connection getAuthorID: " + ex.getMessage());
		}
		return rs;
	}
	public static String getAuthorIDS(String name){
		String id;
		ResultSet rset = getAuthorID(name);
		try{
			while (rset.next()){
				id = rset.getString("AUTHOR_ID");
				return id;
			}
		}catch(SQLException ex) {
			System.out.println("Error in connection getAuthorIDS: " + ex.getMessage());
		}
		return null;
	}
	
	public static ResultSet searchAhutorName(String isbn){
		ResultSet rs = null;
		try{
			String sql = "";
			sql = "SELECT BA.AUTHOR_ID, NAME "
					+ "FROM BOOK_AUTHORS BA, AUTHOR A "
					+ "WHERE BA.AUTHOR_ID=A.AUTHOR_ID "
					+ "AND BA.ISBN=?";
			PreparedStatement preStmt = conn.prepareStatement(sql);
			preStmt.setString(1, isbn);
			rs = preStmt.executeQuery();
		}catch(SQLException ex){
			System.out.println("Error in connection: " + ex.getMessage());
		}
		return rs;
	}
	
	public static ResultSet selectBooks(String keyword){
		ResultSet rs = null;
		try{
			String sql = "";
			sql = "SELECT DISTINCT B.ISBN, B.TITLE "
					+ "FROM BOOK B, BOOK_AUTHORS BA, AUTHOR A "
					+ "WHERE B.ISBN=BA.ISBN AND BA.AUTHOR_ID=A.AUTHOR_ID "
					+ "AND (B.TITLE LIKE ? OR A.NAME LIKE ?)";
			PreparedStatement preStmt = conn.prepareStatement(sql);
			preStmt.setString(1, "%" + keyword + "%");
			preStmt.setString(2, "%" + keyword + "%");
			rs = preStmt.executeQuery();
		}catch(SQLException ex){
			System.out.println("Error in connection: " + ex.getMessage());
		}
		return rs;
	}
	
	public static void close(){
		try{
			conn.close();
		}catch(SQLException ex){
			System.out.println("Error in connection: " + ex.getMessage());
		}
	}
}
