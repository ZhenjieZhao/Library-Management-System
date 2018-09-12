package com.show;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.business.BorrowerManag;

public class UpdateBorrower extends JFrame{
	private static final long serialVersionUID = 1L;
	
	
	JLabel cardId;
	JLabel cardIdInput;
	JLabel ssn;
	JTextField ssnInput;
	JLabel bname;
	JTextField bnameInput;
	JLabel address;
	JTextField addressInput;
	JLabel phone;
	JTextField phoneInput;
	JButton jb;
	
	UpdateBorrower(String cardid){
		JPanel panel =new JPanel();
		panel.setLayout(null);
		
		cardId = new JLabel("Card_ID:");
		cardId.setBounds(10, 10, 80, 25);
		panel.add(cardId);
		
		cardIdInput = new JLabel(cardid);
		cardIdInput.setBounds(100, 10, 80, 25);
		panel.add(cardIdInput);
		
		ssn = new JLabel("Ssn:");
		ssn.setBounds(10, 40, 80, 25);
		panel.add(ssn);
		
		ssnInput = new JTextField();
		ssnInput.setBounds(100, 40, 80, 25);
		panel.add(ssnInput);
		
		bname = new JLabel("Name:");
		bname.setBounds(10, 70, 80, 25);
		panel.add(bname);
		
		bnameInput = new JTextField();
		bnameInput.setBounds(100, 70, 80, 25);
		panel.add(bnameInput);
		
		address = new JLabel("Address:");
		address.setBounds(10, 100, 80, 25);
		panel.add(address);
		
		addressInput = new JTextField();
		addressInput.setBounds(100, 100, 80, 25);
		panel.add(addressInput);
		
		phone = new JLabel("Phone:");
		phone.setBounds(10, 130, 80, 25);
		panel.add(phone);
		
		phoneInput = new JTextField();
		phoneInput.setBounds(100, 130, 80, 25);
		panel.add(phoneInput);
		
		jb = new JButton("Submit");
		jb.setBounds(50, 160, 80, 25);
		panel.add(jb);
		
		jb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //add event handler
            	if(!checkEmpty()){
            		if(insertBorrower()){
                		new Message("Successfully Create New Borrower!");
                	}else{
                		new Message("Unsuccessful!");
                	}
            	}
            }
		});
		
		this.add(panel);
		this.setSize(300,300);
		this.setTitle("Update Borrower");
		this.setVisible(true);
	}
	
	public String getSsn() {
        return ssnInput.getText().trim();
    }
	public String getBname() {
        return bnameInput.getText().trim();
    }
	public String getAddress() {
        return addressInput.getText().trim();
    }
	public String getPhone() {
        return phoneInput.getText().trim();
    }
	
	public boolean checkEmpty(){
		if(getSsn().equals("") || getBname().equals("") ||getAddress().equals("") ||getPhone().equals("")){
			new Message("Please fill every field");
			return true;
		}
		return false;
	}
	
	public boolean insertBorrower(){
		BorrowerManag bom = new BorrowerManag();
		return bom.insertBorrower(getSsn(), getBname(), getAddress(), getPhone());
	}

}
