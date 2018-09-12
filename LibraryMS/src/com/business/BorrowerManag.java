package com.business;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.database.DatabaseConnect;

public class BorrowerManag {
	
	public boolean insertBorrower(String ssn, String name, String address, String phone){
		boolean b = DatabaseConnect.insertBorrower(ssn, name, address, phone);
		return b;
	}
	
	public ArrayList<Borrower> searchBorrowers(String id, String name){
		ArrayList<Borrower> result = new ArrayList<>();
		ResultSet rset;
		if(!(id == null || id.equals(""))){
			int idInt = Integer.parseInt(id);
			rset = DatabaseConnect.selectBorrowerByID(idInt);
		}else{
			rset = DatabaseConnect.selectBorrowerByName(name);
		}
		
		try{
			while (rset.next()){
				int cardid = rset.getInt("CARD_ID");
				String ssn = rset.getString("SSN");
				String bname = rset.getString("BNAME");
				String address = rset.getString("ADDRESSS");
				String phone = rset.getString("PHONE");
								
				Borrower b = new Borrower(cardid, ssn, bname, address, phone);
				result.add(b);
			}
			
		}catch(SQLException ex) {
			System.out.println("Error in connection: " + ex.getMessage());
		}
		return result;
	}

}
