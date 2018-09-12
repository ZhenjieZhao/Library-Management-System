package com.business;

import com.database.DatabaseConnect;

public class AuthorManag {
	public String getAuthorID(String name){
		//DatabaseConnect.connect();
		String id = DatabaseConnect.getAuthorIDS(name);
		//DatabaseConnect.close();
		return id;
	}

}
