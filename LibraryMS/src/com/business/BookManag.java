package com.business;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.database.DatabaseConnect;

public class BookManag {
	public boolean isAvailable(String isbn){
		//DatabaseConnect.connect();
		ResultSet rset = DatabaseConnect.selectLoanByISBN(isbn);
		try{
			if(rset.next()){
				return false;
			}
		}catch(SQLException ex) {
			System.out.println("Error in connection: " + ex.getMessage());
		}
		//DatabaseConnect.close();
		return true;
	}
	public ArrayList<Book> searchBooks(String keyword){
		ArrayList<Book> result = new ArrayList<>();
		//DatabaseConnect.connect();
		ResultSet rset = DatabaseConnect.selectBooks(keyword);
		try{
			while (rset.next()){
				String isbn = rset.getString("ISBN");
				String title = rset.getString("TITLE");
				String author = "";
				
				ResultSet aset = DatabaseConnect.searchAhutorName(isbn);
				while(aset.next()){
					String name = aset.getString("NAME");
					if(author.length() > 0){
						author += ",";
					}
					author += name;
				}
				
				Book b = new Book(isbn,title,author,isAvailable(isbn));
				result.add(b);
			}
			
		}catch(SQLException ex) {
			System.out.println("Error in connection: " + ex.getMessage());
		}
		//DatabaseConnect.close();
		return result;
	}

}
