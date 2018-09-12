package com.show;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import com.database.DatabaseConnect;

public class Menu extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton jb1;
	JButton jb2;
	JButton jb3;
	JButton jb4;
	Menu(){
		JPanel panel =new JPanel();
		panel.setLayout(null);
		
		jb1 = new JButton("Search Books");
		jb1.setBounds(50, 10, 200, 50);
		panel.add(jb1);
		
		jb2 = new JButton("Manage Borrowers");
		jb2.setBounds(50, 80, 200, 50);
		panel.add(jb2);
		
		jb3 = new JButton("Check In");
		jb3.setBounds(50, 150, 200, 50);
		panel.add(jb3);
		
		jb4 = new JButton("Manage Fines");
		jb4.setBounds(50, 220, 200, 50);
		panel.add(jb4);
		
		
		jb1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //add event handler
            	new DisplayBooks();
            }
		});
		
		jb2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //add event handler
            	new DisplayBorrowers();
            }
		});
		
		jb3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //add event handler
            	new DisplayLoans();
            }
		});
		
		jb4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //add event handler
            	new DisplayFines();
            }
		});
		
		this.add(panel);
		this.setSize(350,400);
		this.setTitle("Menu");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
		this.setVisible(true);
		
		addWindowListener(new WindowAdapter() {
		      public void windowClosing(WindowEvent e) {
		    	  DatabaseConnect.close();
		      }
		    });
	}
	
		

}
