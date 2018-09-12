package com.show;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.business.*;

public class DisplayLoans extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	         
	JTable jt;
	JLabel bookid;
	JTextField bookidInput;
	JLabel cardid;
	JTextField cardidInput;
	JLabel username;
	JTextField usernameInput;
	JButton jb1;
	JButton jb2;
	JScrollPane sp;

	DisplayLoans(){
		JPanel panel =new JPanel();
		panel.setLayout(null);
		
		bookid = new JLabel("BOOK_ID:");
		bookid.setBounds(10, 10, 80, 25);
		panel.add(bookid);

		bookidInput = new JTextField(20);
		bookidInput.setBounds(100, 10, 160, 25);
		panel.add(bookidInput);
		
		cardid = new JLabel("Card_ID:");
		cardid.setBounds(10, 40, 80, 25);
		panel.add(cardid);

		cardidInput = new JTextField(20);
		cardidInput.setBounds(100, 40, 160, 25);
		panel.add(cardidInput);
		
		username = new JLabel("User_Name:");
		username.setBounds(10, 70, 80, 25);
		panel.add(username);

		usernameInput = new JTextField(20);
		usernameInput.setBounds(100, 70, 160, 25);
		panel.add(usernameInput);
		
		jb1 = new JButton("search");
		jb1.setBounds(270, 10, 100, 25);
		panel.add(jb1);
		
		jb2 = new JButton("Check In");
		jb2.setBounds(400, 10, 150, 25);
		panel.add(jb2);
		
		
		jt=new JTable();
		String column[]={"ISBN","Title","Card_ID", "UserName", "Date_Out", "Due_Date"};
		DefaultTableModel contactTableModel = (DefaultTableModel) jt.getModel();
	    contactTableModel.setColumnIdentifiers(column);
		
		
		sp = new JScrollPane(jt);
		sp.setBounds(10, 110, 600, 300);
		panel.add(sp);
		
		jb1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //add event handler
            	updateTable();
            }
		});
		
		jb2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //add event handler
            	checkIn();
            	updateTable();
            }
		});
		
		this.add(panel);
		this.setSize(600,500);
		this.setTitle("Check In");
		this.setVisible(true);
	}
	
	private String getBookID() {
        return bookidInput.getText().trim();
    }
	
	private String getCardId() {
        return cardidInput.getText().trim();
    }
	
	private String getBName() {
        return usernameInput.getText().trim();
    }
	
	private void updateTable(){
		DefaultTableModel tableModel = (DefaultTableModel) jt.getModel();
		tableModel.setRowCount(0);
		LoanManag lm = new LoanManag();
		ArrayList<Loan> loans = lm.searchLoan(getBookID(), getCardId(), getBName());
		for(int i = 0; i < loans.size(); i++){
			String[] loan = new String[6];
			loan[0] = loans.get(i).getIsbn();
			loan[1] = loans.get(i).getTitle();
			loan[2] = Integer.toString(loans.get(i).getCardid());
			loan[3] = loans.get(i).getBname();
			loan[4] = loans.get(i).getDateout().toString();
			loan[5] = loans.get(i).getDuedate().toString();
			tableModel.addRow(loan);
		}
		
		jt.setModel(tableModel);
	}
	
	public void checkIn(){
		LoanManag lm = new LoanManag();
    	int row = jt.getSelectedRow();
    	if(row < 0){
			new Message("Please select a book!");
			return;
		}
    	String value = jt.getModel().getValueAt(row, 0).toString();
    	int loanid = lm.getLoanId(value);
    	lm.checkIn(loanid);
	}

}