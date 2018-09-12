package com.business;

import java.sql.*;
import java.util.ArrayList;

import com.database.DatabaseConnect;

public class FineManag {
	
	public ArrayList<FineByUser>  listFineByUser(){
		ArrayList<FineByUser> res = new ArrayList<>();
		ResultSet rset;
		
		rset = DatabaseConnect.listFineByUser();
		
		try{
			while (rset.next()){
				int cardid = rset.getInt("BL.CARD_ID");
				float amout = rset.getFloat("AMOUNT");
								
				FineByUser fbu = new FineByUser(cardid, amout);
				res.add(fbu);
			}
			
		}catch(SQLException ex) {
			System.out.println("Error in connection: " + ex.getMessage());
		}
		
		return res;
	}
	
	public boolean updatePay(int cardid){
		return DatabaseConnect.updatePay(cardid);
	}

}
