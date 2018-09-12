package com.show;

import com.database.DatabaseConnect;

public class Test {
	//this is the main method used to start the program
	public static void main(String[] args) {
		DatabaseConnect.connect();
		
		new Menu();
		

	}

}
